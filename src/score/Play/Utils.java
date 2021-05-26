package score.Play;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;
import score.IScoringStrategy;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Utils {
    // get the last numCards in hand
    public static Hand tail(Hand hand, int numCards) {
        List<Card> listCards = hand.getCardList();
        if (listCards.size() < numCards) {
            return null;
        }

        class MyCardValues implements Deck.CardValues { // Need to generate a unique value for every card
            public int[] values(Enum suit) {  // Returns the value for each card in the suit
                return Stream.of(Cribbage.Rank.values()).mapToInt(r -> (r.order - 1) * (Cribbage.Suit.values().length) + suit.ordinal()).toArray();
            }
        }

        Hand result = new Hand(new Deck(Cribbage.Suit.values(), Cribbage.Rank.values(), "cover", new MyCardValues()));
        for (Card card : listCards.subList(listCards.size() - numCards, listCards.size())) {
            result.insert(card, false);
        }
        return result;
    }

    public static IScoringStrategy.Score getPairScore(Hand hand, int numCards, int score, String strategyName) {
        hand = tail(hand, numCards);
        if (hand != null) {
            boolean pair = true;
            int firstCard = ((Cribbage.Rank) hand.get(0).getRank()).order;
            for (int i = 1; i < numCards; i++) {
                if (firstCard != ((Cribbage.Rank) hand.get(i).getRank()).order) {
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
        hand = tail(hand, numCards);
        if (hand != null) {
            TreeSet<Integer> cardSet = new TreeSet<>();
            for (int i = 0; i < numCards; i++) {
                cardSet.add(((Cribbage.Rank) hand.get(i).getRank()).order);
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
