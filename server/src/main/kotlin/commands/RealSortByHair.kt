package commands
import Receiver
import constituents.Person
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class RealSortByHair(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        var res = Result()

            try{
                this.receiver.hairComparator()

                res.setMsg("sorting by hair completed successful")
                res.success()
            } catch (ex: Exception){
                res.setMsg(ex.message+"")
                res.fail()
            }
        return res
    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}