package lotto.dto;

import java.util.Map.Entry;
import lotto.domain.Money;
import lotto.domain.Statistics;

public record StatisticsDto(String statisticsInfo, double profitRate) {

    public static StatisticsDto of(Statistics statistics, Money money) {
        return new StatisticsDto(
                formatStatistics(statistics),
                statistics.calculateProfitRate(money)
        );
    }

    private static String formatStatistics(Statistics statistics) {
        StringBuilder sb = new StringBuilder();
        statistics.getWinningCounts()
                .entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEach(entry -> {
                    String string = String.format("%d개 일치 (%d원) - %d개",
                            entry.getKey().matchCount(),
                            entry.getKey().prize(),
                            entry.getValue()
                    );
                    sb.append(string).append('\n');
                });
        return sb.toString();
    }
}
