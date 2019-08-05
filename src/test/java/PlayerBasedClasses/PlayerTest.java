package PlayerBasedClasses;

import CardBasedClasses.Card;
import CardBasedClasses.Suits;
import CardBasedClasses.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player p;
    private Card   sC;
    private Card   aC1;
    private Card   aC2;

    @BeforeEach
    void setUp() {
        p = new Player(21);
        sC = new Card(Suits.CLUB, Type.STANDARD, Type.STANDARD.getDefaultValue());
        aC1 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
        aC2 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
    }

    @Test
    void takeNewCard() {
        p.takeNewCard(sC);
        assertEquals(p.showHand().get(0), sC, "Method should add card to hand");
    }

    @Test
    void checkLimit() {
        p.takeNewCard(aC1);
        assertEquals(false, p.getReady(), "Ready state should be false");
        p.takeNewCard(aC2);
        assertEquals(true, p.getReady(), "Ready state should be True");
    }

    @Test
    void shouldTakeCard() {
        assertTrue(p.shouldTakeCard(), "Player should take card (true)");
        p.takeNewCard(aC1);
        assertTrue(p.shouldTakeCard(), "Player should take card (true)");
        p.takeNewCard(aC2);
        assertFalse(p.shouldTakeCard(), "Player should not take card (false)");

    }

    @Test
    void makeReady() {
        p.makeReady();
        assertEquals(true, p.getReady(), "Ready should be true");
        p.makeReady();
        assertEquals(true, p.getReady(), "Ready should be true");
    }

    @Test
    void showHand() {
        List<Card> h = p.showHand();
        assertEquals(0, h.size(), "Hand should be empty (0)");
        p.takeNewCard(sC);
        assertEquals(1, h.size(), "Hand should have 1 card");
        p.takeNewCard(aC1);
        assertEquals(2, h.size(), "Hand should have 2 cards");
        p.takeNewCard(aC2);
        assertEquals(3, h.size(), "Hand should have 3 cards");

    }

    @Test
    void getReady() {
        assertEquals(false, p.getReady(), "Ready should be false");
        p.makeReady();
        assertEquals(true, p.getReady(), "Ready should be true");
    }

    @Test
    void showHandScore() {
        assertEquals(0, p.showHandScore(), "Hand score should be 0");
    }

    @Test
    void updateHandScore() {
        p.takeNewCard(aC1);
        assertEquals(11, p.showHandScore(), "Hand score should be 11");
        p.takeNewCard(aC2);
        assertEquals(22, p.showHandScore(), "Hand score should be 22");
    }
}