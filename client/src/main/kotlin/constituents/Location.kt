package constituents

class Location {


    private var x: Long? = null //Поле не может быть null

    private var y = 0f
    private var z: Long? = null //Поле не может быть null


    fun getX(): Long? {
        return x
    }

    fun setX(x: Long?) {
        this.x = x
    }

    fun getY(): Float {
        return y
    }

    fun setY(y: Float) {
        this.y = y
    }

    fun getZ(): Long? {
        return z
    }

    fun setZ(z: Long?) {
        this.z = z
    }
}