package commands
import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class Help(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        var res = Result()
        res.setText(this.receiver.help())

        res.success()
        res.setMessage("you got help")
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}