package network.packet

import utils.pow
import utils.toDottedDecimal

class IPV4: IP {
    var address: String = ""
    var mask: UInt = 0u
    private val numericAddress by lazy {
        address.split(".")
            .map{ it.toUInt() }
            .reduce {
                    acc, octet -> (acc shl 8) + octet }
    }
    private val maskNumeric by lazy{
        2u.pow(mask) - 1u shl (32 - mask.toInt())
    }
    private val prefixNumeric by lazy{
        numericAddress and maskNumeric!!
    }
    override fun toString(): String {
        return String.format("%s/%d",address,mask.toInt())
    }
    override fun subnet(): String{
        return prefixNumeric.toDottedDecimal()
    }

    override fun validate(): Boolean {
        return address.matches(Regex("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}\$")) &&
                mask >= 0u && mask <= 32u
    }
}

fun ipv4(init: IPV4.() -> Unit): IPV4{
    val ipaddr = IPV4()
    ipaddr.init()
    if(!ipaddr.validate()){
        throw IllegalArgumentException("Invalid IPV4 address and/or netmask provided, ip: ${ipaddr.address}, netmask: ${ipaddr.mask}")
    }
    return ipaddr
}