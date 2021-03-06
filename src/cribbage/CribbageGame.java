package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public interface CribbageGame {
    void deal(Hand pack, Hand[] hands);
    void discardToCrib();
    void starter(Hand pack);
    void go(int player);
    void playCard(int player, Card card, Hand hand);
    void showHands(int player, Hand starter, Hand hand);
}
