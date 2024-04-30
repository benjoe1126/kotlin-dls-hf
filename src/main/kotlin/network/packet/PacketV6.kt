package network.packet

import delegates.once
import enums.EtherTypes
import enums.Extension
import network.ip.IPV6
import printable.Printable
import utils.addVerticalBar
import kotlin.properties.Delegates

class PacketV6: Packet() {
    var srcIp: IPV6 by Delegates.once()
    var dstIp: IPV6 by Delegates.once()
    var flowLabel = 0
    lateinit var nextHeader: List<Extension>
    override fun getEtherType() = EtherTypes.IPv6
    override fun getLength() = 0u
    override val version = 6
    private val payloadLength by lazy{
        40 + nextHeader.size * 8
    }
    override fun print(): String {
        val seg = segment
        return "IPV6 packet\n" + """
            | $version | $tos | $flowLabel
            | $payloadLength | Next header | $ttl
            | ${srcIp.address}
            | ${dstIp.address}
            """".trimIndent()
            .addVerticalBar() + "\n" + if(seg != null && seg is Printable) seg.print() else ""
    }
}
fun packetv6(init: PacketV6.()->Unit): PacketV6{
    val packetV6 = PacketV6()
    packetV6.init()
    return packetV6
}