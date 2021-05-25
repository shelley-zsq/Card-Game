package score;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Run7 implements IScoringStrategy {
    static final int SCORE = 7;
    static final String strategyName = "run7";

    @Override
    public List<Score> getScore(Hand hand) {
        List<Score> result = new ArrayList<>();
        List<Card> listCards= hand.getCardList();
        TreeSet<Integer> cardSet = new TreeSet<>();
        for (int i = 0; i < listCards.size(); i++) {
            cardSet.add(((Cribbage.Rank) listCards.get(i).getRank()).order);
        }
        int minCard = cardSet.first();
        boolean run = true;
        for (int i = 1; i < cardSet.size(); i++) {
            if (!cardSet.contains(minCard + i)) {
                run = false;
                break;
            }
        }
        if (run) {
            result.add(new Score(SCORE, strategyName, hand));
        }
        return result;
    }
}
