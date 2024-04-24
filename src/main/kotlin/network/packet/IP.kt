package network.packet

import annotations.Encapsulation

@Encapsulation
sealed interface IP {
    fun subnet(): String
    fun validate(): Boolean
}