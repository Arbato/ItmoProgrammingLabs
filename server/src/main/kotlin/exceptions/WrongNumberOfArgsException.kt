package exceptions

class WrongNumberOfArgsException : java.lang.Exception {
    constructor() : super("Invalid number of arguments passed to method, there should be one argument, the xml file.")
    constructor(message: String?) : super(message)
    fun getMesage(): String?
    {
        return this.message
    }
}
