package score.Play;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.List;

public class Pair2 implements IScoringStrategy {
    static final int NUM_CARDS = 2;
    static final int SCORE = 2;
    static final String STRATEGY_NAME = "pair2";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        Score score = Utils.getPairScore(hand, NUM_CARDS, SCORE, STRATEGY_NAME);
        if (score != null) {
            result.add(score);
        }
        return result;
    }
}
