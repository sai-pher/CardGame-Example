package gamebasedclasses;

import CardBasedClasses.Card;
import CardBasedClasses.Suits;
import CardBasedClasses.Type;
import PlayerBasedClasses.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    public static final int LIMIT = 21;

    private Game   game;
    private Player player1;
    private Player player2;
    private Card   sC;
    private Card   aC1;
    private Card   aC2;

    @BeforeEach
    void setUp() {

        game = new Game(LIMIT);
        player1 = new Player(game.getLimit());
        player2 = new Player(game.getLimit());

        sC = new Card(Suits.CLUB, Type.STANDARD, Type.STANDARD.getDefaultValue());
        aC1 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
        aC2 = new Card(Suits.CLUB, Type.ACE, Type.ACE.getDefaultValue());
    }

    @Test
    void getLimit() {

        assertEquals(LIMIT, game.getLimit(), "Games limit should match global limit");
    }

    @Test
    void getRound() {
        assertEquals(1, game.getRound(), "Games' first round should be 1");
    }

    @Test
    void advanceRound() {
        game.advanceRound();
        assertEquals(2, game.getRound(), "Games' first round should be 2");
    }

    @Test
    void addPlayer() {
        assertEquals(0, game.getPlayers().size(), "There should be no players.");

        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size(), "There should be 1 player.");
        assertEquals(player1, game.getPlayers().get(0), "The player in the game should be player1");

        game.addPlayer(player2);
        assertEquals(2, game.getPlayers().size(), "There should be 2 players.");
        assertEquals(player2, game.getPlayers().get(1), "The player in the game should be player2");

        game.addPlayer(player2);
        assertEquals(2, game.getPlayers().size(), "There should be 2 players.");
        assertEquals(player2, game.getPlayers().get(1), "The player in the game should be player2");

    }

    @Test
    void allPlayersReady() {

        game.addPlayer(player1);
        game.addPlayer(player2);

        assertFalse(game.allPlayersReady(), "All players should not be ready");

        game.getPlayers().get(0).makeReady();
        assertFalse(game.allPlayersReady(), "All players should not be ready");

        game.getPlayers().get(1).makeReady();
        assertTrue(game.allPlayersReady(), "All players should be ready");
    }

    @Test
    void findWinner() {

        game.addPlayer(player1);
        game.addPlayer(player2);

        Player p1 = game.getPlayers().get(0);
        Player p2 = game.getPlayers().get(1);

//        assertNull(game.findWinner().get(0), "i have no idea");

        p1.takeNewCard(sC);

        assertEquals(player1, game.findWinner().get(0), "Winner should be player 1");
        assertEquals(2, p1.showHandScore(), "Player 1's score should be 2");

        p2.takeNewCard(aC1);

        assertEquals(player2, game.findWinner().get(0), "Winner should be player 2");
        assertEquals(11, p2.showHandScore(), "Player 2's score should be 11");

        p1.takeNewCard(aC2);

        assertEquals(player1, game.findWinner().get(0), "Winner should be player 1");
        assertEquals(13, p1.showHandScore(), "Player 1's score should be 13");

        p1.takeNewCard(aC2);

        assertEquals(player2, game.findWinner().get(0), "Winner should be player 2");
        assertEquals(24, p1.showHandScore(), "Player 2's score should be 24");


    }

    @Test
    void gameStart() {

        game.addPlayer(player1);
        game.addPlayer(player2);

        Player p1 = game.getPlayers().get(0);
        Player p2 = game.getPlayers().get(1);

        game.gameStart();

        assertEquals(2, p1.showHand().size(), "Should be 2");
        assertEquals(2, p2.showHand().size(), "Should be 2");
    }

    @Test
    void play() {

        for (int i = 0; i < 10000; i++) {

            game.addPlayer(player1);
            game.addPlayer(player2);

            Player p1 = game.getPlayers().get(0);
            Player p2 = game.getPlayers().get(1);

            game.gameStart();

            List<Player> results = game.play();

            if (results.size() > 0) {
                if (game.play().get(0).equals(player1) & game.play().size() == 1) {
//                    System.out.println("Player 1 wins: " + p1.showHandScore() + " " + p2.showHandScore());
                    assertTrue(p1.showHandScore() > p2.showHandScore() | p2.showHandScore() >= LIMIT,
                               "Player 1 should be the winner");
                }
                else if (game.play().get(0).equals(player2) & game.play().size() == 1) {
//                    System.out.println("Player 2 wins: " + p1.showHandScore() + " " + p2.showHandScore());
                    assertTrue(p1.showHandScore() < p2.showHandScore() | p1.showHandScore() >= LIMIT,
                               "Player 2 should be the winner");
                }
                else if (game.play().size() > 1) {
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

            game.gameReset();
        }


    }

    @Test
    void cardHandOff() {

        game.addPlayer(player1);
        game.addPlayer(player2);

        Player p1 = game.getPlayers().get(0);
        Player p2 = game.getPlayers().get(1);

        assertEquals(0, p1.showHand().size(), "Player should have 0 card in hand");

        game.cardHandOff(p1);

        assertEquals(1, p1.showHand().size(), "Player should have 1 card in hand");
    }
}