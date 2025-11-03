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
                    String matchDescription = entry.getKey().matchCount() + "개 일치";
                    if (entry.getKey().matchCount() == 5 && entry.getKey().prize() == 30_000_000L) {
                        matchDescription = "5개 일치, 보너스 볼 일치";
                    }
                    String string = String.format("%s (%,d원) - %d개",
                            matchDescription,
                            entry.getKey().prize(),
                            entry.getValue()
                    );
                    sb.append(string).append('\n');
                });
        return sb.toString();
    }
}
