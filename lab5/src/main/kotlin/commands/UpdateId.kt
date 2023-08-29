package commands

import Receiver
import exceptions.WrongNumberOfArgsException

class UpdateId(private var receiver: Receiver): Command {
    override fun execute(s:String): Result {
            if (s.isNotEmpty()) {
                throw WrongNumberOfArgsException("Wrong number of arguments! See more info about available commands. \"help\" ")
            } else {
                this.receiver.updateId()
            }
        var res = Result()
        res.success()
        res.setMsg("exit completed")
        return res
        }
    }
