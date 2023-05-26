package commands


//for commands with arg
interface CommandId: SuperCommand {
    /**
     * Method to interact with Collection Manager.
     * @param args - the arguments that are passed to command
     * @throws Exception
     */
    @Throws(Exception::class)
    fun Execute(str: String)
}