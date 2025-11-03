package lotto.service;

import lotto.domain.LottoBundle;
import lotto.domain.Statistics;
import lotto.repository.LottoBundleRepository;
import lotto.repository.StatisticsRepository;
import lotto.dto.PurchaseHistoryDto;
import lotto.dto.StatisticsDto;

public class LottoQueryService {

    private final LottoBundleRepository lottoBundleRepository;
    private final StatisticsRepository statisticsRepository;

    public LottoQueryService(LottoBundleRepository lottoBundleRepository, StatisticsRepository statisticsRepository) {
        this.lottoBundleRepository = lottoBundleRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public PurchaseHistoryDto getPurchaseHistory() {
        LottoBundle lottoBundle = lottoBundleRepository.find();
        return PurchaseHistoryDto.of(lottoBundle.getMoney(), lottoBundle);
    }

    public StatisticsDto getStatistics() {
        LottoBundle lottoBundle = lottoBundleRepository.find();
        Statistics statistics = statisticsRepository.find();
        return StatisticsDto.of(statistics, lottoBundle.getMoney());
    }
}
