package CardBasedClasses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CardTest {

    ArrayList<Card> mockDeck = new ArrayList<>();

    @BeforeAll
    void setUp() {

        for (Suits s : Suits.values()) {
            for (Type type : Type.values()) {

                if (type != Type.STANDARD) {
                    if (type == Type.ACE) {
                        final Card aceCard = new Card(s, type, type.getDefaultValue(), type.getAltValue());
                        mockDeck.add(aceCard);
                    }
                    else {
                        final Card card = new Card(s, type, type.getDefaultValue());
                        mockDeck.add(card);
                    }
                }
                else {
                    final Card card = new Card(s, type, type.getDefaultValue());
                    mockDeck.add(card);
                }

            }
        }
    }


    @Test
    void getSUITS() {

    }

    @Test
    void getTYPE() {
    }

    @Test
    void getVALUES() {
    }

    @Test
    void isSPECIAL() {
    }

    @Test
    void equals1() {
    }

    @Test
    void hashCode1() {
    }

    @Test
    void toString1() {
    }
}