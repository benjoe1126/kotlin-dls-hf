package utils

fun UInt.pow(exp: UInt): UInt{
    var res = 1u
    var base = this
    var exponent = exp
    while ( exponent > 0u) {
        if (exponent % 2u == 0u) {
            exponent /= 2u
            base *= base
        } else {
            exponent -= 1u
            res *= base
            exponent /= 2u
            base *= base
        }
    }
    return res
}