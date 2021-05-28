package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public abstract class CribbageGameDecorator implements CribbageGame {
    protected CribbageGame decoratedCribbage;

    public CribbageGameDecorator(CribbageGame cribbage) {
        this.decoratedCribbage = cribbage;
    }

    public void deal(Hand pack, Hand[] hands) {
        decoratedCribbage.deal(pack, hands);
    }

    public void discardToCrib() {
        decoratedCribbage.discardToCrib();
    }

    public void starter(Hand pack) {
        decoratedCribbage.starter(pack);
    }

    public void go(int player) {
        decoratedCribbage.go(player);
    }

    public void playCard(int player, Card card, Hand hand) {
        decoratedCribbage.playCard(player, card, hand);
    }

    public void showHands(int player, Hand starter, Hand hand) {
        decoratedCribbage.showHands(player, starter, hand);
    }
}
