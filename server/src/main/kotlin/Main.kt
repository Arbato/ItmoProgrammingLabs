import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.datatype.*
import com.fasterxml.jackson.datatype.jdk8.*
import com.fasterxml.jackson.datatype.jsr310.*
import com.fasterxml.jackson.module.*
import commands.*
import constituents.*
import inputstufs.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants.START_ELEMENT
import javax.xml.stream.XMLStreamReader

fun main(args: Array<String>) {
    println("oh sheet it works")
    var arr = ByteArray(30000)
    var len = arr.size
    val ds: DatagramSocket
    var dp: DatagramPacket
    var host: InetAddress
    var port = 6789
    var res: Result
    var person: Person? = null
    var id: Int? = null
    var color: String? = null
    var string: String? = null


    var elCommands: HashSet<String> = hashSetOf<String>("add", "update_id", "remove_by_id")
    var intCommands: HashSet<String> = hashSetOf<String>("update_id", "remove_by_id", "remove_greater", "remove_lower")
    var colorCommands: HashSet<String> = hashSetOf<String>("filter_by_haircolor")
    var strCommands: HashSet<String> = hashSetOf<String>("execute_script")

    val receiver = Receiver()

    if (args.size != 1) {
        println("collection is empty")
    }
    try {
        val fileName = args[0]
        loadDataBase(fileName, receiver)
    } catch (ex: IOException){
        println("whoops: IOE")
        println(ex.message)
    }

    val invoker = Invoker()
    val outputManager = Output(System.out)
    val inputManager = Input(System.`in`, outputManager)

    // creates a hash map of commands to their command line names
    registerCommands(receiver, invoker,inputManager)

    ds = DatagramSocket(port)
    dp = DatagramPacket(arr, len)
    ds.receive(dp)
    host = dp.getAddress()
    port = dp.getPort()
    //dp = DatagramPacket(arr, len)
    //ds.send(dp)

    var arr1 = hashSetToByteArray(elCommands)
    var arr2 = hashSetToByteArray(intCommands)
    var arr3 = hashSetToByteArray(colorCommands)
    var arr4= hashSetToByteArray(strCommands)
    dp = DatagramPacket(arr1, arr1.size, host, port)
    ds.send(dp)
    dp = DatagramPacket(arr2, arr2.size, host, port)
    ds.send(dp)
    dp = DatagramPacket(arr3, arr3.size, host, port)
    ds.send(dp)
    dp = DatagramPacket(arr4, arr4.size, host, port)
    ds.send(dp)

    var msg = ""
    while (msg != "exit") {
        ds.receive(dp)
        msg = String(dp.getData(), dp.getOffset(), dp.getLength())
        println(msg)
        if (msg in elCommands){
            // get person from client
            val arr = ByteArray(30000)
            dp = DatagramPacket(arr, len)
            ds.receive(dp)
            println(String(dp.getData(), dp.getOffset(), dp.getLength()))
            person = xmlToPerson(String(dp.getData(), dp.getOffset(), dp.getLength()))
        }
        if (msg in intCommands){
            // get int from client
            val arr = ByteArray(4)
            dp = DatagramPacket(arr, arr.size)
            ds.receive(dp)
            //val wrapped: ByteBuffer = ByteBuffer.wrap(dp.data) // big-endian by default
            //val num: Int = wrapped.getShort().toInt() // 1
            var num = String(dp.getData(), dp.getOffset(), dp.getLength())
            var intValue = num.toInt()
            println(intValue)
            id = intValue
        }

        if (msg in colorCommands){
            // get haircolor from client
            val arr = ByteArray(30000)
            dp = DatagramPacket(arr, len)
            ds.receive(dp)
            color = String(dp.getData(), dp.getOffset(), dp.getLength())
        }

        res = invoker.exec(msg, person, id, color, string)
        color = null
        person = null
        id = null
        println(res.getMessage())
        println(res.toString())
        arr = toByteArr(res)

        println(arr.size)

        host = dp.getAddress()
        port = dp.getPort()
        dp = DatagramPacket(arr, arr.size, host, port)
        ds.send(dp)
    }
    println("exited")
}


fun newXmlMapper(): XmlMapper {
    val mapper = XmlMapper()
    mapper.registerModule(JavaTimeModule())
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    return mapper
}
fun xmlToPerson(s:String): Person {
    val xm = newXmlMapper()
    val res: Person = xm.readValue(s, Person::class.java)
    return res
}



fun toByteArr(res: Any): ByteArray{
    val objmapper = XmlMapper()
    objmapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true)
    println(objmapper.writeValueAsString(res))
    return objmapper.writeValueAsString(res).toByteArray()
}
fun registerCommands(receiver: Receiver, invoker: Invoker, input: Input){
    invoker.Register("help", Help(receiver))
    invoker.Register("exit", Exit(receiver))
    invoker.Register("add", Add(receiver))
    invoker.Register("show", Show(receiver))
    invoker.Register("info", Info(receiver))
    invoker.Register("update_id", UpdateId(receiver))
    invoker.Register("remove_by_id", RemoveId(receiver))

    invoker.Register("clear", Clear(receiver))
    invoker.Register("remove_greater", RmGreater(receiver))
    invoker.Register("remove_lower", RmLesser(receiver))
    invoker.Register("filter_by_haircolor", FHairColor(receiver))
    invoker.Register("save", Save(receiver))
    invoker.Register("sort_by_haircolor",RealSortByHair(receiver))
    invoker.Register("print_ascending", DecByHairColor(receiver))
    invoker.Register("reorder", Reorder(receiver))
    invoker.Register("execute_script", ExecuteScript(receiver, input))
}

//function that gets an object from client


fun loadDataBase(fileName: String, receiver:Receiver){
    val xm = newXmlMapper()
    val xif: XMLInputFactory = XMLInputFactory.newInstance()
    val xr: XMLStreamReader = xif.createXMLStreamReader(FileInputStream(fileName).bufferedReader())

    // you must to read step by step
    while (xr.hasNext()) {
        xr.next()
        if (xr.getEventType() === START_ELEMENT) {
            if (xr.getLocalName() == "Person") {
                val person: Person = xm.readValue(xr, Person::class.java)
                receiver.pushPerson(person)
            }
        }
    }
}

fun saveToDataBase(filePath: String, people: Vector<Person>) {
    val objmapper = newXmlMapper()
    var file = File(filePath).bufferedWriter()
    file.write("<Persons>\n")
    for (i in people) {
        file.write(objmapper.writeValueAsString(i))
    }
    file.write("</Persons>\n")
    file.close()
}

fun hashSetToByteArray(set: HashSet<String>): ByteArray {
    val byteList = mutableListOf<Byte>()
    for (str in set) {
        byteList.addAll(str.toByteArray().asList())
        byteList.add(0)
    }
    return byteList.toByteArray()
}