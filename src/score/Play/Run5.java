package score.Play;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import score.IScoringStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Run5 implements IScoringStrategy {
    static final int NUM_CARDS = 5;
    static final int SCORE = 5;
    static final String STRATEGY_NAME = "run5";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        Score score = Utils.getRunScore(hand, NUM_CARDS, SCORE, STRATEGY_NAME);
        if (score != null) {
            result.add(score);
        }
        return result;
    }
}
