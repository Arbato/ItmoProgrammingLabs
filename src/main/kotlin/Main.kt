import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.*
import com.fasterxml.jackson.datatype.jdk8.*
import com.fasterxml.jackson.datatype.jsr310.*
import com.fasterxml.jackson.module.*
import commands.*
import constituents.Person
import exceptions.InvalidCommand
import inputstufs.*
import org.codehaus.jackson.map.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants.START_ELEMENT
import javax.xml.stream.XMLStreamReader

/**
* @author Kseniya Arbatova
* @version 2
*/

fun main(args: Array<String>) {
    val outputManager = Output(System.out)
    val inputManager = Input(System.`in`, outputManager)

    // Create View Object
    val view = TerminalView(inputManager, outputManager)

    // Create controller object
    val receiver = Receiver(view)

    // creates a hash map of commands to their command line names
    registerCommands(receiver, view.invoker, inputManager)

    // default file to get data from
    // if a file is given, try loading data from file
    if (args.size != 1) {
        view.noFile()
        return
    }
        try {
            val fileName = args[0]
            loadDataBase(fileName, receiver)
        } catch (ex: IOException){
            println("whoops: IOE")
            println(ex.message)
        }

    // get commands until exit is entered.
    view.info()
    view.run()
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

fun newXmlMapper(): XmlMapper {
    val mapper = XmlMapper()
    mapper.registerModule(JavaTimeModule())
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    return mapper
}

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


