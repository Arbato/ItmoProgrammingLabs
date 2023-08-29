package commands

import Receiver
import constituents.Person

class Info(private var receiver: Receiver): Command {

    override fun execute(s:String): Result {
        var res = Result()
        res.setText(this.receiver.info())
        res.success()
        res.setMsg("you got info")
        return res

    }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}