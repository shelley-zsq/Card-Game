package score.Play;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import score.IScoringStrategy;

import java.util.List;
import java.util.TreeSet;

public class Utils {
    // get the last numCards in hand
    public static List<Card> tail(Hand hand, int numCards) {
        List<Card> listCards = hand.getCardList();
        if (listCards.size() < numCards) {
            return null;
        }
        return listCards.subList(listCards.size() - numCards, listCards.size());
    }

    public static IScoringStrategy.Score getPairScore(Hand hand, int numCards, int score, String strategyName) {
        List<Card> listCards = tail(hand, numCards);
        if (listCards != null) {
            boolean pair = true;
            int firstCard = ((Cribbage.Rank) listCards.get(0).getRank()).order;
            for (int i = 1; i < listCards.size(); i++) {
                if (firstCard != ((Cribbage.Rank) listCards.get(i).getRank()).order) {
                    pair = false;
                    break;
                }
            }
            if (pair) {
                return new IScoringStrategy.Score(score, strategyName, hand);
            }
        }
        return null;

    }

    public static IScoringStrategy.Score getRunScore(Hand hand, int numCards, int score, String strategyName) {
        List<Card> listCards = tail(hand, numCards);
        if (listCards != null) {
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
                return new IScoringStrategy.Score(score, strategyName, hand);
            }
        }
        return null;
    }
}
