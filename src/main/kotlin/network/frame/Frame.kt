package network.frame

import enums.EtherTypes
import network.encap.L2
import network.packet.Packet

class Frame{
    lateinit var srcMac: String
    lateinit var dstMac: String
    private var preambleAndSfd: Byte = 85
    var data: L2? = null
    private var etherType: EtherTypes = EtherTypes.IPv4
        get(){
            return field
        }
    fun computeEtherType(){
        etherType = data?.getEtherType()?:EtherTypes.VLAN
    }
}

fun frame(init: Frame.() -> Unit){
    var fr = Frame()
    fr.init()
    fr.computeEtherType()
}