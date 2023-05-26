package commands
import Receiver
import constituents.Person
import exceptions.*

class Add(private var receiver: Receiver): Command {
    /**
     * Execute method add in receiver.
     * @param args - the arguments that are passed to command
     * @throws ValidationException
     * @throws WrongNumberOfArgsException
     */

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result {
        val res = commands.Result()


        try {
            receiver.pushPerson(person)
            res.setMsg("addition to collection completed")
            res.success()

        } catch (ex: Exception) {
            res.fail()
            res.setMsg("adding to collection failed: "+ ex.message)
        }
        return res
    }

    override fun execute(s: String): Result {
        TODO("Not yet implemented")
    }
}

