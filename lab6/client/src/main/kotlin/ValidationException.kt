
class ValidationException : java.lang.Exception {
    constructor() : super("Validation Exception: 'the variable is not in correct range!'")
    constructor(message: String?) : super(message)
}
