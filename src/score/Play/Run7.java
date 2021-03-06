package score.Play;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.List;

public class Run7 implements IScoringStrategy {
    static final int NUM_CARDS = 7;
    static final int SCORE = 7;
    static final String STRATEGY_NAME = "run7";

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
