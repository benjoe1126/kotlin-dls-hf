package network.segment

import delegates.once
import network.encap.L3
import printable.Printable
import utils.addVerticalBar
import java.lang.IllegalStateException
import java.math.BigInteger
import kotlin.math.absoluteValue
import kotlin.properties.Delegates

class TCP: Segment(), L3 {
    override var src: UInt by Delegates.once()
    override var dst: UInt by Delegates.once()
    var sequenceNumber: UInt by Delegates.once()
    var ackNumber: UInt by Delegates.once()
    var windowSize: UInt by Delegates.once()
    override fun print(): String {
        val msg = data
        return "TCP header\n" + """
            ---------------------------------------
            | $src | $dst
            | $sequenceNumber 
            | $ackNumber
            | Data offset | Reserved | $windowSize
            | $checkSum
            ---------------------------------------
        """.trimIndent()
            .addVerticalBar() + if((msg != null) && (msg is Printable)) msg.print() else ""
    }

    override fun validate(): Boolean {
        return src > 0u && dst > 0u && sequenceNumber >= ackNumber
    }

    override val checkSum by lazy{
       src.hashCode().absoluteValue.toBigInteger() + dst.hashCode().absoluteValue.toBigInteger() + sequenceNumber.hashCode().absoluteValue.toBigInteger() + ackNumber.hashCode().absoluteValue.toBigInteger() + windowSize.hashCode().absoluteValue.toBigInteger()
    }
}

fun tcp(init: TCP.() -> Unit): TCP {
    val tcp = TCP()
    tcp.init()
    if(!tcp.validate()) throw IllegalStateException("TCP ports should be gt then 0 and sequence number must be greater or equal to ack number")
    return tcp
}