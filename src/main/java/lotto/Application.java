package lotto;

import java.util.List;
import lotto.repository.InMemoryLottoBundleRepository;
import lotto.repository.InMemoryStatisticsRepository;
import lotto.repository.LottoBundleRepository;
import lotto.repository.StatisticsRepository;
import lotto.service.LottoCommandService;
import lotto.service.LottoQueryService;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;
import lotto.view.dto.PurchaseHistoryDto;
import lotto.view.dto.StatisticsDto;

public class Application {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();

        LottoBundleRepository lottoBundleRepository = new InMemoryLottoBundleRepository();
        StatisticsRepository statisticsRepository = new InMemoryStatisticsRepository();

        LottoCommandService commandService = new LottoCommandService(
                new RandomLottoGenerator(), lottoBundleRepository, statisticsRepository);
        LottoQueryService queryService = new LottoQueryService(lottoBundleRepository, statisticsRepository);

        int purchaseAmount = reader.readPurchaseAmount();
        commandService.purchaseLotto(purchaseAmount);
        PurchaseHistoryDto purchaseHistoryDto = queryService.getPurchaseHistory();
        writer.writePurchaseHistory(purchaseHistoryDto);

        List<Integer> numbers = reader.readWinningNumbers();
        int bonusNumber = reader.readBonusNumber();
        commandService.calculateStatistics(numbers, bonusNumber);
        StatisticsDto statisticsDto = queryService.getStatistics();
        writer.writeStatistics(statisticsDto);
    }
}
