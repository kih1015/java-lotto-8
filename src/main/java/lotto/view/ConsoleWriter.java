package lotto.view;

public class ConsoleWriter {

    public void writePurchaseHistory(int purchaseCount, String lottoInfo) {
        String message = String.format("%d개를 구매했습니다.", purchaseCount);
        System.out.println(message);
        System.out.println(lottoInfo);
    }

    public void writeStatistics(String statisticsInfo, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(statisticsInfo);
        String message = String.format("총 수익률은 %.2f%%입니다.", profitRate);
        System.out.println(message);
    }
}
