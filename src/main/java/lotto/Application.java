package lotto;

import java.util.List;
import lotto.repository.InMemoryLottoBundleRepository;
import lotto.repository.LottoBundleRepository;
import lotto.service.LottoService;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;
import lotto.view.dto.PurchaseHistoryDto;
import lotto.view.dto.StatisticsDto;

public class Application {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        LottoBundleRepository repository = new InMemoryLottoBundleRepository();
        LottoService lottoService = new LottoService(new RandomLottoGenerator(), repository);

        int purchaseAmount = reader.readPurchaseAmount();
        PurchaseHistoryDto purchaseHistoryDto = lottoService.purchaseLotto(purchaseAmount);
        writer.writePurchaseHistory(purchaseHistoryDto);

        List<Integer> numbers = reader.readWinningNumbers();
        int bonusNumber = reader.readBonusNumber();
        StatisticsDto statisticsDto = lottoService.calculateStatistics(numbers, bonusNumber);
        writer.writeStatistics(statisticsDto);
    }
}
