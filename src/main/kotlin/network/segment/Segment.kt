package network.segment

import annotations.Encapsulation
import network.message.Message

@Encapsulation
open abstract class Segment() {
    protected  var dst: UInt = 0u
    protected var src: UInt = 0u
    protected lateinit var checksum: ByteArray
    protected var data: Message? = null
    open fun calcChecksum(){}
}
