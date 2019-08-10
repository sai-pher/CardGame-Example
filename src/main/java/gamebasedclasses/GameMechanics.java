package gamebasedclasses;

import CardBasedClasses.Card;
import CardBasedClasses.Deck;
import PlayerBasedClasses.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: Make defensive

public class GameMechanics {

    private final Deck         deck;
    private final int          limit;
    private final int          initialCardCount;
    private       int          round   = 1;
    private       List<Player> players = new ArrayList<>();

    public GameMechanics(int limit) {
        this.deck = new Deck();
        this.limit = limit;
        this.initialCardCount = 2;
    }

    /**
     * Method to return game's deck object.
     *
     * @return The deck object used in the game.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Method to return the games score limit.
     *
     * @return The games score limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Method to return the round the game is currently on.
     *
     * @return The game's current round.
     */
    public int getRound() {
        return round;
    }

    /**
     * Method to return The initial number of cards given at game start.
     *
     * @return The initial number of cards given at game start.
     */
    public int getInitialCardCount() {
        return initialCardCount;
    }

    /**
     * Method to return all players currently in the game
     *
     * @return A list of Player objects.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Method to increase the round count by 1.
     */
    public void advanceRound() {
        round += 1;
    }

    /**
     * Method to add new players to the game at play time.
     *
     * @param p A new Player object to be added to the game.
     */
    public void addPlayer(Player p) {
        if (!players.contains(p))
            players.add(p);
    }

    /**
     * Method to show if all players in the game have a true ready state.
     *
     * @return Boolean true or false.
     */
    public boolean allPlayersReady() {
        for (Player p : players) {
            if (!p.showReady())
                return false;
        }
        return true;
    }

    /**
     * Method to find the players with the highest score and return them.
     *
     * @return A list containing all players with the highest score.
     */
    public List<Player> findWinner() {
        int          highestWinningScore = 0;
        List<Player> winners             = new ArrayList<>();

        for (Player p : players) {
            if (p.showHandScore() < limit & p.showHandScore() > highestWinningScore) {
                highestWinningScore = p.showHandScore();
            }
        }

        for (Player p : players) {
            if (p.showHandScore() == highestWinningScore)
                winners.add(p);
        }
        return winners;
    }

    /**
     * Method to provide the games initial state
     */
    public void gameStart() {
        for (Player p : players) {
            for (int i = 0; i < initialCardCount; i++) {
                cardHandOff(p);
            }
        }
    }

    /**
     * Method to run the game play mechanic for all players in the game.
     *
     * @return A list of all the winners of a given game run.
     */
    public List<Player> play() {

        while (!allPlayersReady()) {
            for (Player p : players) {

                if (!p.showReady()) {
                    if (p.shouldTakeCard()) {
                        cardHandOff(p);
                    }
                    else {
                        p.makeReady();
                    }
                }
            }
        }
        return findWinner();
    }

    /**
     * Method to reset the given game to a start state.
     */
    public void gameReset() {
        for (Player p :
                players) {
            p.playerReset();
        }

        deck.deckReset();
    }

    /**
     * Method to draw a card from the deck and hand it over to
     * a given player object
     *
     * @param p A player object to be given a card.
     */
    public void cardHandOff(Player p) {
        Card c = deck.draw();
        p.takeNewCard(c);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameMechanics)) return false;
        GameMechanics gameMechanics = (GameMechanics) o;
        return limit == gameMechanics.limit &&
               Objects.equals(deck, gameMechanics.deck) &&
               Objects.equals(players, gameMechanics.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, limit, players);
    }

    @Override
    public String toString() {
        return "GameMechanics{" +
               "deck=" + deck +
               ", limit=" + limit +
               ", players=" + players +
               '}';
    }

}
