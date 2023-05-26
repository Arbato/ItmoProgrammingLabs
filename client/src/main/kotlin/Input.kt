import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*



/**
 * responsible for input
 * @author kseniya
 */
class Input(inputStream: InputStream?, outputManager: Output) {
    public val scanners = Stack<Scanner>()
    public val files = Stack<File>()

    /**
     * @return true if input manager is reading from file now, and false otherwise
     */
    public var scriptMode = false

    private val outputManager: Output

    init {
        scanners.push(Scanner(inputStream))
        this.outputManager = outputManager
    }

    /**
     * reads one line
     * @return the line that was read
     */
    @Throws(InvalidCommand::class)
    fun read(): String {
        if (scanners.peek().hasNextLine()) {
            return scanners.peek().nextLine()
        } else {
            if (scriptMode) {
                println("asdfdsa")
                finishReadScript()
                outputManager.printlnMessage("Reached the end of the file")
            } else {
                throw InvalidCommand("Invalid Command")
            }
        }
        return ""
    }

    /**
     * starts reading from the file if there is no recursion, otherwise print a message about the detected recursion
     * @param fileName
     */
    fun startReadScript(fileName: String?) {
        val scriptFile = File(fileName)
        if (files.contains(scriptFile)) {
            outputManager.printlnImportantColorMessage(
                "Recursion detected in file " + files.peek().name
                        + ". The script " + scriptFile.name + " will not be executed twice!"
            )
        } else {
            try {
                outputManager.println("Start reading from file " + scriptFile.name + "...")
                scanners.push(Scanner(scriptFile))
                files.push(scriptFile)
                scriptMode = true
                outputManager.muteNotifications()
            } catch (e: IOException) {
                outputManager.printlnImportantColorMessage("Cannot find file " + scriptFile.name)
            }
        }

        fun getScriptMode():Boolean{
            return scriptMode
        }

    }

    /**
     * finish read from the file and starts read from input stream that set in the constructor
     */
    fun finishReadScript() {
        if (scriptMode) {
            if (scanners.size == 2) {
                scriptMode = false
                outputManager.enableNotifications()
            }
            scanners.pop()
            outputManager.println("Reading from file " + files.pop().name + " was finished")
        }
    }

    fun getScriptMod(): Boolean {
        return scriptMode
    }
}