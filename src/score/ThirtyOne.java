package score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;
import java.util.List;

public class ThirtyOne implements IScoringStrategy {
    static final int SCORE = 2;
    static final int THIRTY_ONE = 31;
    static final String strategyName = "thirtyone";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        int total = 0;
        for (Card c : hand.getCardList()) {
            total += ((Cribbage.Rank) c.getRank()).value;
        }
        if (total == THIRTY_ONE) {
            result.add(new Score(SCORE, strategyName, hand));
        }
        return result;
    }
}
