package constituents

import ValidationException

class Coordinates {
    private var x = 0.0 //Максимальное значение поля: 245
    private var y = 0 //Значение поля должно быть больше -851
    fun getX(): Double {
        return x
    }

    @Throws(ValidationException::class)
    fun setX(x: Double) {
        if (x <= 245) {
            this.x = x
        } else {
            throw ValidationException("x must be less than 245!")
        }
    }

    fun getY(): Int {
        return y
    }

    @Throws(ValidationException::class)
    fun setY(y: Int) {
        if (y > -851) {
            this.y = y
        } else {
            throw ValidationException("y must be more than -851!")
        }
    }

    /**
     * Method which compares coordinates with each other.
     * @param anotherCoordinates - another coordinates.
     * @return
     */
    operator fun compareTo(anotherCoordinates: Coordinates): Int {
        return (x - anotherCoordinates.x).toInt()
    }

    override fun toString(): String {
        return "Coordinates [x = $x, y = $y] "
    }
}