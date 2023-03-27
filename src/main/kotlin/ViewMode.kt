import constituents.Person

interface ViewMode {
    fun run()
    fun inputId(): Int
    fun info()
    fun help()
    fun readElement(name: String, person: Person): Person
    fun fail(message: String)




}