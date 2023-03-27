package commands

//for command without arg
interface Command : SuperCommand{

    /**
     * Method to interact with Collection Manager.
     * @param args - the arguments that are passed to command
     * @throws Exception
     */
    @Throws(Exception::class)
    fun execute(str:String): Result
}