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
import lotto.repository.StatisticsRepository;

public class LottoCommandService {

    private final LottoGenerator lottoGenerator;
    private final LottoBundleRepository lottoBundleRepository;
    private final StatisticsRepository statisticsRepository;

    public LottoCommandService(
            LottoGenerator lottoGenerator,
            LottoBundleRepository lottoBundleRepository,
            StatisticsRepository statisticsRepository
    ) {
        this.lottoGenerator = lottoGenerator;
        this.lottoBundleRepository = lottoBundleRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public void purchaseLotto(int purchaseAmount) {
        Money money = new Money(purchaseAmount);
        VendingMachine vendingMachine = new VendingMachine(lottoGenerator);
        LottoBundle lottoBundle = vendingMachine.buy(money);
        lottoBundleRepository.save(lottoBundle);
    }

    public void calculateStatistics(List<Integer> winningNumbers, int bonusNumber) {
        LottoBundle lottoBundle = lottoBundleRepository.find();
        List<LottoNumber> lottoNumbers = winningNumbers.stream().map(LottoNumber::new).toList();
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(lottoNumbers),
                new LottoNumber(bonusNumber)
        );
        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        statisticsRepository.save(statistics);
    }
}
