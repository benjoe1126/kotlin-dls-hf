package network.packet

class Packet {
    lateinit var srcIpv4: IPV4
    lateinit var dstIpv4: IPV4
}

fun packet(init: Packet.() -> Unit): Packet {
    val packet = Packet()
    packet.init()
    return packet
}