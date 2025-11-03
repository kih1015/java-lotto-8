package lotto.view;

import lotto.view.dto.PurchaseHistoryDto;
import lotto.view.dto.StatisticsDto;

public class ConsoleWriter {

    public void writePurchaseHistory(PurchaseHistoryDto dto) {
        String message = String.format("%d개를 구매했습니다.", dto.purchaseCount());
        System.out.println(message);
        System.out.println(dto.lottoInfo());
    }

    public void writeStatistics(StatisticsDto dto) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(dto.statisticsInfo());
        String message = String.format("총 수익률은 %.2f%%입니다.", dto.profitRate());
        System.out.println(message);
    }
}
