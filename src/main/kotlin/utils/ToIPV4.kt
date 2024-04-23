package utils

import exepctions.InvalidIPFormatException
import network.packet.IPV4
import network.packet.ipv4

fun String.toIpV4(): IPV4 {
    if (this.isEmpty() || !this.contains("/")) {
        throw InvalidIPFormatException("Invalid IPv4 format provided, expected XX.XX.XX.XX/Y, got ${this}")
    }
    val splt = this.split("/")
    val ret = ipv4{
        address = splt[0]
        mask = splt[1].toUInt()
    }
    if(!ret.validate()){
        throw InvalidIPFormatException("Invalid IPv4 provided, expected XX.XX.XX.XX/Y, got ${this}")
    }
    return ret
}
