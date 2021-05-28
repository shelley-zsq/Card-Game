package score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jack implements IScoringStrategy {
    static final int JACK = 11;
    static final int SCORE = 1;
    static final String strategyName = "jack";

    @Override
    public List<Score> getScore(Hand hand) {
        hand = Utils.newHand(hand);
        hand.sort(Hand.SortType.POINTPRIORITY, false);
        List<Score> result = new ArrayList<>();

        for (Card card : hand.getCardList()) {
            Hand tmpHand = Utils.newHand();
            tmpHand.insert(card.clone(), false);
            if (((Cribbage.Rank) card.getRank()).order == JACK && card.getSuit() == Cribbage.cribbage.starter.get(0).getSuit()) {
                result.add(new Score(SCORE, strategyName, tmpHand));
            }
        }

        Collections.sort(result);
        return result;
    }
}
