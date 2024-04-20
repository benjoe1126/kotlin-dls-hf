package enums

enum class EtherTypes(val type:Int) {
    IPv4(0x0800),
    IPv6(0x86DD),
    ARP(0x0806),
    RARP(0x8035),
    VLAN(0x8100)
}