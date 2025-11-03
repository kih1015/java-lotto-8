package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class Statistics {

    private final Map<Rank, Long> winningCounts;

    public Statistics(Map<Rank, Long> winningCounts) {
        this.winningCounts = new EnumMap<>(winningCounts);
    }

    public Double calculateProfitRate(Money purchaseAmount) {
        return ((double) calculateProfit()) / purchaseAmount.amount();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        winningCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String str = String.format("%d개 일치 (%d원) - %d개",
                            entry.getKey().getMatchCount(),
                            entry.getKey().prize(),
                            entry.getValue()
                    );
                    sb.append(str).append('\n');
                });
        return sb.toString();
    }

    private Long calculateProfit() {
        return winningCounts.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().prize() * entry.getValue())
                .sum();
    }
}
