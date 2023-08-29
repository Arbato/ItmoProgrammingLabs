import commands.Command
import commands.*
import constituents.Color
import constituents.Person
import exceptions.*

class Invoker {
    private val commandMap: HashMap<String, Command> = HashMap<String, Command>()

    /**
     * Register commands to hashMap
     * @param String?
     */
    fun Register(commandName: String?, command: Command) {
        commandMap[commandName!!] = command
    }

    @Throws(Exception::class)
    fun exec(commandName: String, person: Person?, id: Int?, color: String?, string: String?): Result {
        val res = commands.Result()
        res.fail()
        res.setMsg("")

        if (commandName.trim() == "") {
            return res//throw InvalidCommand("")
        }

        val input = commandName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val command = commandMap[input[0]]
        if (command == null) {
            res.setMsg("Trying to call invalid command, \"$commandName\". To see more info about available commands, enter \"help\"")
            return res
        }
        val args = arrayOfNulls<String>(input.size - 1)
        for (i in args.indices) {
            args[i] = input[i + 1]
        }
        try {
            //if at least one of the args is not null
            if ((person === null && id === null && color === null&& string === null)){
                println("no args")
                return command.execute("")
            } else {
                println("with args")
                return command.execute(person, id, color, string)
            }

        } catch (ex: WrongNumberOfArgsException) {
            System.err.println(ex.getMesage())
            return res

        } catch(ex: Exception){
            System.err.println(ex.message)
            return res
        }
    }
}