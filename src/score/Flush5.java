package score;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;
import java.util.List;

public class Flush5 implements IScoringStrategy {
    static final int SCORE = 5;
    static final String strategyName = "flush5";

    @Override
    public List<Score> getScore(Hand hand) {
        // assumes that the first card is the starter card
        List<Score> result = new ArrayList<>();
        Cribbage.Suit suit = (Cribbage.Suit) hand.getCardList().get(0).getSuit();
        for (int i = 1; i < hand.getCardList().size(); i++) {
            if (suit != hand.getCardList().get(i).getSuit()) {
                return result;
            }
        }
        result.add(new Score(SCORE, strategyName, hand));
        return result;
    }
}