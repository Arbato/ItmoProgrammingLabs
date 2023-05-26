package commands

import constituents.Person
import java.net.InetAddress

interface SuperCommand {
    fun execute(str: String?){}
    fun execute(str: String?, i: InetAddress, port:Int){}

}