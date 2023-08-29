import java.net.*

fun main(args: Array<String>) {
    println("Waiting for server...")
    val outputManager = Output(System.out)
    val inputManager = Input(System.`in`, outputManager)

    // Create View Object (it also sends the commands to server)
    val view = TerminalView(inputManager, outputManager)
    view.info()
    view.initial()
    view.run()
}