package utils

fun String.addVerticalBar(): String {
    val lines = lines()
    val maxLength = lines.maxByOrNull { it.length }?.length ?: 0
    val result = lines.mapIndexed{ idx, line ->
        if(idx == 0 || idx == lines.lastIndex){
            line
        } else {
            val padding = " ".repeat(maxLength - line.length)
            "$line$padding|"
        }
    }
    return result.joinToString("\n")
}