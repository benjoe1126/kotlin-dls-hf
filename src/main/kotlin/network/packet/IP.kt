package network.packet

sealed interface IP {
    fun subnet(): String
    fun validate(): Boolean
}