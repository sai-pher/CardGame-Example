package CardBasedClasses;

import java.util.Arrays;
import java.util.Objects;

public final class Card {

    private final Suits   SUITS;
    private final Type    TYPE;
    private final int[]   VALUES;
    private final Boolean SPECIAL;


    public Card(final Suits SUITS, final Type TYPE, final int standardValue, final int altValue) {
        this.SUITS = SUITS;
        this.TYPE = TYPE;
        this.VALUES = new int[]{standardValue, altValue};
        this.SPECIAL = TYPE.isSpecial();
    }

    public Card(Suits SUITS, final Type TYPE, final int standardValue) {
        this.SUITS = SUITS;
        this.TYPE = TYPE;
        this.VALUES = new int[]{standardValue, standardValue};
        this.SPECIAL = TYPE.isSpecial();
    }

    public Suits getSUITS() {
        return SUITS;
    }

    public Type getTYPE() {
        return TYPE;
    }

    public int getDefaultValue() {
        return VALUES[0];
    }

    public int getAltValue() {
        return VALUES[1];
    }

    public Boolean isSPECIAL() {
        return SPECIAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return SUITS == card.SUITS &&
               TYPE == card.TYPE &&
               Arrays.equals(VALUES, card.VALUES) &&
               SPECIAL.equals(card.SPECIAL);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(SUITS, TYPE, SPECIAL);
        result = 31 * result + Arrays.hashCode(VALUES);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
               "SUITS=" + SUITS +
               ", TYPE=" + TYPE +
               ", VALUES=" + Arrays.toString(VALUES) +
               ", SPECIAL=" + SPECIAL +
               '}';
    }


}
