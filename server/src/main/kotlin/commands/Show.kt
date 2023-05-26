package commands
import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class Show(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        var res = Result()
        res.setText(this.receiver.show())
        res.success()
        res.setMsg("show completed")
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}