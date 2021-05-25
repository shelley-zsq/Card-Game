package score;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

abstract class CompositeScoringStrategy implements IScoringStrategy {
    protected ArrayList<IScoringStrategy> scoringStrategies = new ArrayList<>();
    public void add(IScoringStrategy strategy) {
        scoringStrategies.add(strategy);
    }
    public abstract List<Score> getScore(Hand hand);
}