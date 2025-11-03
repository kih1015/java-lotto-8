package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.VendingMachine;
import lotto.domain.WinningLotto;
import lotto.view.ConsoleReader;
import lotto.view.ConsoleWriter;

public class Application {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        VendingMachine vendingMachine = new VendingMachine(new RandomLottoGenerator());

        int purchaseAmount = reader.readPurchaseAmount();
        Money money = new Money(purchaseAmount);
        LottoBundle lottoBundle = vendingMachine.buy(money);
        writer.writePurchaseHistory(money.getPurchases(), lottoBundle.toString());

        List<Integer> numbers = reader.readWinningNumbers();
        int bonusNumber = reader.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(numbers.stream().map(LottoNumber::new).toList()),
                new LottoNumber(bonusNumber)
        );
        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        writer.writeStatistics(statistics.toString(), statistics.calculateProfitRate(money));
    }
}
