package network.message

import delegates.once
import enums.DNSFlags
import utils.addVerticalBar
import java.math.BigInteger
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextULong

class DNS: Message() {
    private val id = Random.nextULong()
    var opcode:UInt by Delegates.once()
    var flag: DNSFlags by Delegates.once()
    var rcode: UInt by Delegates.once()
    var QDCOUNT: BigInteger by Delegates.once()
    var ANCOUNT: BigInteger by Delegates.once()
    var NSCOUNT: BigInteger by Delegates.once()
    var ARCOUNT: BigInteger by Delegates.once()
    override fun print() = "DNS message\n" +
            """
                ----------------------------
                | $id
                | QR | $opcode | $flag | $rcode
                | $QDCOUNT 
                | $ANCOUNT
                | $NSCOUNT
                | $ARCOUNT
                ----------------------------
            """.trimIndent()
                .addVerticalBar()
}
fun dns(init: DNS.() -> Unit): DNS {
    val dns = DNS()
    dns.init()
    return dns
}