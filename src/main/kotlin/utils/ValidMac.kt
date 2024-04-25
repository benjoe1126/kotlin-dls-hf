package utils

fun String.validMacAddress(): Boolean{
   return matches(Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$"))
}