package commands
import Receiver
import exceptions.*

class Add(private var receiver: Receiver): Command {
    /**
     * Execute method add in receiver.
     * @param args - the arguments that are passed to command
     * @throws ValidationException
     * @throws WrongNumberOfArgsException
     */

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String):Result {
        if (s.isNullOrEmpty()) {
            receiver.addElement()
        }else{
            receiver.addElement(s)
        }
        val res = commands.Result()
        res.setMsg("addition to collection completed")
        res.success()
        return res
    }
}

