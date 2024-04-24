package utils

import java.math.BigInteger
import java.net.Inet6Address

fun UInt.toDottedDecimal(): String {
    val num = this.toBigEndian()
    var ret = ""
    for(i in (0..3)){
        ret += num shr (i*8) and 0x000000ffu
        if(i != 3)
            ret += "."
    }
    return ret
}
fun UInt.toBigEndian(): UInt {
    return ((this and 0xFFu shl 24) or
            (this and 0xFF00u shl 8) or
            (this and 0xFF0000u shr 8) or
            (this and 0xFF000000u shr 24))
}

fun BigInteger.toCanonicalIpv6(): String{
    return String.format("%s:%s:%s:%s:%s:%s:%s:%s",
        Integer.toHexString(this.shiftRight(112).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(96).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(80).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(64).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(48).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(32).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.shiftRight(16).and(BigInteger.valueOf(0xFFFF)).toInt()).toString(),
    Integer.toHexString(this.and(BigInteger.valueOf(0xFFFF)).toInt()).toString())
}
