package utils

import exepctions.InvalidIPFormatException
import network.ip.IPV4
import network.ip.ipv4

fun String.toIpV4(): IPV4 {
    if (this.isEmpty()) {
        throw InvalidIPFormatException("Invalid IPv4 format provided, expected XX.XX.XX.XX/Y, got ${this}")
    }
    var addr = this
    var msk = 32u
    if(this.contains("/")){
        val splt = this.split("/")
        addr = splt[0]
        msk = splt[1].toUInt()
    }
    val ret = ipv4{
        address = addr
        mask = msk
    }
    if(!ret.validate()){
        throw InvalidIPFormatException("Invalid IPv4 provided, expected XX.XX.XX.XX/Y, got ${this}")
    }
    return ret
}
