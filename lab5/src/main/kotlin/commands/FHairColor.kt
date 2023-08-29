package commands
import Receiver
import constituents.Color
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class FHairColor(private var receiver:Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    /*override fun Execute(vararg args: String?) {
        if (args.size==1) {
            try{
                this.receiver.fHairColor(Color.valueOf(args[0]!!))
            } catch (ex: Exception){System.err.println(ex.message)}

        } else {
            throw WrongNumberOfArgsException("Wrong number of arguments! See more info about available commands. \"help\" ")

        }
    }*/
    override fun execute(str: String): Result {
        receiver.fHairColor(Color.valueOf(str))
        var res = Result()
        res.success()
        res.setMsg("Execution of script successful")
        return res
    }

}