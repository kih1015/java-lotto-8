package lotto.factory;

import lotto.RandomLottoGenerator;
import lotto.repository.InMemoryLottoBundleRepository;
import lotto.repository.InMemoryStatisticsRepository;
import lotto.repository.LottoBundleRepository;
import lotto.repository.StatisticsRepository;
import lotto.service.LottoCommandService;
import lotto.service.LottoQueryService;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;

public class LottoAppFactory {

    private final LottoBundleRepository lottoBundleRepository;
    private final StatisticsRepository statisticsRepository;

    public LottoAppFactory() {
        this.lottoBundleRepository = new InMemoryLottoBundleRepository();
        this.statisticsRepository = new InMemoryStatisticsRepository();
    }

    public ConsoleReader createReader() {
        return new ConsoleReader();
    }

    public ConsoleWriter createWriter() {
        return new ConsoleWriter();
    }

    public LottoCommandService createCommandService() {
        return new LottoCommandService(
                new RandomLottoGenerator(),
                lottoBundleRepository,
                statisticsRepository
        );
    }

    public LottoQueryService createQueryService() {
        return new LottoQueryService(lottoBundleRepository, statisticsRepository);
    }
}
