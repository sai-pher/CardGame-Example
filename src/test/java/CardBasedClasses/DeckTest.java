package CardBasedClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DeckTest {

    @Test
    public void testDeckHas52Cards() {
        Deck d = new Deck();
        assertEquals(52, d.getLength(), "Should contain 52 cards");
    }

    @Test
    public void testDeckCanDrawCard() {
        Deck d = new Deck();
        Card c = d.draw();
        assertNotEquals(null, c, "Card should be drawn");
    }

    @Test
    public void testCardsDrawnNotEqual() {
        Deck d  = new Deck();
        Card c1 = d.draw();
        Card c2 = d.draw();

        System.out.println(c1 + "\n" + c2);

        assertNotEquals(c1, c2, "Cards drawn should not be the same");
    }

}