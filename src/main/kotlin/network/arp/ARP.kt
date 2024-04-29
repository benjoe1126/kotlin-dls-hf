package network.arp

import annotations.Encapsulation
import delegates.once
import enums.ArpOperation
import enums.EtherTypes
import exepctions.InvalidArpPacketException
import network.encap.L2
import network.ip.IPV4
import printable.Printable
import utils.addVerticalBar
import utils.validMacAddress
import kotlin.properties.Delegates

@Encapsulation
class ARP: L2, Printable {
    val hardwareType = 1 //For ethernet, nothing else is supported :D
    var operation: ArpOperation by Delegates.once()
    var senderHardwareAddress: String by Delegates.once()
    var targetHardwareAddress: String by Delegates.once()
    var senderProtocolAddress: IPV4 by Delegates.once()
    var targetProtocolAddress: IPV4 by Delegates.once()
    val hardwareAddressLength = 6
    private val protocolAddressLength = 32
    private val protocolType = 0x0800
    override fun getEtherType() = EtherTypes.ARP
    override fun print(): String {
        return """
            --------------------------------------------------------------------------------
            | $hardwareType | $protocolType 
            | $hardwareAddressLength | $protocolAddressLength | $operation
            | $senderHardwareAddress
            | $senderProtocolAddress
            | $targetHardwareAddress
            | $targetProtocolAddress
            --------------------------------------------------------------------------------
        """.trimIndent()
            .addVerticalBar()
    }
    fun validate():Boolean{
        return targetHardwareAddress.validMacAddress() and
                targetHardwareAddress.validMacAddress() and
                targetProtocolAddress.validate() and
                senderProtocolAddress.validate()
    }

    override fun getLength(): UInt = 32u
}

fun arp(init: ARP.() -> Unit): ARP {
    val ret = ARP()
    ret.init()
    if(!ret.validate()){
        throw InvalidArpPacketException("""Malformed ARP packet, addresses missmatched, got
               src ip: $ret.senderProtocolAddress
               dst ip: $ret.targetProtocolAddress
               src mac: $ret.senderHardwareAddress
               dst mac: $ret.targetHardwareAddress
            """.trimIndent())
    }
    return ret
}