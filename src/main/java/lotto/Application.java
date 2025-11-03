package lotto;

import java.util.List;
import lotto.dto.PurchaseHistoryDto;
import lotto.dto.StatisticsDto;
import lotto.factory.LottoAppFactory;
import lotto.service.LottoCommandService;
import lotto.service.LottoQueryService;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;

public class Application {
    public static void main(String[] args) {
        try {
            LottoAppFactory factory = new LottoAppFactory();

            ConsoleReader reader = factory.createReader();
            ConsoleWriter writer = factory.createWriter();
            LottoCommandService commandService = factory.createCommandService();
            LottoQueryService queryService = factory.createQueryService();

            int purchaseAmount = reader.readPurchaseAmount();
            commandService.purchaseLotto(purchaseAmount);
            PurchaseHistoryDto purchaseHistoryDto = queryService.getPurchaseHistory();
            writer.writePurchaseHistory(purchaseHistoryDto);

            List<Integer> numbers = reader.readWinningNumbers();
            int bonusNumber = reader.readBonusNumber();
            commandService.calculateStatistics(numbers, bonusNumber);
            StatisticsDto statisticsDto = queryService.getStatistics();
            writer.writeStatistics(statisticsDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
