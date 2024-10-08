package network.packet

import annotations.Encapsulation
import delegates.once
import enums.EtherTypes
import exepctions.MissmatchedIPException
import network.encap.L2
import network.encap.L3
import network.ip.IP
import network.ip.IPV4
import network.ip.IPV6
import printable.Printable
import utils.addVerticalBar
import kotlin.properties.Delegates
import kotlin.random.Random

class PacketV4: Packet() {
    var srcIp: IPV4 by Delegates.once()
    var dstIp: IPV4 by Delegates.once()
    override val version: Int = 4
    private val identification: UInt by lazy {
        val bytes = Random.nextBytes(2)
        (bytes[0].toUInt() shl 8) + bytes[1].toUInt()
    }

    override fun getEtherType() = EtherTypes.IPv4

    override fun getLength(): UInt = 24u

    private val checksum by lazy {
        srcIp.hashCode().toBigInteger() + dstIp.hashCode().toBigInteger() + version.toBigInteger() + tos.hashCode()
            .toBigInteger()
    }


    override fun print(): String {
        val seg = segment
        return "IPV4 packet\n" + """
            ---------------------------------------------------
            | $version | 32 | $tos | Packet Length  
            | $identification |   | DF | MF | Fragment Offset  
            | $ttl  |  Transport | $checksum 
            | ${srcIp.addressString()} 
            | ${dstIp.addressString()} 
            ---------------------------------------------------
        """
            .trimIndent()
            .addVerticalBar() + "\n" + if (seg != null && seg is Printable) seg.print() else ""
    }
}

fun packetv4(init: PacketV4.() -> Unit): PacketV4 {
    val packetV4 = PacketV4()
    packetV4.init()
    return packetV4
}
