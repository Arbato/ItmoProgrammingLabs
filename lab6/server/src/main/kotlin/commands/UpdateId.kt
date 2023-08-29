package commands

import Receiver
import constituents.Person
import exceptions.WrongNumberOfArgsException

class UpdateId(private var receiver: Receiver): Command {
    override fun execute(s:String): Result {
        var res = Result()
        if (s.isNotEmpty()) {
                throw WrongNumberOfArgsException("Wrong number of arguments! See more info about available commands. \"help\" ")
        } else {
            try{
                //this.receiver.getNewId()
                //this.receiver.updateId()
                res.success()

                res.setMsg("updating id completed")

                } catch (ex: Exception){
                    res.fail()
                res.setMsg("Updating Id failed, bc "+ ex.message)

            }

        }
        return res
        }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        var res = Result()

            try{
                this.receiver.updateId(person!!, id!!)
                res.success()
                res.setMsg("updating id completed")

            } catch (ex: Exception){
                println(id)
                res.fail()
                res.setMsg("Updating Id failed, bc "+ ex.message)
            }

        return res
    }
}
