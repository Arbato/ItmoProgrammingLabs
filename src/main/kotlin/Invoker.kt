
import commands.Command
import exceptions.*
import commands.Result

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
    fun Execute(commandName: String): Result {
        val res = commands.Result()
        res.fail()
        res.setMsg("")
        if (commandName.trim() == "") {
            throw InvalidCommand(" ")
        }

        if (commandName == "") {
            return res//throw InvalidCommand("")
        }

        val input = commandName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val command = commandMap[input[0]]
            ?: throw InvalidCommand("Trying to call invalid command, \"$commandName\". To see more info about available commands, enter \"help\"")
        val args = arrayOfNulls<String>(input.size - 1)
        for (i in args.indices) {
            args[i] = input[i + 1]
        }
        try {
            if (args.isNullOrEmpty()){
                //println(command.execute(""))
                return command.execute("")
            } else{
                return command.execute(args[0]!!)
            }

        } catch (ex: WrongNumberOfArgsException) {
            System.err.println(ex.getMesage())
        //}catch(ex: Exception){
         //   System.err.println(ex.message)
        }
        return res

    }
}