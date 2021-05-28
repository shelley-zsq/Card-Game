package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import score.IScoringStrategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Utils {
    // get the last numCards in hand
    public static Hand tail(Hand hand, int numCards) {
        if (hand.getNumberOfCards() < numCards) {
            return null;
        }

        List<Card> listCards = hand.getCardList();
        Hand result = newHand();
        for (Card card : listCards.subList(listCards.size() - numCards, listCards.size())) {
            result.insert(card.clone(), false);
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

    // returns the hand of pairs, assume the hand given is sorted.
    public static Hand containsPair(Hand hand, int numCards) {
        for (int i = 0; i <= hand.getNumberOfCards() - numCards; i++) {
            Hand result = newHand();
            result.insert(hand.get(i).clone(), false);
            for (int j = 1; j < numCards; j++) {
                if (hand.get(i).getRank() != hand.get(i + j).getRank()) {
                    return null;
                }
                result.insert(hand.get(i + j).clone(), false);
            }
            return result;
        }
        return null;
    }

    public static IScoringStrategy.Score getRunScore(Hand hand, int numCards, int score, String strategyName) {
        hand = tail(hand, numCards);
        if (hand != null) {
            List<Card> listCard = hand.getCardList();
            List<Integer> ranks = new ArrayList<>();
            int minRank = 14;
            for (Card card : listCard) {
                int rank = ((Cribbage.Rank) card.getRank()).order;
                ranks.add(rank);
                if (minRank > rank) {
                    minRank = rank;
                }
            }
            boolean run = true;
            for (int i = 1; i < listCard.size(); i++) {
                if (!ranks.contains(minRank + i)) {
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

    public static int computeTotalValue(Hand hand) {
        return Cribbage.cribbage.total(hand);
    }

    public static Hand newHand(){
        return new Hand(Cribbage.cribbage.deck);
    }

    public static Hand newHand(Hand hand) {
        Hand result = newHand();
        for (Card card : hand.getCardList()) {
            result.insert(card.clone(), false);
        }
        return result;
    }


    public static void combine(Hand hand, int currentIndex, Hand tmpHand, int targetLength, List<Hand> result) {
        if (tmpHand.getNumberOfCards() == targetLength) {
            result.add(tmpHand);
            return;
        }
        if (currentIndex < hand.getNumberOfCards()) {
            Hand withCurrent = Utils.newHand(tmpHand);
            withCurrent.insert(hand.get(currentIndex).clone(), false);
            combine(hand, currentIndex + 1, withCurrent, targetLength, result);
            combine(hand, currentIndex + 1, tmpHand, targetLength, result);
        }
    }

    public static void createFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("cribbage.log"));
            out.close();
        } catch (IOException e) {}
    }

    public static void appendToFile(String line) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("cribbage.log", true));
            out.append(line);
            out.close();
        } catch (IOException e) {}
    }
}
