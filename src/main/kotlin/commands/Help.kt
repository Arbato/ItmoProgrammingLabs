package commands
import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class Help(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {

            this.receiver.help()
        var res = Result()
        res.success()
        res.setMsg("you got help")
        return res
    }
}