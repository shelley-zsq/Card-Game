package score.Show;

import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fifteen implements IScoringStrategy {
    static final int NUM_CARDS = 5;
    static final int FIFTEEN = 15;
    static final int SCORE = 2;
    static final String STRATEGY_NAME = "fifteen";

    // combine the cards in the hand to set of 2, 3, 4, or 5 and see if each set
    // has a total face value of 15.
    // returns all set that has total = 15, in the canonical order.
    @Override
    public List<Score> getScore(Hand hand) {
        // sort the hand
        hand = Utils.newHand(hand);
        hand.sort(Hand.SortType.POINTPRIORITY, false);
        List<Score> result = new ArrayList<>();
        List<Hand> hands = new ArrayList<>();
        // since the max face value is 10, 2 or more cards can have face value of 15, start from 2.
        for (int i = 2; i <= NUM_CARDS; i++) {
            Utils.combine(hand, 0, Utils.newHand(), i, hands);
        }
        for (Hand h : hands) {
            int value = Utils.computeTotalValue(h);
            if (value == FIFTEEN) {
                result.add(new Score(SCORE, STRATEGY_NAME, h));
            }
        }
        Collections.sort(result);
        return result;
    }
}
