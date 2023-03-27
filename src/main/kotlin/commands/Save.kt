package commands

import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException


class Save(private var receiver:Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        var res = Result()
            try{
                res.success()
                res.setMsg("saved to file $s")
                if (s.isNullOrEmpty()) {
                    this.receiver.saveToOther()
                }
            } catch (ex: Exception){res.setMsg(ex.message+", fail")
                res.fail()}
    return res
    }
}