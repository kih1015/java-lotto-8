package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.LottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Statistics;
import lotto.dto.PurchaseHistoryDto;
import lotto.dto.StatisticsDto;
import lotto.repository.InMemoryLottoBundleRepository;
import lotto.repository.InMemoryStatisticsRepository;
import lotto.repository.LottoBundleRepository;
import lotto.repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoQueryServiceTest {

    private LottoQueryService lottoQueryService;
    private LottoBundleRepository lottoBundleRepository;
    private StatisticsRepository statisticsRepository;

    @BeforeEach
    void setUp() {
        lottoBundleRepository = new InMemoryLottoBundleRepository();
        statisticsRepository = new InMemoryStatisticsRepository();
        lottoQueryService = new LottoQueryService(lottoBundleRepository, statisticsRepository);
    }

    @Test
    void 구매_이력_조회() {
        Money money = new Money(3000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12))),
                new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                        new LottoNumber(16), new LottoNumber(17), new LottoNumber(18)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);
        lottoBundleRepository.save(lottoBundle);

        PurchaseHistoryDto result = lottoQueryService.getPurchaseHistory();

        assertThat(result).isNotNull();
        assertThat(result.purchaseCount()).isEqualTo(3);
    }

    @Test
    void 통계_조회() {
        Money money = new Money(5000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12))),
                new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                        new LottoNumber(16), new LottoNumber(17), new LottoNumber(18))),
                new Lotto(List.of(new LottoNumber(19), new LottoNumber(20), new LottoNumber(21),
                        new LottoNumber(22), new LottoNumber(23), new LottoNumber(24))),
                new Lotto(List.of(new LottoNumber(25), new LottoNumber(26), new LottoNumber(27),
                        new LottoNumber(28), new LottoNumber(29), new LottoNumber(30)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);
        lottoBundleRepository.save(lottoBundle);

        Map<Rank, Long> winningCounts = new EnumMap<>(Rank.class);
        winningCounts.put(Rank.FIFTH, 1L);
        winningCounts.put(Rank.FOURTH, 0L);
        winningCounts.put(Rank.THIRD, 0L);
        winningCounts.put(Rank.SECOND, 0L);
        winningCounts.put(Rank.FIRST, 0L);
        Statistics statistics = new Statistics(winningCounts);
        statisticsRepository.save(statistics);

        StatisticsDto result = lottoQueryService.getStatistics();

        assertThat(result).isNotNull();
        assertThat(result.profitRate()).isEqualTo(1.0);
    }

    @Test
    void 구매한_로또가_여러개일_때_조회() {
        Money money = new Money(10000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12))),
                new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                        new LottoNumber(16), new LottoNumber(17), new LottoNumber(18))),
                new Lotto(List.of(new LottoNumber(19), new LottoNumber(20), new LottoNumber(21),
                        new LottoNumber(22), new LottoNumber(23), new LottoNumber(24))),
                new Lotto(List.of(new LottoNumber(25), new LottoNumber(26), new LottoNumber(27),
                        new LottoNumber(28), new LottoNumber(29), new LottoNumber(30))),
                new Lotto(List.of(new LottoNumber(31), new LottoNumber(32), new LottoNumber(33),
                        new LottoNumber(34), new LottoNumber(35), new LottoNumber(36))),
                new Lotto(List.of(new LottoNumber(37), new LottoNumber(38), new LottoNumber(39),
                        new LottoNumber(40), new LottoNumber(41), new LottoNumber(42))),
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(3), new LottoNumber(5),
                        new LottoNumber(7), new LottoNumber(9), new LottoNumber(11))),
                new Lotto(List.of(new LottoNumber(2), new LottoNumber(4), new LottoNumber(6),
                        new LottoNumber(8), new LottoNumber(10), new LottoNumber(12))),
                new Lotto(List.of(new LottoNumber(14), new LottoNumber(16), new LottoNumber(18),
                        new LottoNumber(20), new LottoNumber(22), new LottoNumber(24)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);
        lottoBundleRepository.save(lottoBundle);

        PurchaseHistoryDto result = lottoQueryService.getPurchaseHistory();

        assertThat(result.purchaseCount()).isEqualTo(10);
    }

    @Test
    void 구매_이력이_없을_때_조회시_예외_발생() {
        assertThatThrownBy(() -> lottoQueryService.getPurchaseHistory())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("저장된 구매 정보가 없습니다");
    }

    @Test
    void 통계가_없을_때_조회시_예외_발생() {
        Money money = new Money(1000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);
        lottoBundleRepository.save(lottoBundle);

        assertThatThrownBy(() -> lottoQueryService.getStatistics())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("저장된 통계 정보가 없습니다");
    }
}
