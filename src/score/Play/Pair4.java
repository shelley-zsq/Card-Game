package score.Play;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import score.Utils;

import java.util.ArrayList;
import java.util.List;

public class Pair4 implements IScoringStrategy {
    static final int NUM_CARDS = 4;
    static final int SCORE = 12;
    static final String STRATEGY_NAME = "pair4";

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