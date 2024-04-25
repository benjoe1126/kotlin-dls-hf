package network.packet

import annotations.Encapsulation
import delegates.once
import enums.EtherTypes
import network.encap.L2
import network.encap.L3
import printable.Printable
import kotlin.properties.Delegates

@Encapsulation
class Packet: L2, Printable {
    var srcIp: IP by Delegates.once()
    var dstIp: IP by Delegates.once()
    var segment: L3? = null
    var ttl: UByte = 255u
    var tos: Byte = 0x00
    private val version by lazy{
        when(srcIp){
            is IPV4 -> 4
            is IPV6 -> 6
        }
    }
    override fun getEtherType(): EtherTypes {
        return when(srcIp){
            is IPV4 -> EtherTypes.IPv4
            is IPV6 -> EtherTypes.IPv6
        }
    }

    override fun getLength(): UInt = 24u

    fun checkIpMatching(): Boolean{
        return srcIp.javaClass == dstIp.javaClass
    }
    private val checksum by lazy{
        srcIp.hashCode().toBigInteger() + dstIp.hashCode().toBigInteger() + version.toBigInteger() + tos.hashCode().toBigInteger()
    }

    override fun print(): String {
        // Needed for smart casting
        val src = srcIp
        //Because of the validity check both should be of the same address family
        val dst = dstIp
        return when(src) {
            is IPV4 -> "IP packet\n" + """
            ---------------------------------------------------
            | $version | 32 | $tos | Packet Length  
            | Identification |   | DF | MF | Fragment Offset  
            | $ttl  |  Transport | $checksum 
            | ${src.address} 
            | ${(dst as IPV4).address} 
            ---------------------------------------------------
        """
                .trimIndent()
                .addVerticalBar()

            is IPV6 -> ""
        }
    }
}

fun packet(init: Packet.() -> Unit): Packet {
    val packet = Packet()
    packet.init()
    if(!packet.checkIpMatching()) throw IllegalStateException("Src and dst ip addresses must be of same address family")
    return packet
}

fun String.addVerticalBar(): String {
    val lines = lines()
    val maxLength = lines.maxByOrNull { it.length }?.length ?: 0
    val result = lines.mapIndexed{ idx, line ->
            if(idx == 0 || idx == lines.lastIndex){
                line
            } else {
                val padding = " ".repeat(maxLength - line.length)
                "$line$padding|"
            }
    }
    return result.joinToString("\n")
}