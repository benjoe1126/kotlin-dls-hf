package network.arp

import enums.EtherTypes
import network.encap.L2
import printable.Printable

class ARP: L2, Printable {
    override fun getEtherType() = EtherTypes.ARP

    override fun print(): String {
        TODO("Not yet implemented")
    }

    override fun getLength(): UInt {
        TODO("Not yet implemented")
    }
}