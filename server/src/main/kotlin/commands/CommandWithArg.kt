import commands.SuperCommand
import java.net.InetAddress

//for commands with arg
//
interface CommandWithArg : SuperCommand {
    override fun execute(str: String?, i: InetAddress, port:Int){}
}
