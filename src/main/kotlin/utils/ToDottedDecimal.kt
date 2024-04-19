package utils

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