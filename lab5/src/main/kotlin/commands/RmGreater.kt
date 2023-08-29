package commands

import Receiver


class RmGreater(private var receiver:Receiver) : Command {

    override fun execute(str: String): Result {
        val res=Result()
        try{
            str[0]?.let { this.receiver.rmGreater(it.toInt())
            res.success()
            res.setMsg("removal completed successfully")}
        } catch (ex: Exception){res.setMsg(ex.message+", fail")
        res.fail()}
        return res
    }
}
