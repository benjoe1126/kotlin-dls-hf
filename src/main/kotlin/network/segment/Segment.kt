package network.segment

import network.message.Message
import network.packet.Packet

open abstract class Segment() {
    protected  var dst: UInt = 0u
    protected var src: UInt = 0u
    protected lateinit var checksum: ByteArray
    protected var data: Message? = null
    open fun calcChecksum(){}
}
