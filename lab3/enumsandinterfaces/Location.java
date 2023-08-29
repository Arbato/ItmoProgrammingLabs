package enumsandinterfaces;

public enum Location {
    INFRONTOF("Перед"),
    BEHIND("позади"),
    BETWEEN("между"),
    UNDER("под"),
    IN("в"),
    OVER("над"),
    ON("на");

    private final String location;

    Location(String location)
    {
        this.location = location;
    }

    public String getName() {
        return this.location;
    }
}
