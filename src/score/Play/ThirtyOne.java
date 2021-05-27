package score.Play;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import score.Utils;

import java.util.ArrayList;
import java.util.List;

public class ThirtyOne implements IScoringStrategy {
    static final int SCORE = 2;
    static final int THIRTY_ONE = 31;
    static final String STRATEGY_NAME = "thirtyone";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        if (Utils.computeTotalValue(hand) == THIRTY_ONE) {
            result.add(new Score(SCORE, STRATEGY_NAME, hand));
        }
        return result;
    }
}
