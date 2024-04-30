package enums

enum class Extension(val type: UByte){
    HOP_BY_HOP(0u),
    ROUTING(43u),
    FRAGMENT(44u),
    AH(51u),
    ESP(50u),
    DESTINATION_OPTIONS(60u),
    MOBILITY(135u),
    HIP(139u),
    Shim6(140u),
}