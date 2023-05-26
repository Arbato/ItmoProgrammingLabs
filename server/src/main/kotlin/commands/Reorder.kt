package commands

import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class Reorder(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        val res = Result()
            try{
                receiver.reorder()
                res.setMsg("reordering successful")
                res.success()
            } catch (ex: Exception){res.setMsg(ex.message+", fail")
                res.fail()}
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}
// add class message^ the res{message, status}  of execute