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
    }

    public List<Score> getScore(Hand hand);
}
