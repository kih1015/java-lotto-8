package lotto.repository;

import lotto.domain.Statistics;

public class InMemoryStatisticsRepository implements StatisticsRepository {

    private Statistics statistics;

    @Override
    public void save(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public Statistics find() {
        if (statistics == null) {
            throw new IllegalStateException("[ERROR] 저장된 통계 정보가 없습니다.");
        }
        return statistics;
    }
}
