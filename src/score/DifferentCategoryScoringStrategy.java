package score;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class DifferentCategoryScoringStrategy extends CompositeScoringStrategy {
    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        for (IScoringStrategy strategy : scoringStrategies) {
            result.addAll(strategy.getScore(hand));
        }
        return result;
    }
}
