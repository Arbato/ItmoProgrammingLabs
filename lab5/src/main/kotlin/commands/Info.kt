package commands

import Receiver

class Info(private var receiver: Receiver): Command {

    override fun execute(s:String): Result {

            this.receiver.info()
        var res = Result()
        res.success()
        res.setMsg("you got info")
        return res

    }
}