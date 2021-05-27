package score;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import score.IScoringStrategy;

import java.util.ArrayList;
import java.util.List;

public class Jack implements IScoringStrategy {
    static final int JACK = 11;
    static final int SCORE = 1;
    static final String strategyName = "jack";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        // assume the first card in the hand is the starter card.
        if (((Cribbage.Rank) hand.getCardList().get(0).getRank()).order == JACK) {
            result.add(new Score(SCORE, strategyName, hand));
            return result;
        }
        return result;
    }
}
