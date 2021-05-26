package score.Show;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import score.IScoringStrategy;

import java.util.ArrayList;
import java.util.List;

public class Flush4 implements IScoringStrategy {
    static final int SCORE = 4;
    static final String strategyName = "flush4";

    @Override
    public List<Score> getScore(Hand hand) {
        // hand is a list of cards, the first card is the starter card.
        List<Score> result = new ArrayList<>();
        Cribbage.Suit suit = (Cribbage.Suit) hand.getCardList().get(1).getSuit();
        for (int i = 2; i < hand.getCardList().size(); i++) {
            if (suit != hand.getCardList().get(i).getSuit()) {
                return result;
            }
        }
        result.add(new Score(SCORE, strategyName, hand));
        return result;
    }
}
