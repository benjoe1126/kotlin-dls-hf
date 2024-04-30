package network.segment

import delegates.once
import utils.addVerticalBar
import java.lang.IllegalStateException
import java.math.BigInteger
import kotlin.math.absoluteValue
import kotlin.properties.Delegates

class UDP: Segment() {
    override var src: UInt by Delegates.once()
    override var dst: UInt by Delegates.once()
    private val length by lazy{
        20
    }
    override val checkSum: BigInteger by lazy{
        src.hashCode().absoluteValue.toBigInteger() + dst.hashCode().absoluteValue.toBigInteger() + length.hashCode().absoluteValue.toBigInteger()
    }

    override fun validate(): Boolean {
        return src > 0u && dst > 0u
    }

    override fun print(): String {
        return "UDP header\n" + """
            ------------
            | $src | $dst
            | $length | $checkSum
            ------------
        """.trimIndent()
            .addVerticalBar()
    }
}

fun udp(init: UDP.() -> Unit): UDP {
    val ud = UDP()
    ud.init()
    if(!ud.validate())  throw IllegalStateException("UDP src and dst port must be gt then 0")
    return ud
}