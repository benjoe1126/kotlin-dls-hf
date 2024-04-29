package network.ip

import utils.toCanonicalIpv6
import java.math.BigInteger
import java.net.InetAddress

class IPV6: IP {
    lateinit var address: String
    var mask: UInt = 0u

    private val _addressNumeric by lazy {
        if(address.contains("/")){
            val splt = address.split("/")
            address = splt[0]
            mask = splt[1].toUInt()
        }
        val i = InetAddress.getByName(address)
        val a: ByteArray = i.address
        BigInteger(1, a)
    }
    private val _maskNumeric by lazy {
        (2.toBigInteger().pow(mask.toInt()) - 1.toBigInteger() shl (128 - mask.toInt() - 1))
    }

    private val prefixNumeric by lazy{
        _maskNumeric and _addressNumeric
    }
    override fun subnet(): String {
        return prefixNumeric.toCanonicalIpv6()
    }
    override fun validate(): Boolean{
        return address.isNotBlank()
                && address.matches(
                    Regex(
                        "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))"
                    )
                )
    }

    override fun addressString() = address
}
fun ipv6(init: IPV6.() -> Unit): IPV6 {
    val ret = IPV6()
    ret.init()
    return ret
}