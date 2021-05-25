package score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;
import java.util.List;

public class Pair3 implements IScoringStrategy {
    static final int SCORE = 6;
    static final String strategyName = "pair3";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        List<Card> listCards= hand.getCardList();
        boolean pair = true;
        int firstCard = ((Cribbage.Rank) listCards.get(0).getRank()).order;
        for (int i = 1; i < listCards.size(); i++) {
            if (firstCard != ((Cribbage.Rank) listCards.get(i).getRank()).order) {
                pair = false;
                break;
            }
        }
        if (pair) {
            result.add(new Score(SCORE, strategyName, hand));
        }
        return result;
    }

}