package lotto.repository;

import lotto.domain.Statistics;

public interface StatisticsRepository {

    void save(Statistics statistics);

    Statistics find();
}
