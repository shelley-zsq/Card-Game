package score;

import score.Show.Flush4;
import score.Show.Flush5;
import score.Show.Pair;
import score.Play.*;

public class ScoringStrategySingletonFactory {
    private static ScoringStrategySingletonFactory instance = new ScoringStrategySingletonFactory();

    public IScoringStrategy getScoringStrategy(String stage) {
        switch (stage) {
            case "STARTER":
                return new Jack();
            case "GO":
                return new Go();
            case "PLAY":
                CompositeScoringStrategy playStrategy = new DifferentCategoryScoringStrategy();
                // Total 15 / 31
                CompositeScoringStrategy totalStrategy = new SameCategoryScoringStrategy();
                totalStrategy.add(new ThirtyOne());
                totalStrategy.add(new Fifteen());
                playStrategy.add(totalStrategy);
                // Runs run7 / run6 / run5 / run4 / run3
                CompositeScoringStrategy runStrategy = new SameCategoryScoringStrategy();
                runStrategy.add(new Run7());
                runStrategy.add(new Run6());
                runStrategy.add(new Run5());
                runStrategy.add(new Run4());
                runStrategy.add(new Run3());
                playStrategy.add(runStrategy);
                // Pairs pair4 / pair3 / pair2
                CompositeScoringStrategy pairsStrategy = new SameCategoryScoringStrategy();
                pairsStrategy.add(new Pair4());
                pairsStrategy.add(new Pair3());
                pairsStrategy.add(new Pair2());
                playStrategy.add(pairsStrategy);
                return playStrategy;
            case "SHOW":
                CompositeScoringStrategy showStrategy = new DifferentCategoryScoringStrategy();
                // 15
                showStrategy.add(new score.Show.Fifteen());
                // run5 run4 run3
                runStrategy = new SameCategoryScoringStrategy();
                runStrategy.add(new score.Show.Run5());
                runStrategy.add(new score.Show.Run4());
                runStrategy.add(new score.Show.Run3());
                showStrategy.add(runStrategy);
                // pair4 pair3 pair2
                showStrategy.add(new Pair());
                // flush5 flush4
                CompositeScoringStrategy flushStrategy = new SameCategoryScoringStrategy();
                flushStrategy.add(new Flush5());
                flushStrategy.add(new Flush4());
                showStrategy.add(flushStrategy);
                return showStrategy;
            default:
                return null;
        }
    }

    public static ScoringStrategySingletonFactory getInstance() {
        return instance;
    }
}
