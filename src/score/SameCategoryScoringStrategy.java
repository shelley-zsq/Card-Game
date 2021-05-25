package score;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class SameCategoryScoringStrategy extends CompositeScoringStrategy {
    @Override
    public List<Score> getScore(Hand hand) {
        for (IScoringStrategy strategy : scoringStrategies) {
            List<Score> result = strategy.getScore(hand);
            if (result.size() > 0) {
                return result;
            }
        }
        return new ArrayList<>();
    }
}
