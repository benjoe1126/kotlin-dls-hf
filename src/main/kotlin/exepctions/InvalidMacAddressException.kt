package exepctions

class InvalidMacAddressException: Exception {
    constructor(): super()
    constructor(msg: String): super(msg)
    constructor(msg: String, cause: Throwable): super(msg, cause)
    constructor(cause:Throwable): super(cause)
}