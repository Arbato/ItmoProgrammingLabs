package constituents

import exceptions.ValidationException
import java.time.LocalDateTime
import java.util.*


class Person(): Comparable<Person> {
    private var id = 1
    private var name: String? = null
    private var coordinates: Coordinates? = null
    private var creationDate: LocalDateTime? = null
    private var height: Long? = null
    private var birthsday: java.time.ZonedDateTime? = null
    private var location: Location? = null
    private var hairColor: Color? = null
    private var nationality: Country? = null

    init {
        creationDate = LocalDateTime.now()
    }


    companion object{
        var lastIdPerson = 0
        fun  idKeeeper(): Int {
            lastIdPerson++
            return lastIdPerson
        }
    }
    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCoordinates(): Coordinates? {
        return coordinates
    }

    fun setCoordinates(coordinates: Coordinates?) {
        this.coordinates = coordinates
    }

    fun getCreationDate(): LocalDateTime? {
        return creationDate
    }

    fun setCreationDate(creationDate: LocalDateTime?) {
        this.creationDate = creationDate
    }

    fun getHeight(): Long? {
        return height
    }

    @Throws(ValidationException::class)
    fun setHeight(height: Long?) {

        if ((height == null) || (height > 0)) {
            this.height = height
        } else {
            throw ValidationException("Height must be > 0!")
        }
    }

    fun getBirthsday(): java.time.ZonedDateTime? {
        return birthsday
    }

    fun setBirthsday(birthsday: java.time.ZonedDateTime?) {
        this.birthsday = birthsday
    }

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location?) {
        this.location = location
    }

    fun getHairColor(): Color? {
        return hairColor
    }

    fun setHairColor(hairColor: Color?) {
        this.hairColor = hairColor
    }

    fun getNationality(): Country? {
        return nationality
    }

    fun setNationality(nationality: Country?) {
        this.nationality = nationality
    }

    override fun compareTo(other: Person): Int {
        if (this.id > other.getId()) return 1
        return -1
    }

    override fun toString(): String {
        return "Person id: " + this.id.toString() +
                "\n  Name: "+this.name +
                "\n  Coordinates: " + this.coordinates.toString() +
                "\n  Created: "+ this.creationDate.toString() +
                "\n  Height: "+ this.height.toString() +
                "\n  Born: "+this.birthsday.toString() +
                "\n  Located: "+ this.location.toString() +
                "\n  Hair color: " + this.hairColor +
                "\n ----------"
    }

    fun compareFields(other: Any?): Boolean {
        if(other is Person){
                if (other.getName() == this.getName()){

                    if (other.getNationality() == this.getNationality()){

                        if (other.getHairColor() == this.getHairColor()){

                            if(other.getHeight() == this.getHeight()) {

                                if (other.getBirthsday() == this.getBirthsday()) {
                                    return true

                                }
                            }
                        }
                }
            }
        }
        return false//other.getCoordinates() == this.getCoordinates()
    }

}