package commands

import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class RemoveId(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(str: String):Result {
        val res = Result()
        try{
            val x = str[0]?.toInt()
            this.receiver.removeById(x!!)
            res.success()
            res.setMsg("item removed")
        } catch (ex: Exception){res.setMsg(ex.message+", failed")
        res.fail()}
        return res
    }
}