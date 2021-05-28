package score.Show;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;
import cribbage.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair implements IScoringStrategy {

    // Get all pairs in the hand
    // - first count the number of each rank
    // - then see if any pair exists, and return all pairs
    @Override
    public List<Score> getScore(Hand hand) {
        hand = Utils.newHand(hand);
        hand.sort(Hand.SortType.POINTPRIORITY, false);

        List<Score> result = new ArrayList<>();
        Hand pair = Utils.containsPair(hand, 4);
        List<Score> pair4 = new ArrayList<>();
        while (pair != null) {
            pair4.add(new Score(12, "pair4", pair));
            List<Card> cardList = hand.getCardList();
            hand = Utils.newHand();
            for (Card card : cardList) {
                if (!pair.getCardList().contains(card)) {
                    hand.insert(card.clone(), false);
                }
            }
            pair = Utils.containsPair(hand, 4);
        }
        Collections.sort(pair4);
        result.addAll(pair4);

        // Pair 3
        List<Score> pair3 = new ArrayList<>();
        pair = Utils.containsPair(hand, 3);
        while (pair != null) {
            pair3.add(new Score(6, "pair3", pair));
            List<Card> cardList = hand.getCardList();
            hand = Utils.newHand();
            for (Card card : cardList) {
                if (!pair.getCardList().contains(card)) {
                    hand.insert(card.clone(), false);
                }
            }
            pair = Utils.containsPair(hand, 3);
        }
        Collections.sort(pair3);
        result.addAll(pair3);

        // pair 2
        List<Score> pair2 = new ArrayList<>();
        pair = Utils.containsPair(hand, 2);
        while (pair != null) {
            System.out.println(pair);
            pair2.add(new Score(2, "pair2", pair));
            List<Card> cardList = hand.getCardList();
            hand = Utils.newHand();
            for (Card card : cardList) {
                if (!pair.getCardList().contains(card)) {
                    hand.insert(card.clone(), false);
                }
            }
            pair = Utils.containsPair(hand, 2);
        }
        Collections.sort(pair2);
        result.addAll(pair2);
        return result;
    }
}
