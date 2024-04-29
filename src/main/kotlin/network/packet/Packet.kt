package network.packet

import annotations.Encapsulation
import delegates.once
import network.encap.L2
import network.encap.L3
import network.ip.IP
import printable.Printable
import kotlin.properties.Delegates

@Encapsulation
abstract class Packet: L2, Printable {
    var srcIp: IP by Delegates.once()
    var dstIp: IP by Delegates.once()
    var ttl:UByte = 255u
    var segment: L3? = null
    abstract val version: Int
    abstract fun validateIp(): Boolean
}