import constituents.Coordinates
import constituents.Person
import java.time.LocalDateTime
import java.util.*

interface ViewMode {
    fun run()
    fun inputId(): Int
    fun info()
    fun help()
    fun readElement(name: String, person: Person): Person
    fun fail(message: String)
    fun setRec(rec: Receiver)
    fun exit()

    fun info(people: Vector<Person>, creationDate: LocalDateTime, stackType: String)
    fun infoProblem()
    fun print(s:String)
    fun show(people: Vector<Person>)

    fun readCoords(coordinates: Coordinates, scanner: Scanner, person: Person)
    fun getPath():String
    abstract fun runScript(fileName: String)


}