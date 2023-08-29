package commands
import Receiver
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException

class RealSortByHair(private var receiver: Receiver) : Command {

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s:String): Result {
        var res = Result()

            try{
                this.receiver.hairComparator()

                res.setMsg("execution successful")
                res.success()
            } catch (ex: Exception){
                res.setMsg(ex.message+"")
                res.fail()
            }
        return res

    }
}