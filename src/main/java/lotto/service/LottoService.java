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
import lotto.repository.LottoBundleRepository;
import lotto.view.dto.PurchaseHistoryDto;
import lotto.view.dto.StatisticsDto;

public class LottoService {

    private final LottoGenerator lottoGenerator;
    private final LottoBundleRepository lottoBundleRepository;

    public LottoService(LottoGenerator lottoGenerator, LottoBundleRepository lottoBundleRepository) {
        this.lottoGenerator = lottoGenerator;
        this.lottoBundleRepository = lottoBundleRepository;
    }

    public PurchaseHistoryDto purchaseLotto(int purchaseAmount) {
        Money money = new Money(purchaseAmount);
        VendingMachine vendingMachine = new VendingMachine(lottoGenerator);
        LottoBundle lottoBundle = vendingMachine.buy(money);

        lottoBundleRepository.save(lottoBundle);

        return PurchaseHistoryDto.of(money, lottoBundle);
    }

    public StatisticsDto calculateStatistics(List<Integer> winningNumbers, int bonusNumber) {
        LottoBundle lottoBundle = lottoBundleRepository.find();

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(winningNumbers.stream().map(LottoNumber::new).toList()),
                new LottoNumber(bonusNumber)
        );
        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        return StatisticsDto.of(statistics, lottoBundle.getMoney());
    }
}
