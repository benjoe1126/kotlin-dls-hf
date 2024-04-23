package network.packet

import annotations.Encapsulation
import enums.EtherTypes
import network.encap.L2
import network.encap.L3

@Encapsulation
class Packet: L2 {
    lateinit var srcIp: IP
    lateinit var dstIp: IP
    private var segment: L3? = null
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
}

fun packet(init: Packet.() -> Unit): Packet {
    val packet = Packet()
    packet.init()
    if(!packet.checkIpMatching()) throw IllegalStateException("Src and dst ip addresses must be of same address family")
    return packet
}