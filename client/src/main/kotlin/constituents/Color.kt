package constituents

enum class Color(val str: String) {
    GREEN("GREEN"), BLUE("BLUE"), ORANGE("ORANGE");
    private var color: String? = null

    open fun Color(name: String?) {
        color = name
    }

    open fun getName(): String? {
        return color
    }
}