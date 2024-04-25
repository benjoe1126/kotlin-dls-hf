package network.frame

import annotations.Encapsulation
import delegates.once
import enums.EtherTypes
import exepctions.InvalidMacAddressException
import network.encap.L2
import printable.Printable
import utils.validMacAddress
import kotlin.math.absoluteValue
import kotlin.properties.Delegates

@Encapsulation
class Frame: Printable {
    var srcMac: String by Delegates.once()
    var dstMac: String by Delegates.once()
    private var preambleAndSfd: Byte = 85
    var data: L2? = null
    private val etherType by lazy{
        data?.getEtherType()?:EtherTypes.VLAN
    }
    private val crc by lazy{
        srcMac.hashCode().absoluteValue.toBigInteger() + dstMac.hashCode().absoluteValue.toBigInteger() + preambleAndSfd.hashCode().absoluteValue.toBigInteger() + data.hashCode().absoluteValue.toBigInteger()

    }
    override fun print(): String {
        val dat = data
        return "Ethernet frame\n" + """
            -----------------------------------------------------------------------
            | $preambleAndSfd | ${dstMac} | ${srcMac} | ${data?.getLength()?:0u + 20u} | $etherType | $crc |
            -----------------------------------------------------------------------
                """.trimIndent() + "\n" + if(dat != null && dat is Printable) dat.print() else ""
    }
    fun validateMacAddresses(): Boolean{
        return srcMac.validMacAddress() and dstMac.validMacAddress()
    }
}

fun frame(init: Frame.() -> Unit): Frame{
    var fr = Frame()
    fr.init()
    if(!fr.validateMacAddresses()){
        throw InvalidMacAddressException("Malformed mac address, expected xx:xx:xx:xx:xx:xx, got source = ${fr.srcMac} ;  destination = ${fr.dstMac}")
    }
    return fr
}