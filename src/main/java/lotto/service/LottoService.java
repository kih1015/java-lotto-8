package lotto.service;

import java.util.List;
import lotto.LottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Statistics;
import lotto.domain.VendingMachine;
import lotto.domain.WinningLotto;
import lotto.view.dto.PurchaseHistoryDto;
import lotto.view.dto.StatisticsDto;

public class LottoService {

    private final LottoGenerator lottoGenerator;
    private LottoBundle lottoBundle;
    private Money money;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public PurchaseHistoryDto purchaseLotto(int purchaseAmount) {
        this.money = new Money(purchaseAmount);
        VendingMachine vendingMachine = new VendingMachine(lottoGenerator);
        this.lottoBundle = vendingMachine.buy(money);
        return PurchaseHistoryDto.of(money, lottoBundle);
    }

    public StatisticsDto calculateStatistics(List<Integer> winningNumbers, int bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(winningNumbers.stream().map(LottoNumber::new).toList()),
                new LottoNumber(bonusNumber)
        );
        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        return StatisticsDto.of(statistics, money);
    }
}
