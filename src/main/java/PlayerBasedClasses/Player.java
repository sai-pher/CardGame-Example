package PlayerBasedClasses;

import CardBasedClasses.Card;

import java.util.ArrayList;
import java.util.List;

public final class Player {

    private final int        THRESHOLD      = 15;
    private final int        playerID;
    private final int        KNOWN_LIMIT;
    private       List<Card> hand           = new ArrayList<>();
    private       int        handTotalScore = 0;
    private       Boolean    ready          = false;

    public Player(int playerID, final int limit) {
        this.playerID = playerID;
        KNOWN_LIMIT = limit;
    }

    /**
     * Method to take a new card object and add it to the players hand.
     *
     * @param card A new card object to be added to the players hand
     */
    public void takeNewCard(Card card) {
        hand.add(card);
        updateHandScore(card);
        checkLimit();
    }

    /**
     * A method to add a cards value to the hand score.
     *
     * @param card A new card object whose value will be added to hand score
     */
    private void updateHandScore(Card card) {
        handTotalScore += card.getDefaultValue();
    }

    /**
     * Method to compare a players hand to the games known limit.
     * The method sets the players ready signal to true if hand score
     * exceeds the games limit, ending their turn.
     */
    private void checkLimit() {
        if (handTotalScore >= KNOWN_LIMIT)
            ready = true;
    }

    /**
     * Method to signal to player whether or not it should take a new card
     * based on some internal player logic.
     *
     * @return A boolean value signal.
     */
    public boolean shouldTakeCard() {
        return handTotalScore <= THRESHOLD;
    }

    /**
     * Method to set a players ready signal to true, signaling to the game that
     * the player is ready to end their turn and show their cards and scores.
     */
    public void makeReady() {
        ready = true;
    }

    /**
     * A method to reveal the players hand to the game.
     *
     * @return The data structure containing the players cards at the end of the round.
     */
    public List<Card> showHand() {
        return hand;
    }

    /**
     * Method to reveal the players score to the game.
     *
     * @return The players total score.
     */
    public int showHandScore() {
        return handTotalScore;
    }

    /**
     * @return The boolean value of a players ready state.
     */
    public Boolean showReady() {
        return ready;
    }

    public void playerReset() {
        handTotalScore = 0;
        hand.clear();
        ready = false;
    }


    public int getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return String.format("Player %d", playerID);
    }
}
