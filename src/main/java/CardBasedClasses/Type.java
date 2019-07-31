package CardBasedClasses;

public enum Type {

    STANDARD(10, false, 0, 0),
    JOKER(1, false, 10, 10),
    QUEEN(1, false, 10, 10),
    KING(1, false, 10, 10),
    ACE(1, true, 11, 1);

    private final int     number;
    private final boolean special;
    private final int     defaultValue;
    private final int     altValue;

    Type(int number, boolean special, int defaultValue, int AltValue) {
        this.number = number;
        this.special = special;
        this.defaultValue = defaultValue;
        altValue = AltValue;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSpecial() {
        return special;
    }


    public int getDefaultValue() {
        return defaultValue;
    }

    public int getAltValue() {
        return altValue;
    }
}
