package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StatisticsTest {

    @Test
    void 통계_생성() {
        Map<Rank, Long> winningCounts = new EnumMap<>(Rank.class);
        winningCounts.put(Rank.FIFTH, 1L);
        winningCounts.put(Rank.FOURTH, 0L);
        winningCounts.put(Rank.THIRD, 0L);
        winningCounts.put(Rank.SECOND, 0L);
        winningCounts.put(Rank.FIRST, 0L);

        Statistics statistics = new Statistics(winningCounts);
        assertThat(statistics.getWinningCounts()).containsEntry(Rank.FIFTH, 1L);
    }

    @Test
    void 수익률_계산() {
        Map<Rank, Long> winningCounts = new EnumMap<>(Rank.class);
        winningCounts.put(Rank.FIFTH, 1L);
        winningCounts.put(Rank.FOURTH, 0L);
        winningCounts.put(Rank.THIRD, 0L);
        winningCounts.put(Rank.SECOND, 0L);
        winningCounts.put(Rank.FIRST, 0L);

        Statistics statistics = new Statistics(winningCounts);
        Money money = new Money(5000);

        Double profitRate = statistics.calculateProfitRate(money);
        assertThat(profitRate).isEqualTo(1.0);
    }

    @Test
    void 수익률_계산_복수_당첨() {
        Map<Rank, Long> winningCounts = new EnumMap<>(Rank.class);
        winningCounts.put(Rank.FIFTH, 2L);
        winningCounts.put(Rank.FOURTH, 1L);
        winningCounts.put(Rank.THIRD, 0L);
        winningCounts.put(Rank.SECOND, 0L);
        winningCounts.put(Rank.FIRST, 0L);

        Statistics statistics = new Statistics(winningCounts);
        Money money = new Money(10000);

        Double profitRate = statistics.calculateProfitRate(money);
        assertThat(profitRate).isEqualTo(6.0);
    }

    @Test
    void 통계_복사본_반환() {
        Map<Rank, Long> winningCounts = new EnumMap<>(Rank.class);
        winningCounts.put(Rank.FIFTH, 1L);
        winningCounts.put(Rank.FOURTH, 0L);
        winningCounts.put(Rank.THIRD, 0L);
        winningCounts.put(Rank.SECOND, 0L);
        winningCounts.put(Rank.FIRST, 0L);

        Statistics statistics = new Statistics(winningCounts);
        Map<Rank, Long> returned = statistics.getWinningCounts();
        returned.put(Rank.FIFTH, 100L);

        assertThat(statistics.getWinningCounts().get(Rank.FIFTH)).isEqualTo(1L);
    }
}
