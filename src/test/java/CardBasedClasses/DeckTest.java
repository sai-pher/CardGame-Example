package CardBasedClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DeckTest {

    @Test
    public void testDeckHas52Cadrs() {
        Deck d = new Deck();
        assertEquals(52, d.getLength(), "Should contain 52 cards");
    }

    @Test
    public void testDeckCanDrawCard() {
        Deck d = new Deck();
        Card c = d.draw();
        assertNotEquals(null, c, "Card should be drawn");
    }

}