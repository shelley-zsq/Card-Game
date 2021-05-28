package score;

import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage;

import java.util.List;
import java.util.stream.Collectors;

public interface IScoringStrategy {
    class Score implements Comparable {
        int score;
        String strategyName;
        Hand hand;

        public Score(int score, String strategyName, Hand hand) {
            this.score = score;
            this.strategyName = strategyName;
            this.hand = hand;
        }

        public int getScore() {
            return score;
        }

        public String getStrategyName() {
            return strategyName;
        }

        public Hand getHand() {
            return hand;
        }

        @Override
        public int compareTo(Object o) {
            Score other = (Score) o;
            return Cribbage.cribbage.canonical(hand).compareTo(Cribbage.cribbage.canonical(other.hand));
        }
    }

    public List<Score> getScore(Hand hand);
}
