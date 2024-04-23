package network.frame

import annotations.Encapsulation
import enums.EtherTypes
import network.encap.L2
import printable.Printable

@Encapsulation
class Frame: Printable {
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
    override fun print(): String {
        return """
            ---------------------------------------------------------------------------------
            |  Preamble | SOF delim | ${dstMac} | ${srcMac} | ${data?.getLength()?:0u + 20u}
            --------------------------------------------------------------------------------- 
                """.trimIndent()
    }
}

fun frame(init: Frame.() -> Unit): Frame{
    var fr = Frame()
    fr.init()
    fr.computeEtherType()
    return fr
}