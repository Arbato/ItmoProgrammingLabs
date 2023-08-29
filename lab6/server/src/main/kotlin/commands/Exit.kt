package commands

import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class Exit(private var receiver: Receiver) : Command {
    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String):Result  {

            this.receiver.exit()
            var res = Result()
            res.success()
            res.setMsg("exit completed")
            return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}