package network.message

import delegates.once
import enums.HTTPStatusCode
import enums.HTTPVersion
import kotlin.properties.Delegates

class HTTP: Message() {
    var version: HTTPVersion by Delegates.once()
    var status: HTTPStatusCode by Delegates.once()
    lateinit var header: Map<String, Any>
    var body: String by Delegates.once()
    override fun print() =
        "HTTP message\n"+
        """
HTTP/${version}, ${status.value} ${status.toString()}
${header}

$body
        """.trimIndent()
}

fun http(init: HTTP.() -> Unit): HTTP {
    val ht = HTTP()
    ht.apply(init)
    return ht
}