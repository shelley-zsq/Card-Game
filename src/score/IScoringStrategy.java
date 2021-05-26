package score;

import ch.aplu.jcardgame.Hand;
import java.util.List;

public interface IScoringStrategy {
    class Score {
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
    }

    public List<Score> getScore(Hand hand);
}
