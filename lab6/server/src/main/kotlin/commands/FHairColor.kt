package commands
import Receiver
import constituents.Color
import constituents.Person
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
    override fun execute(s: String): Result {
        receiver.fHairColor(Color.valueOf(s))
        var res = Result()
        res.success()
        res.setMsg("Execution of script successful")
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        var res = Result()

        try {
            println(color)
            res.setText(receiver.fHairColor(Color.valueOf(color!!)))
            res.success()
            res.setMsg("filter by hair color $color successful")
        }catch (ex: Exception){
            res.fail()
            res.setMsg("filter by hair color $color unsuccessful, " + ex.message)
        }
        return res
    }

}