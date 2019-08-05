package CardBasedClasses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    private static ArrayList<Card> mockDeck = new ArrayList<>();

    @BeforeAll
    static void setUp() {

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

        System.out.println(mockDeck);
    }


    @Test
    void getSUITS() {
        int clubCards    = 0;
        int spadeCards   = 0;
        int diamondCards = 0;
        int heartCards   = 0;

        for (Card c : mockDeck) {
            if (c.getSUITS() == Suits.CLUB)
                clubCards += 1;
            else if (c.getSUITS() == Suits.SPADE)
                spadeCards += 1;
            else if (c.getSUITS() == Suits.DIAMOND)
                diamondCards += 1;
            else if (c.getSUITS() == Suits.HEART)
                heartCards += 1;
        }

        assertEquals(5, clubCards, "Club cards should be 5");
        assertEquals(5, spadeCards, "Spade cards should be 5");
        assertEquals(5, diamondCards, "Diamond cards should be 5");
        assertEquals(5, heartCards, "Heart cards should be 5");
    }

    @Test
    void getTYPE() {
        int standardCards = 0;
        int jokerCards    = 0;
        int queenCards    = 0;
        int kingCards     = 0;
        int aceCards      = 0;

        for (Card c : mockDeck) {
            if (c.getTYPE() == Type.STANDARD)
                standardCards += 1;
            else if (c.getTYPE() == Type.JOKER)
                jokerCards += 1;
            else if (c.getTYPE() == Type.QUEEN)
                queenCards += 1;
            else if (c.getTYPE() == Type.KING)
                kingCards += 1;
            else if (c.getTYPE() == Type.ACE)
                aceCards += 1;
        }

        assertEquals(4, standardCards, "Club cards should be 4");
        assertEquals(4, jokerCards, "Spade cards should be 4");
        assertEquals(4, queenCards, "Diamond cards should be 4");
        assertEquals(4, kingCards, "Heart cards should be 4");
        assertEquals(4, aceCards, "Heart cards should be 4");
    }

    @Test
    void getVALUES() {
        for (Card c : mockDeck) {
            assertEquals(c.getTYPE().getDefaultValue(), c.getDefaultValue(),
                         "Cards default value should match types' value.");
            if (c.getTYPE() == Type.ACE)
                assertEquals(c.getTYPE().getAltValue(), c.getAltValue(), "Cards alt value should match types' value.");
            else
                assertEquals(c.getTYPE().getDefaultValue(), c.getAltValue(),
                             "Cards alt value should match types' value.");

        }
    }

    @Test
    void isSPECIAL() {
        int clubCards    = 0;
        int spadeCards   = 0;
        int diamondCards = 0;
        int heartCards   = 0;

        for (Card c : mockDeck) {
            if (c.getSUITS() == Suits.CLUB) {
                if (c.isSPECIAL())
                    clubCards += 1;
            }
            else if (c.getSUITS() == Suits.SPADE) {
                if (c.isSPECIAL())
                    spadeCards += 1;
            }
            else if (c.getSUITS() == Suits.DIAMOND) {
                if (c.isSPECIAL())
                    diamondCards += 1;
            }
            else if (c.getSUITS() == Suits.HEART) {
                if (c.isSPECIAL())
                    heartCards += 1;
            }
        }

        assertEquals(1, clubCards, "Club cards should have one special card");
        assertEquals(1, spadeCards, "Spade cards should have one special card");
        assertEquals(1, diamondCards, "Diamond cards should have one special card");
        assertEquals(1, heartCards, "Heart cards should have one special card");
    }

    @Test
    void equals1() {
        for (int i = 0; i < mockDeck.size(); i++) {
            for (int j = 0; j < mockDeck.size(); j++) {
                if (i == j)
                    assertEquals(mockDeck.get(i), mockDeck.get(j), "The same cards should be equal");
                else {
                    assertNotEquals(mockDeck.get(i), mockDeck.get(j), "The same cards should not be equal");
                }
            }
        }
    }
}