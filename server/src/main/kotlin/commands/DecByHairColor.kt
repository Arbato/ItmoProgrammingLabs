package commands

import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class DecByHairColor(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result  {
            val res = Result()
            try{
                this.receiver.decByHairColor()
                res.setMsg("printed ascending")
                res.success()

            } catch (ex: Exception){
                ex.message?.let { res.setMsg(it) }
                res.fail()
            }
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}