package commands

import Receiver
import constituents.Person
import exceptions.RecurrentExecuteScripts
import exceptions.ValidationException
import exceptions.WrongNumberOfArgsException
import inputstufs.Input
import java.io.FileNotFoundException


class ExecuteScript(private var receiver: Receiver, input: Input) : Command {
    private var input = input

    @Throws(ValidationException::class, WrongNumberOfArgsException::class)
    override fun execute(s: String):Result {
            val filepath = s
            val res = Result()
            if (filepath == "") {
                res.setMsg("no file passed in after command")
                res.fail()
                return res
            }

            try {
                receiver.executeScript(filepath)
                res.setMsg("execution completed successfully")
                res.success()

            } catch (e: FileNotFoundException) {
                res.setMsg("file not found")
                res.fail()

            } catch (e: RecurrentExecuteScripts) {
                res.setMsg("Skipped a call of a script")
                res.fail()
            }
        return res
        }

    override fun execute(person: Person?, id:Int?, color:String?, string: String?):Result{
        TODO()
    }
}
