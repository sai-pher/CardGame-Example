package CardBasedClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Deck {

    private List<Card> deck     = new ArrayList<>();
    private int        nextCard = 0;
    private int        length;
    //TODO: Add method/variables to count each card type.

    public Deck() {
        createDeck();
        shuffle();
        length = deck.size();
    }

    private void createDeck() {

        for (Suits s : Suits.values()) {
            for (Type type : Type.values()) {

                if (type != Type.STANDARD) {
                    if (type == Type.ACE) {
                        final Card aceCard = new Card(s, type, type.getDefaultValue(), type.getAltValue());
                        deck.add(aceCard);
                    }
                    else {
                        final Card card = new Card(s, type, type.getDefaultValue());
                        deck.add(card);
                    }
                }
                else {
                    for (int i = 2; i <= type.getNumber(); i++) {
                        final Card card = new Card(s, type, i);
                        deck.add(card);
                    }
                }

            }
        }
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {

        if (nextCard < deck.size()) {
            Card card = deck.get(nextCard);
            nextCard += 1;
            return card;
        }
        return null;
    }

    public int getLength() {
        return length;
    }
}
