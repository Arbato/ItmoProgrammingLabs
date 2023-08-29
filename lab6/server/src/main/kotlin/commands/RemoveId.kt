package commands

import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class RemoveId(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result {
        val res = Result()
        try{
            this.receiver.removeById(id)
            println("3")

            res.success()

            res.setMsg("item removed")
            res.setText("item removed")
            println("4")


        } catch (ex: Exception){
            res.setMsg("Removing by id failed, because " +ex.message)
            res.fail()
        }

        return res
    }

    override fun execute(s: String): Result {
        TODO("Not yet implemented")
    }
}