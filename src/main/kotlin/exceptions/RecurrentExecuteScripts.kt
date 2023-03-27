package exceptions

class RecurrentExecuteScripts: java.lang.Exception {

    constructor() : super("Recurring script!")
    constructor(message: String?) : super(message)
}