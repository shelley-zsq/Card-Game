package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import score.ScoringStrategySingletonFactory;

import java.util.List;

public class ScoringCribbageGameDecorator extends CribbageGameDecorator {
    public ScoringCribbageGameDecorator(CribbageGame cribbage) {
        super(cribbage);
    }

    @Override
    public void starter(Hand pack) {
        decoratedCribbage.starter(pack);
        IScoringStrategy starterStrategy = ScoringStrategySingletonFactory.getInstance().getScoringStrategy("STARTER");
        List<IScoringStrategy.Score> scores = starterStrategy.getScore(Cribbage.cribbage.starter);
        if (scores.size() > 0) {
            IScoringStrategy.Score score = scores.get(0);
            int player = 1;
            Cribbage.cribbage.scores[player] += score.getScore();
            Utils.appendToFile(String.format("score,P%d,%d,%d,%s,%s%n",
                    player,
                    Cribbage.cribbage.scores[player],
                    score.getScore(),
                    score.getStrategyName(),
                    Cribbage.cribbage.canonical(score.getHand())));
            System.out.printf("score,P%d,%d,%d,%s,%s%n",
                    player,
                    Cribbage.cribbage.scores[player],
                    score.getScore(),
                    score.getStrategyName(),
                    Cribbage.cribbage.canonical(score.getHand()));
            Cribbage.cribbage.updateScore(player, Cribbage.cribbage.scores[player]);
        }
    }

    @Override
    public void go(int player) {
        decoratedCribbage.go(player);
        IScoringStrategy goStrategy = ScoringStrategySingletonFactory.getInstance().getScoringStrategy("GO");
        IScoringStrategy.Score score = goStrategy.getScore(null).get(0);
        Cribbage.cribbage.scores[player] += score.getScore();
        Utils.appendToFile(String.format("score,P%d,%d,%d,%s%n", player, Cribbage.cribbage.scores[player], score.getScore(), score.getStrategyName()));
        System.out.printf("score,P%d,%d,%d,%s%n", player, Cribbage.cribbage.scores[player], score.getScore(), score.getStrategyName());
        Cribbage.cribbage.updateScore(player, Cribbage.cribbage.scores[player]);
    }

    @Override
    public void playCard(int player, Card card, Hand hand) {
        decoratedCribbage.playCard(player, card, hand);
        IScoringStrategy playStrategy = ScoringStrategySingletonFactory.getInstance().getScoringStrategy("PLAY");
        for (IScoringStrategy.Score score : playStrategy.getScore(hand)) {
            Cribbage.cribbage.scores[player] += score.getScore();
            Utils.appendToFile(String.format("score,P%d,%d,%d,%s%n", player, Cribbage.cribbage.scores[player], score.getScore(), score.getStrategyName()));
            System.out.printf("score,P%d,%d,%d,%s%n", player, Cribbage.cribbage.scores[player], score.getScore(), score.getStrategyName());
            Cribbage.cribbage.updateScore(player, Cribbage.cribbage.scores[player]);
        }
    }

    @Override
    public void showHands(int player, Hand starter, Hand hand) {
        decoratedCribbage.showHands(player, starter, hand);
        Hand tmpHand = Utils.newHand(starter);
        for (Card card : hand.getCardList()) {
            tmpHand.insert(card.clone(), false);
        }
        hand = tmpHand;
        IScoringStrategy showStrategy = ScoringStrategySingletonFactory.getInstance().getScoringStrategy("SHOW");
        for (IScoringStrategy.Score score : showStrategy.getScore(hand)) {
            Cribbage.cribbage.scores[player] += score.getScore();
            Utils.appendToFile(String.format("score,P%d,%d,%d,%s,%s%n",
                    player,
                    Cribbage.cribbage.scores[player],
                    score.getScore(),
                    score.getStrategyName(),
                    Cribbage.cribbage.canonical(score.getHand())));
            System.out.printf("score,P%d,%d,%d,%s,%s%n",
                    player,
                    Cribbage.cribbage.scores[player],
                    score.getScore(),
                    score.getStrategyName(),
                    Cribbage.cribbage.canonical(score.getHand()));
            Cribbage.cribbage.updateScore(player, Cribbage.cribbage.scores[player]);
        }
    }
}
