package lotto;

public class ConsoleWriter {

    public void writePurchaseHistory(Money money, LottoBundle lottoBundle) {
        String message = String.format("%d개를 구매했습니다.", money.getPurchases());
        System.out.println(message);
        System.out.println(lottoBundle.toString());
    }

    public void writeStatistics(Money money, Statistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(statistics.toString());
        String message = String.format("총 수익률은 %.2f%%입니다.", statistics.calculateProfitRate(money));
        System.out.println(message);
    }
}
