package score;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.List;

public class Starter implements IScoringStrategy {
    static final int JACK = 11;
    static final int SCORE = 2;
    static final String strategyName = "starter";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        // assume the first card in the hand is the starter card.
        if (((Cribbage.Rank) hand.getCardList().get(0).getRank()).order == JACK) {
            result.add(new Score(SCORE, strategyName, hand));
        }
        return result;
    }
}
