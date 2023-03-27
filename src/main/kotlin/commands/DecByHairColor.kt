package commands

import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class DecByHairColor(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result  {
            val res = Result()
            try{
                this.receiver.decByHairColor()
            } catch (ex: Exception){
                ex.message?.let { res.setMsg(it) }
                res.fail()
            }
        return res
    }
}