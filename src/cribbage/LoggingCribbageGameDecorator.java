package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class LoggingCribbageGameDecorator extends CribbageGameDecorator {

    public LoggingCribbageGameDecorator(CribbageGame cribbage) {
        super(cribbage);
        System.out.printf("seed,%d%n", Cribbage.SEED);
        for (int i = 0; i < Cribbage.players.length; i++) {
            System.out.printf("%s,P%d%n", Cribbage.players[i].getClass().getName(), i);
        }
    }

    @Override
    public void deal(Hand pack, Hand[] hands) {
        decoratedCribbage.deal(pack, hands);
        for (int i = 0; i < hands.length; i++) {
            System.out.printf("deal,P%d,%s%n", i, Cribbage.cribbage.canonical(hands[i]));
        }
    }

    @Override
    public void discardToCrib() {
        decoratedCribbage.discardToCrib();
        Hand[] discardCards = new Hand[Cribbage.nPlayers];
        for (int i = 0; i < Cribbage.nPlayers; i++) {
            discardCards[i] = Utils.newHand();
            for (int j = 0; j < Cribbage.cribbage.crib.getNumberOfCards() / Cribbage.nPlayers; j++) {
                discardCards[i].insert(
                        Cribbage.cribbage.crib.get(
                                j * Cribbage.cribbage.crib.getNumberOfCards() / Cribbage.nPlayers + i),
                        false);
            }
            System.out.printf("discard,P%d,%s%n", i, Cribbage.cribbage.canonical(discardCards[i]));
        }
    }

    @Override
    public void starter(Hand pack) {
        decoratedCribbage.starter(pack);
        System.out.printf("starter,%s%n", Cribbage.cribbage.canonical(Cribbage.cribbage.starter.get(0)));
    }

    @Override
    public void playCard(int player, Card card, Hand hand) {
        decoratedCribbage.playCard(player, card, hand);
        System.out.printf("play,P%d,%d,%s%n", player, Cribbage.cribbage.total(hand), Cribbage.cribbage.canonical(card));
    }

    @Override
    public void showHandsCrib(int player, Hand starter, Hand hand) {
        decoratedCribbage.showHandsCrib(player, starter, hand);
        System.out.printf("show,P%d,%s+%s%n",player, Cribbage.cribbage.canonical(starter.get(0)), Cribbage.cribbage.canonical(hand));
    }
}
