package lotto;

import lotto.domain.*;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;

public class Application {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        VendingMachine vendingMachine = new VendingMachine(new RandomLottoGenerator());

        Money money = reader.inputMoney();
        LottoBundle lottoBundle = vendingMachine.buy(money);
        writer.writePurchaseHistory(money, lottoBundle);

        WinningLotto winningLotto = reader.inputWinningLotto();
        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        writer.writeStatistics(money, statistics);
    }
}
