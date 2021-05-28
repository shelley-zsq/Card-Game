package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingCribbageGameDecorator extends CribbageGameDecorator {

    public LoggingCribbageGameDecorator(CribbageGame cribbage) {
        super(cribbage);
        Utils.createFile();
        Utils.appendToFile(String.format("seed,%d%n", Cribbage.SEED));
        System.out.printf("seed,%d%n", Cribbage.SEED);
        for (int i = 0; i < Cribbage.players.length; i++) {
            Utils.appendToFile(String.format("%s,P%d%n", Cribbage.players[i].getClass().getName(), i));
            System.out.printf("%s,P%d%n", Cribbage.players[i].getClass().getName(), i);
        }
    }

    @Override
    public void deal(Hand pack, Hand[] hands) {
        decoratedCribbage.deal(pack, hands);
        for (int i = 0; i < hands.length; i++) {
            Utils.appendToFile(String.format("deal,P%d,%s%n", i, Cribbage.cribbage.canonical(hands[i])));
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
            Utils.appendToFile(String.format("discard,P%d,%s%n", i, Cribbage.cribbage.canonical(discardCards[i])));
            System.out.printf("discard,P%d,%s%n", i, Cribbage.cribbage.canonical(discardCards[i]));
        }
    }

    @Override
    public void starter(Hand pack) {
        decoratedCribbage.starter(pack);
        Utils.appendToFile(String.format("starter,%s%n", Cribbage.cribbage.canonical(Cribbage.cribbage.starter.get(0))));
        System.out.printf("starter,%s%n", Cribbage.cribbage.canonical(Cribbage.cribbage.starter.get(0)));
    }

    @Override
    public void playCard(int player, Card card, Hand hand) {
        decoratedCribbage.playCard(player, card, hand);
        Utils.appendToFile(String.format("play,P%d,%d,%s%n", player, Cribbage.cribbage.total(hand), Cribbage.cribbage.canonical(card)));
        System.out.printf("play,P%d,%d,%s%n", player, Cribbage.cribbage.total(hand), Cribbage.cribbage.canonical(card));
    }

    @Override
    public void showHands(int player, Hand starter, Hand hand) {
        decoratedCribbage.showHands(player, starter, hand);
        Utils.appendToFile(String.format("show,P%d,%s+%s%n",player, Cribbage.cribbage.canonical(starter.get(0)), Cribbage.cribbage.canonical(hand)));
        System.out.printf("show,P%d,%s+%s%n",player, Cribbage.cribbage.canonical(starter.get(0)), Cribbage.cribbage.canonical(hand));
    }

    @Override
    public void showCrib(int player, Hand starter, Hand hand) {
        decoratedCribbage.showCrib(player, starter, hand);
        Utils.appendToFile(String.format("show,P%d,%s+%s%n",player, Cribbage.cribbage.canonical(starter.get(0)), Cribbage.cribbage.canonical(hand)));
        System.out.printf("show,P%d,%s+%s%n",player, Cribbage.cribbage.canonical(starter.get(0)), Cribbage.cribbage.canonical(hand));
    }
}
