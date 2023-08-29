package commands
import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class Show(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        this.receiver.show()
        var res = Result()
        res.success()
        res.setMsg("show completed")
        return res
    }
}