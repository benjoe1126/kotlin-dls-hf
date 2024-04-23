package utils

import exepctions.InvalidIPFormatException
import network.packet.IPV6
import network.packet.ipv6

fun String.toIPv6(): IPV6{
    if(isBlank() || !contains("/")){
        throw InvalidIPFormatException("Invalid ipv6 format")
    }
    val splt = this.split("/")
    val ret = ipv6{
        address = splt[0]
        mask = splt[1].toUInt()
    }
    if(!ret.validate()){
        throw InvalidIPFormatException("Invalid ipv6 format, got ${this}")
    }
    return ret
}