package commands

import Receiver
import constituents.Person


class RmLesser(private var receiver:Receiver) : Command {
    override fun execute(s: String): Result {
        val res = Result()

        try{
            s[0]?.let { this.receiver.rmLesser(it.toInt())
            res .success()
            res.setMsg("removal completed successfully")}

        } catch (ex: Exception){
            res.setMsg(ex.message+", fail")
            res.fail()}
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        val res = Result()

        try{
            id?.let { this.receiver.rmLesser(it)
                res .success()
                res.setMsg("removal completed successfully")}

        } catch (ex: Exception){
            res.setMsg(ex.message+", fail")
            res.fail()
        }
        return res
        }

}





