package score;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class Go implements IScoringStrategy {
    static final int SCORE = 1;
    static final String strategyName = "go";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        result.add(new Score(SCORE, strategyName, hand));
        return result;
    }
}
