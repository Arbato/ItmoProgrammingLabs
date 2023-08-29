import constituents.Color
import constituents.Person
import exceptions.ValidationException
import java.io.File
import java.lang.Exception
import java.lang.reflect.Field
import java.time.LocalDateTime
import java.util.*

/**
 * Reciever class, works with collection of people
 */
class Receiver {
    public val people: Vector<Person> = Vector<Person>()
    private val creationDate: LocalDateTime = LocalDateTime.now()
    //private var view = view
    private var max_id = 1
    var exit = false
    val RESET = "\u001B[0m"   // Text Reset
    val BLUE = "\u001B[34m"    // BLUE
    val PURPLE = "\u001B[35m"  // PURPLE
    val CYAN = "\u001B[36m"    // CYAN
    val GREEN = "\u001B[32m"
    val YELLOW = "\u001B[33m"
    val RED = "\u001B[31m"

    fun help(): String {
        return (" _____HELP_____\n"
                + CYAN + "info" + RESET + " : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n"
                + CYAN + "show" + RESET + " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"
                + CYAN + "add" + RESET + " {element} : добавить новый элемент в коллекцию\n"
                + CYAN + "update_id" + RESET + " {element} : обновить значение id элемента коллекции \n"
                + CYAN + "remove_by_id" + RESET + " : удалить элемент из коллекции по его id\n"
                + CYAN + "clear" + RESET + " : очистить коллекцию\n"
                + CYAN + "save" + RESET + " {filePath}: сохранить коллекцию в файл\n"
                + CYAN + "execute_script file_name" + RESET + " : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n"
                + CYAN + "exit" + RESET + " : завершить программу (без сохранения в файл)\n"
                + CYAN + "remove_greater" + RESET + " {element} : удалить из коллекции все элементы, превышающие заданный\n"
                + CYAN + "remove_lower" + RESET + " {element} : удалить из коллекции все элементы, меньшие, чем заданный\n"
                + CYAN + "reorder" + RESET + " : отсортировать коллекцию в порядке, обратном нынешнему\n"

                + CYAN + "filter_by_haircolor" + RESET + " hairColor : вывести элементы, значение поля hairColor которых равно заданному\n"
                + CYAN + "print_ascending" + RESET + " : вывести элементы коллекции в порядке возрастания\n"
                + CYAN + "sort_by_hair" + RESET + " : вывести значения поля hairColor всех элементов в порядке убывания\n".trimIndent())
    }

    fun exit() {
        exit = true
    }

    fun getterExit(): Boolean {
        return this.exit
    }

    fun addElement(name: String="") {
        TODO()

        for (i in people){
            if (i.getId() >= max_id){
                max_id = i.getId()+1
            }
        }

        val newbie = Person()
            //view.readElement(name, Person())

        if (newbie != null){
            newbie.setId(max_id)
            people.add(newbie)
            //view.success()
        } else {
            var ex = Exception("your person is null for some reason")
            throw ex
            //view.fail("Adding Person failed!")
        }

    }

    fun pushPerson(p: Person?) {
        this.people.add(p)
    }

    fun show(): String {
        var s = "$PURPLE~~~~~~~~~~~~~~~~~~~~~~~$RESET\n"
        for (i in people) {
            s += i.toString()
        }
        s += "$PURPLE~~~~~~~~~~~~~~~~~~~~~~~$RESET\n"
        return s
    }

    fun info():String {
        try {
            val stackField: Field = Receiver::class.java.getDeclaredField("people")
            val stackType = stackField.genericType.typeName
            if (!people.isEmpty()) {
                return ("$PURPLE<><><><><><><><><><><><><><><><><><><><><><><>$RESET\nA collection of people\n"+
                                "   Type: $YELLOW${people.javaClass.getName()}<$stackType>"+
                                "\n   Creation Date: $creationDate" + "\n   Size: ${people.size}\n$RESET" +
                                "$PURPLE<><><><><><><><><><><><><><><><><><><><><><><>$RESET")
            } else {
                return ("collection is empty")
            }
        } catch (ex: NoSuchFieldException) {
            return ("Problem with general class. Can not find type of class!")
        }
    }


    fun updateId(p:Person, newId: Int){
        if (newId<1){
            throw Exception("ID must be positive!")
        }
        for(p in people){
            if (p.getId() == newId){
                throw Exception("This ID already exists!")
            }
        }
        for(compPerson in people){
            if (p.compareFields(compPerson)){
                compPerson.setId(newId)
                return
            }
        }
        throw Exception("No such peron in list")
    }

    fun removeById(id: Int?) {
        println("removing id")
        var t = true
        try{
            for (p in people){
                if (p.getId() == id){
                    people.remove(p)
                    println("removed id")
                    t = false
                }
            }
        }catch (ex: Exception) {
            println(ex.toString())
        }

        //if (t){
         //   throw ValidationException("item not found")
        //}
    }

    fun clear(){
        people.removeAllElements()
    }

    fun saveToOther(path:String){
        try {
            saveToDataBase(path, people)
        }
        catch (ex: Exception) {
            throw Exception("Try again, check your path.")
        }

    }
    fun rmGreater(i: Int){
        var collector : Vector<Person> = Vector<Person>()
        for(p in people){
            if(i < p.getId()){
                collector.add(p)
            }
        }
        people.removeAll(collector)
    }


    fun rmLesser(i: Int){
        var collector : Vector<Person> = Vector<Person>()
        for(p in people){
            if(i > p.getId()){
                collector.add(p)
            }
        }
        people.removeAll(collector)
    }

    fun fHairColor(color: Color): String{
        var s = ""
        for(p in people){
            if (p.getHairColor() === color){
                s+= p.toString()+"\n"
            }
        }
        return s
    }

    fun decByHairColor(): String{
        people.sort()
        var s = ""
        val it: Iterator<Person> = people.iterator()
        while (it.hasNext()) {
            s += it.next().toString()
        }
        return s
    }

    var byColor: Comparator<Person> = Comparator {person1: Person, person2: Person ->
        Integer.compare(
            person1.getHairColor()!!.ordinal,
            person2.getHairColor()!!.ordinal
        )
    }

    fun hairComparator():String{
        people.sortWith(byColor)
        val it: Iterator<Person> = people.iterator()
        var s = ""
        while (it.hasNext()) {
            s+=it.next().toString()
        }
        return s
    }

    fun executeScript(contents: String){

        TODO()
        """
        view.runScript(fileName)
        """
    }

    fun reorder(){
        people.reverse()
    }
}

