package gamebasedclasses;

import CardBasedClasses.Card;
import CardBasedClasses.Suits;
import CardBasedClasses.Type;
import PlayerBasedClasses.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMechanicsTest {

    public static final int LIMIT = 21;

    private GameMechanics gameMechanics;
    private Player        player1;
    private Player        player2;
    private Card          sC;
    private Card          aC1;
    private Card          aC2;

    @BeforeEach
    void setUp() {

        gameMechanics = new GameMechanics(LIMIT);
        player1 = new Player(1, gameMechanics.getLimit());
        player2 = new Player(2, gameMechanics.getLimit());

        sC = new Card(Suits.CLUB, Type.STANDARD, Type.STANDARD.getDefaultValue());
        aC1 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
        aC2 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
    }

    @Test
    void getLimit() {

        assertEquals(LIMIT, gameMechanics.getLimit(), "Games limit should match global limit");
    }

    @Test
    void getRound() {
        assertEquals(1, gameMechanics.getRound(), "Games' first round should be 1");
    }

    @Test
    void advanceRound() {
        gameMechanics.advanceRound();
        assertEquals(2, gameMechanics.getRound(), "Games' first round should be 2");
    }

    @Test
    void addPlayer() {
        assertEquals(0, gameMechanics.getPlayers().size(), "There should be no players.");

        gameMechanics.addPlayer(player1);
        assertEquals(1, gameMechanics.getPlayers().size(), "There should be 1 player.");
        assertEquals(player1, gameMechanics.getPlayers().get(0), "The player in the gameMechanics should be player1");

        gameMechanics.addPlayer(player2);
        assertEquals(2, gameMechanics.getPlayers().size(), "There should be 2 players.");
        assertEquals(player2, gameMechanics.getPlayers().get(1), "The player in the gameMechanics should be player2");

        gameMechanics.addPlayer(player2);
        assertEquals(2, gameMechanics.getPlayers().size(), "There should be 2 players.");
        assertEquals(player2, gameMechanics.getPlayers().get(1), "The player in the gameMechanics should be player2");

    }

    @Test
    void allPlayersReady() {

        gameMechanics.addPlayer(player1);
        gameMechanics.addPlayer(player2);

        assertFalse(gameMechanics.allPlayersReady(), "All players should not be ready");

        gameMechanics.getPlayers().get(0).makeReady();
        assertFalse(gameMechanics.allPlayersReady(), "All players should not be ready");

        gameMechanics.getPlayers().get(1).makeReady();
        assertTrue(gameMechanics.allPlayersReady(), "All players should be ready");
    }

    @Test
    void findWinner() {

        gameMechanics.addPlayer(player1);
        gameMechanics.addPlayer(player2);

        Player p1 = gameMechanics.getPlayers().get(0);
        Player p2 = gameMechanics.getPlayers().get(1);

//        assertNull(gameMechanics.findWinner().get(0), "i have no idea");

        p1.takeNewCard(sC);

        assertEquals(player1, gameMechanics.findWinner().get(0), "Winner should be player 1");
        assertEquals(2, p1.showHandScore(), "Player 1's score should be 2");

        p2.takeNewCard(aC1);

        assertEquals(player2, gameMechanics.findWinner().get(0), "Winner should be player 2");
        assertEquals(11, p2.showHandScore(), "Player 2's score should be 11");

        p1.takeNewCard(aC2);

        assertEquals(player1, gameMechanics.findWinner().get(0), "Winner should be player 1");
        assertEquals(13, p1.showHandScore(), "Player 1's score should be 13");

        p1.takeNewCard(aC2);

        assertEquals(player2, gameMechanics.findWinner().get(0), "Winner should be player 2");
        assertEquals(24, p1.showHandScore(), "Player 2's score should be 24");


    }

    @Test
    void gameStart() {

        gameMechanics.addPlayer(player1);
        gameMechanics.addPlayer(player2);

        Player p1 = gameMechanics.getPlayers().get(0);
        Player p2 = gameMechanics.getPlayers().get(1);

        gameMechanics.gameStart();

        assertEquals(2, p1.showHand().size(), "Should be 2");
        assertEquals(2, p2.showHand().size(), "Should be 2");
    }

    @Test
    void play() {

        for (int i = 0; i < 10000; i++) {

            gameMechanics.addPlayer(player1);
            gameMechanics.addPlayer(player2);

            Player p1 = gameMechanics.getPlayers().get(0);
            Player p2 = gameMechanics.getPlayers().get(1);

            gameMechanics.gameStart();

            List<Player> results = gameMechanics.play();

            if (results.size() > 0) {
                if (gameMechanics.play().get(0).equals(player1) & gameMechanics.play().size() == 1) {
//                    System.out.println("Player 1 wins: " + p1.showHandScore() + " " + p2.showHandScore());
                    assertTrue(p1.showHandScore() > p2.showHandScore() | p2.showHandScore() >= LIMIT,
                               "Player 1 should be the winner");
                }
                else if (gameMechanics.play().get(0).equals(player2) & gameMechanics.play().size() == 1) {
//                    System.out.println("Player 2 wins: " + p1.showHandScore() + " " + p2.showHandScore());
                    assertTrue(p1.showHandScore() < p2.showHandScore() | p1.showHandScore() >= LIMIT,
                               "Player 2 should be the winner");
                }
                else if (gameMechanics.play().size() > 1) {
//                    System.out.println("Players draw: " + p1.showHandScore() + " " + p2.showHandScore());
                    assertTrue(p1.showHandScore() == p2.showHandScore() & p1.showHandScore() <= LIMIT,
                               "Player 1 and 2 should be the winners");
                }
            }
            else {
                assertTrue(p1.showHandScore() >= LIMIT & p2.showHandScore() >= LIMIT);
                System.out.printf("No winners: p1: %d || p2: %d%n", p1.showHandScore(), p2.showHandScore());
                System.out.println(p1.showHand() + "\n" + p2.showHand());
            }

            gameMechanics.gameReset();
        }


    }

    @Test
    void cardHandOff() {

        gameMechanics.addPlayer(player1);
        gameMechanics.addPlayer(player2);

        Player p1 = gameMechanics.getPlayers().get(0);
        Player p2 = gameMechanics.getPlayers().get(1);

        assertEquals(0, p1.showHand().size(), "Player should have 0 card in hand");

        gameMechanics.cardHandOff(p1);

        assertEquals(1, p1.showHand().size(), "Player should have 1 card in hand");
    }
}