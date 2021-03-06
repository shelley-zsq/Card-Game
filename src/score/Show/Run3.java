package score.Show;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Run3 implements IScoringStrategy {
    static final int NUM_CARDS = 3;
    static final int SCORE = 3;
    static final String STRATEGY_NAME = "run3";

    // Get all runs in the hand, in canonical order
    @Override
    public List<Score> getScore(Hand hand) {
        hand = Utils.newHand(hand);
        hand.sort(Hand.SortType.POINTPRIORITY, false);
        List<Score> result = new ArrayList<>();
        List<Hand> hands= new ArrayList<>();
        Utils.combine(hand, 0, Utils.newHand(), NUM_CARDS, hands);

        for (Hand h : hands) {
            Score score = Utils.getRunScore(h, NUM_CARDS, SCORE, STRATEGY_NAME);
            if (score != null) {
                result.add(score);
            }
        }

        Collections.sort(result);
        return result;
    }
}
