package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.LottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.repository.InMemoryLottoBundleRepository;
import lotto.repository.InMemoryStatisticsRepository;
import lotto.repository.LottoBundleRepository;
import lotto.repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoCommandServiceTest {

    private LottoCommandService lottoCommandService;
    private LottoBundleRepository lottoBundleRepository;
    private StatisticsRepository statisticsRepository;

    @BeforeEach
    void setUp() {
        LottoGenerator lottoGenerator = () -> new Lotto(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        lottoBundleRepository = new InMemoryLottoBundleRepository();
        statisticsRepository = new InMemoryStatisticsRepository();
        lottoCommandService = new LottoCommandService(lottoGenerator, lottoBundleRepository, statisticsRepository);
    }

    @Test
    void 로또_구매() {
        lottoCommandService.purchaseLotto(3000);

        assertThat(lottoBundleRepository.find().getLottos()).hasSize(3);
    }

    @Test
    void 잘못된_금액으로_로또_구매시_예외_발생() {
        assertThatThrownBy(() -> lottoCommandService.purchaseLotto(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 단위만 가능합니다");
    }

    @Test
    void 당첨_통계_계산() {
        lottoCommandService.purchaseLotto(3000);

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        lottoCommandService.calculateStatistics(winningNumbers, bonusNumber);

        assertThat(statisticsRepository.find()).isNotNull();
    }

    @Test
    void 잘못된_당첨_번호로_통계_계산시_예외_발생() {
        lottoCommandService.purchaseLotto(1000);

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5);
        int bonusNumber = 7;

        assertThatThrownBy(() -> lottoCommandService.calculateStatistics(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야 합니다");
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복시_예외_발생() {
        lottoCommandService.purchaseLotto(1000);

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        assertThatThrownBy(() -> lottoCommandService.calculateStatistics(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }

    @Test
    void 로또를_구매하지_않고_통계_계산시_예외_발생() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        assertThatThrownBy(() -> lottoCommandService.calculateStatistics(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("저장된 구매 정보가 없습니다");
    }
}
