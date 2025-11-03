package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        VendingMachine vendingMachine = new VendingMachine();

        Money money = reader.inputMoney();

        LottoBundle lottoBundle = vendingMachine.buy(money);

        String message = String.format("%d개를 구매했습니다.", money.getPurchases());
        System.out.println(message);
        System.out.println(lottoBundle.toString());

        WinningLotto winningLotto = reader.inputWinningLotto();

        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);

        System.out.println(statistics.toString());
        System.out.println(statistics.calculateProfitRate(money));
    }
}
