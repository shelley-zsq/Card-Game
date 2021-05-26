package score;

import score.Play.*;

public class ScoringStrategySingletonFactory {
    private static ScoringStrategySingletonFactory instance = new ScoringStrategySingletonFactory();

    public IScoringStrategy getScoringStrategy(String stage) {
        switch (stage) {
            case "GO":
                return new LastCard();
            case "PLAY":
                CompositeScoringStrategy playStrategy = new DifferentCategoryScoringStrategy();
                // Total 15 / 31
                CompositeScoringStrategy totalStrategy = new SameCategoryScoringStrategy();
                totalStrategy.add(new ThirtyOne());
                totalStrategy.add(new Fifteen());
                playStrategy.add(totalStrategy);
                // Runs run7 / run6 / run5 / run4 / run3
                CompositeScoringStrategy runsStrategy = new SameCategoryScoringStrategy();
                runsStrategy.add(new Run7());
                runsStrategy.add(new Run6());
                runsStrategy.add(new Run5());
                runsStrategy.add(new Run4());
                runsStrategy.add(new Run3());
                playStrategy.add(runsStrategy);
                // Pairs pair4 / pair3 / pair2
                CompositeScoringStrategy pairsStrategy = new SameCategoryScoringStrategy();
                pairsStrategy.add(new Pair4());
                pairsStrategy.add(new Pair3());
                pairsStrategy.add(new Pair2());
                playStrategy.add(pairsStrategy);
                return playStrategy;
            case "SHOW":
                return null;
            default:
                return null;
        }
    }

    public static ScoringStrategySingletonFactory getInstance() {
        return instance;
    }
}
