package network.segment

import annotations.Encapsulation
import network.encap.L3
import network.message.Message
import printable.Printable
import java.math.BigInteger

@Encapsulation
open abstract class Segment: Printable, L3 {
    abstract var dst: UInt
    abstract var src: UInt
    protected abstract val checkSum: BigInteger
    var data: Message? = null
    abstract fun validate(): Boolean
}
