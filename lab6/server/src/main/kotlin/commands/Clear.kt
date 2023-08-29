package commands

import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class Clear(private var receiver:Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s: String): Result {
        var res = Result()
        try {
            this.receiver.clear()
            res.setMsg("clearing collection completed")
            res.success()
        } catch (ex: Exception) {
            res.fail()
            res.setMsg("clearing generated an exception")
            }

    return res
    }
    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}