
import constituents.*
import exceptions.InvalidCommand
import exceptions.ValidationException
import inputstufs.Input
import inputstufs.Output
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * class that works with terminal.
 * @param Input
 * @param Output
 */

class TerminalView(inputManager:Input, outputManager:Output):ViewMode {
    lateinit var receiver: Receiver
    val invoker = Invoker()
    var inputManager = inputManager
    var outputManager = outputManager

    val RESET = "\u001B[0m"   // Text Reset
    val BLUE = "\u001B[34m"    // BLUE
    val PURPLE = "\u001B[35m"  // PURPLE
    val  CYAN = "\u001B[36m"    // CYAN
    val GREEN = "\u001B[32m"
    val YELLOW = "\u001B[33m"
    val RED = "\u001B[31m"
    var scanner = Scanner(System.`in`)

    /**
     * actually runs the program
     */

    override fun run(){
        while (!receiver.getterExit() && scanner.hasNext()) {
            try {
                println(invoker.Execute(readUserInput(scanner)).toString())
            } catch (ex: InvalidCommand) {
                System.err.println(ex.message)
            }
        }
        scanner.close()
    }

    /**
     * runs script
     * @param String
     */
    fun runScript(fileName: String) {
        outputManager.muteNotifications()
        inputManager.scriptMode = true
        var old = scanner
        if (File(fileName) in inputManager.files){
            println("recursion not allowed")
            return
        }
        inputManager.files.add(File(fileName))
        scanner = Scanner(File(fileName).inputStream())
        try {
            this.run()
        } finally {
            scanner = old
            inputManager.scriptMode = false
            inputManager.files.remove(File(fileName))
            outputManager.enableNotifications()
        }
    }

    override fun setRec(rec: Receiver) {
        receiver = rec
    }


    override fun exit(){
        println(BLUE+ "Goodbye! See you soon!"+RESET)
        scanner.close()
    }

    override fun inputId(): Int{
        println("New id:")
        var nextValue = scanner.nextLine()
        return nextValue.toInt()
    }

    override fun info() {
        println("Hello!")
        println("Enter "+CYAN+"help"+RESET+" to get help!")
    }

    fun readUserInput(userInput: Scanner): String {
        return userInput.nextLine()
    }

    override fun help() {
        println(" _____HELP_____\n"
                +CYAN+"info"+RESET+" : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n"
                +CYAN+"show"+RESET+" : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"
                +CYAN+"add"+RESET+" {element} : добавить новый элемент в коллекцию\n"
                +CYAN+"update_id"+RESET+" {element} : обновить значение id элемента коллекции \n"
                +CYAN+"remove_by_id"+RESET+" : удалить элемент из коллекции по его id\n"
                +CYAN+"clear"+RESET+" : очистить коллекцию\n"
                +CYAN+"save"+RESET+" {filePath}: сохранить коллекцию в файл\n"
                +CYAN+"execute_script file_name"+RESET+" : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме\n"
                +CYAN+"exit"+RESET+" : завершить программу (без сохранения в файл)\n"
                +CYAN+"remove_greater"+RESET+" {element} : удалить из коллекции все элементы, превышающие заданный\n"
                +CYAN+"remove_lower"+RESET+" {element} : удалить из коллекции все элементы, меньшие, чем заданный\n"
                +CYAN+"reorder"+RESET+" : отсортировать коллекцию в порядке, обратном нынешнему\n"
                
                +CYAN+"filter_by_haircolor"+RESET+" hairColor : вывести элементы, значение поля hairColor которых равно заданному\n"
                +CYAN+"print_ascending"+RESET+" : вывести элементы коллекции в порядке возрастания\n"
                +CYAN+"sort_by_hair"+RESET+" : вывести значения поля hairColor всех элементов в порядке убывания\n".trimIndent())
    }


    /**
     * asks user for element
     */
    override fun readElement(name: String, person: Person): Person { //throws ValidationException {
        var name = name
        val coordinates = Coordinates()
        val location = Location()
        var nextValue = String()
        person.setName(name)

        if (inputManager.scriptMode){while (person.getName() == null || person.getName()=="") {
            name = scanner.nextLine()
            person.setName(name)
        }
            person.setName(name)


            while (person.getCoordinates() == null) {
                try {
                    readCoords(coordinates, scanner, person)
                }catch (ex: ValidationException) {
                    System.err.println(ex.message)
                } catch (ex: NumberFormatException) {
                    System.err.println(ex.message+"." +
                            " Wrong, start coordinates over again!")
                }
            }
            while (person.getBirthsday() == null){
                try{
                    nextValue = scanner.nextLine()
                    if (nextValue == ""){
                        break
                    }
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    val date: java.time.LocalDate = LocalDate.parse(nextValue, formatter)
                    person.setBirthsday(date.atStartOfDay(ZoneId.systemDefault()))}

                catch(ex: Exception){
                    println("Format is DD-MM-YYYY!")
                    System.err.println(ex.message)
                }
            }

            while (person.getHeight() == 0.toLong()){
                try{
                    nextValue = scanner.nextLine()
                    if (nextValue == ""){
                        break
                    }
                    person.setHeight(nextValue.toLong())

                }
                catch(ex:Exception){
                    System.err.println(ex.message)
                }
            }

            while (person.getHairColor() == null){
                try{
                    nextValue = scanner.nextLine()
                    if (nextValue == ""){break}
                    person.setHairColor(Color.valueOf(nextValue))
                }
                catch(ex:Exception){
                    System.err.println(ex.message)
                }
            }
            while (person.getNationality() == null){
                try{
                    nextValue = scanner.nextLine()
                    if (nextValue == ""){break}
                    person.setNationality(Country.valueOf(nextValue))                }
                catch(ex:Exception){
                    System.err.println(ex.message)
                }
            }
            while (person.getLocation() == null){
                try{
                    if (nextValue == ""){break}
                    nextValue = scanner.nextLine()
                    location.setX(nextValue.toLong())
                    nextValue = scanner.nextLine()
                    location.setX(nextValue.toLong())
                    nextValue = scanner.nextLine()
                    if (nextValue.toString() == "y") {
                        nextValue = scanner.nextLine()
                        location.setY(nextValue.toFloat())
                    }
                    person.setLocation(location)
                }
                catch(ex:Exception){
                    System.err.println(ex.message)
                }
            }

        } else {

        while (person.getName() == null || person.getName()=="") {
            println("Enter person's name:")
            name = scanner.nextLine()
            person.setName(name)
        }
        person.setName(name)

        while (person.getCoordinates() == null) {
            try {
                readCoords(coordinates, scanner, person)
            }catch (ex: ValidationException) {
                System.err.println(ex.message)
            } catch (ex: NumberFormatException) {
            System.err.println(ex.message+"." +
                    " Wrong, start coordinates over again!")

        }
        }
        while (person.getBirthsday() == null){
            try{
                println("Enter the DOB of the person: dd-mm-yyyy (or press enter to skip)")
                nextValue = scanner.nextLine()
                if (nextValue == ""){
                    break
                }
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val date: java.time.LocalDate = LocalDate.parse(nextValue, formatter)
                person.setBirthsday(date.atStartOfDay(ZoneId.systemDefault()))}

            catch(ex: Exception){
                println("Format is DD-MM-YYYY!")
                System.err.println(ex.message)
            }
        }

        while (person.getHeight() == 0.toLong()){
            try{
                println("Enter the person's height (or press enter to skip):")
                nextValue = scanner.nextLine()
                if (nextValue == ""){
                    break
                }
                person.setHeight(nextValue.toLong())

            }
            catch(ex:Exception){
                System.err.println(ex.message)
            }
        }

        while (person.getHairColor() == null){
            try{
                println("Enter the hair color, GREEN, BLUE or ORANGE (or press enter to skip):")
                nextValue = scanner.nextLine()
                if (nextValue == ""){break}
                person.setHairColor(Color.valueOf(nextValue))
            }
            catch(ex:Exception){
                System.err.println(ex.message)
            }
        }
        while (person.getNationality() == null){
            try{
                println("Enter the nationality, SPAIN, THAILAND or JAPAN (or press enter to skip):")
                nextValue = scanner.nextLine()
                if (nextValue == ""){break}
                person.setNationality(Country.valueOf(nextValue))                }
            catch(ex:Exception){
                System.err.println(ex.message)
            }
        }
        while (person.getLocation() == null){
            try{        println("Enter the (x,y(optional),z) (or press enter to skip):")
                println("Enter x:")
                if (nextValue == ""){break}
                nextValue = scanner.nextLine()
                location.setX(nextValue.toLong())
                println("Enter z:")
                nextValue = scanner.nextLine()
                location.setX(nextValue.toLong())
                println("Do you want to enter y? y/n:")
                nextValue = scanner.nextLine()
                if (nextValue.toString() == "y") {
                    println("Enter y:")
                    nextValue = scanner.nextLine()
                    location.setY(nextValue.toFloat())
                }
                person.setLocation(location)
            }
            catch(ex:Exception){
                System.err.println(ex.message)
            }
        }
        person.setId(Person.idKeeeper())
        }
        return person
    }

    override fun info(people: Vector<Person>, creationDate: LocalDateTime, stackType: String){

            println("$PURPLE<><><><><><><><><><><><><><><><><><><><><><><>$RESET\nA collection of people")
            println(
                "   Type: $YELLOW${people.javaClass.getName()}<$stackType>"+ "\n   Creation Date: $creationDate" + "\n   Size: ${people.size}\n$RESET" +
                        "$PURPLE<><><><><><><><><><><><><><><><><><><><><><><>$RESET")
    }

    override fun infoProblem(){
        println("""Type can not be defined because collection is empty!""")
    }

    override fun print(s:String){
        println(s)
    }

    override fun show(people: Vector<Person>) {
        println("$PURPLE~~~~~~~~~~~~~~~~~~~~~~~$RESET\n")
        for (i in people) {
            println(i.toString())
        }
        println("$PURPLE~~~~~~~~~~~~~~~~~~~~~~~$RESET\n")
    }

    override fun readCoords(coordinates: Coordinates, scanner: Scanner, person: Person) {
        println("Enter the coordinate x of coordinates:")
        var nextValue = scanner.nextLine()
        coordinates.setX(nextValue.toDouble())
        println("Enter the coordinate y of coordinates:")
        nextValue = scanner.nextLine()
        coordinates.setY(nextValue.toInt())
        person.setCoordinates(coordinates)
    }

    override fun getPath():String {
        println("Enter the path to the file where you want to save the data.")
        return scanner.nextLine()
    }


    override fun fail(message: String){
        println(RED+"Fail,"+message+RESET)
    }

   fun noFile(){
        println(RED+"Программа принимает единственный обязательный аргумент - путь к файлу."+RESET)
    }
}