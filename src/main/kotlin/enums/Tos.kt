package enums

import kotlin.experimental.or

enum class Tos(val value: Byte) {
    LOWDELAY(0b00010000),
    THROUGHPUT(0b00001000),
    RELIABILITY(0b00000100),
    LOWCOST(0b00000010),
    NONE(0x00),
    HIGH_PRECEDENCE(0b1000000),
    MID_PRECEDENCE(0b0100000),
    LOW_PRECEDENCE(0b0010000),
}
operator fun Tos.plus(other: Tos): Byte {
    return this.value.or(other.value)
}
operator fun Tos.plus(other: Byte): Byte {
    return this.value.or(other)
}
fun Tos.toString(): String{
    return value.toString()
}