package network.packet

import com.sun.tools.jdi.Packet
import utils.pow
import utils.toDottedDecimal

class IP {
    var address: String = ""
    var mask: UInt = 0u
    private var numericAddress = 0u
        get(){
            if(field == 0u){
                field = address.split(".")
                    .map{ it.toUInt() }
                    .reduce {
                            acc, octet -> (acc shl 8) + octet
                    }
            }
            return field
        }
    private var maskNumeric: UInt? = null
        get(){
            if(field == null){
                field = 2u.pow(mask) - 1u shl (32 - mask.toInt())
            }
            return field
        }
    private val prefixNumeric: UInt
        get(){
            return (numericAddress and
                    maskNumeric!!)
        }
    override fun toString(): String {
        return String.format("%s/%d",address,mask.toInt())
    }
    fun subnet(): String{
        return prefixNumeric.toDottedDecimal()
    }
}

fun IP.validate(){
    
}

fun ip(init: IP.() -> Unit): IP{
    val ipaddr = IP()
    ipaddr.init()
    return ipaddr
}