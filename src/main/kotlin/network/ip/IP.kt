package network.ip

import annotations.Encapsulation

@Encapsulation
sealed interface IP {
    fun addressString(): String
    fun subnet(): String
    fun validate(): Boolean
}