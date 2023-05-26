package commands

import constituents.Person

//for command without arg
interface Command : SuperCommand{

    /**
     * Method to interact with Collection Manager.
     * @param args - the arguments that are passed to command
     * @throws Exception
     */
    @Throws(Exception::class)
    fun execute(s: String): Result
    fun execute(person: Person?, id:Int?, color:String?, string: String?):Result

}