package lotto;

import java.util.EnumMap;
import java.util.Map;

public class Statistics {

    private final Map<Rank, Long> winningCounts;

    public Statistics(Map<Rank, Long> winningCounts) {
        this.winningCounts = new EnumMap<>(winningCounts);
    }
}
