package CardBasedClasses;

import java.util.Arrays;
import java.util.Objects;

public final class Card {

    private final Suits   suits;
    private final Type    type;
    private final int[]   values;
    private final Boolean special;


    public Card(final Suits suits, final Type type, final int standardValue, final int altValue) {
        this.suits = suits;
        this.type = type;
        this.values = new int[]{standardValue, altValue};
        this.special = type.isSpecial();
    }

    public Card(Suits suits, final Type type, final int standardValue) {
        this.suits = suits;
        this.type = type;
        this.values = new int[]{standardValue, standardValue};
        this.special = type.isSpecial();
    }

    public Suits getSuits() {
        return suits;
    }

    public Type getType() {
        return type;
    }

    public int getDefaultValue() {
        return values[0];
    }

    public int getAltValue() {
        return values[1];
    }

    public Boolean isSpecial() {
        return special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return suits == card.suits &&
               type == card.type &&
               Arrays.equals(values, card.values) &&
               special.equals(card.special);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(suits, type, special);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public String toString() {

        if (this.type == Type.STANDARD)
            return String.format("<--%s of %s--> ", getDefaultValue(), suits);
        else
            return String.format(" <> %s of %s <> ", type, suits);
    }


}
