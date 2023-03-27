package commands

import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class Clear(private var receiver:Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s: String): Result {
        var res = Result()
        try {
            this.receiver.clear()
            res.setMsg("addition to collection completed")
            res.success()
        } catch (ex: Exception) {
            res.fail()
            res.setMsg("clearing generated an exception")
            }

    return res
    }
}