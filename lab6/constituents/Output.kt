package inputstufs

import java.io.IOException
import java.io.OutputStream

/**
 * responsible for output
 *  * @author kseniya
 */
class Output(private val outputStream: OutputStream) {
    private var messageNotifications = MessageNotifications.ON

    private enum class MessageNotifications {
        ON, OFF
    }

    fun println(string: String) {
        if (messageNotifications == MessageNotifications.ON) {
            printlnImportantMessage(string)
        }
    }

    fun printlnMessage(string: String) {
        if (messageNotifications == MessageNotifications.ON) {
            printlnImportantColorMessage(string)
        }
    }

    fun printlnImportantColorMessage(string: String) {
        printlnImportantMessage( string )
    }

    /**
     * writes a string with a new string symbol in the end to the output stream that set in the constructor even if notification is off
     * @param string
     */
    fun printlnImportantMessage(string: String) {
        try {
            outputStream.write(string.toByteArray())
            outputStream.write("\n".toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * turn off notification; messages will be not written until you turn on notification
     */
    fun muteNotifications() {
        messageNotifications = MessageNotifications.OFF
    }

    /**
     * turn on notification
     */
    fun enableNotifications() {
        messageNotifications = MessageNotifications.ON
    }
}