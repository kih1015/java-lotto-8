package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class Statistics {

    private final Map<Rank, Long> winningCounts;

    public Statistics(Map<Rank, Long> winningCounts) {
        this.winningCounts = new EnumMap<>(winningCounts);
    }

    public Map<Rank, Long> getWinningCounts() {
        return new EnumMap<>(winningCounts);
    }

    public Double calculateProfitRate(Money purchaseAmount) {
        return ((double) calculateProfit()) / purchaseAmount.amount() * 100;
    }

    private Long calculateProfit() {
        return winningCounts.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().prize() * entry.getValue())
                .sum();
    }
}
