package network.packet

import enums.EtherTypes
import exepctions.MissmatchedIPException
import network.encap.L2
import network.ip.IPV6
import java.awt.Graphics
import java.awt.print.PageFormat
import java.awt.print.Printable

class PacketV6: Packet() {
    override fun getEtherType() = EtherTypes.IPv6

    override fun getLength(): UInt {
        return 0u
    }

    override fun validateIp() = (srcIp.javaClass == dstIp.javaClass) && (srcIp.javaClass == IPV6().javaClass)

    override val version = 6
    override fun print(): String {
        TODO("Implement me plz :3")
    }
}
fun packetv6(init: PacketV6.()->Unit): PacketV6{
    val packetV6 = PacketV6()
    packetV6.init()
    if(!packetV6.validateIp()) throw MissmatchedIPException("Only IPV6 addresses should have been provided when building a packetv6 instance")
    return packetV6
}