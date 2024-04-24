package utils

import exepctions.InvalidIPFormatException
import network.packet.IPV6
import network.packet.ipv6

fun String.toIPv6(): IPV6{
    if(isBlank()) {
        throw InvalidIPFormatException("Invalid ipv6 format")
    }
    var addr = this
    var msk = 128u
    if(this.contains("/")){
        val splt = this.split("/")
        addr = splt[0]
        msk = splt[1].toUInt()
    }
    val ret = ipv6{
        address = addr
        mask = msk
    }
    if(!ret.validate()){
        throw InvalidIPFormatException("Invalid ipv6 format, got ${this}")
    }
    return ret
}