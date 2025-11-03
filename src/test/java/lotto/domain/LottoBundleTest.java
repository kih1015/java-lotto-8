package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottoBundleTest {

    @Test
    void 로또_묶음_생성() {
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
        assertThat(lottoBundle.getLottos()).hasSize(3);
        assertThat(lottoBundle.getMoney()).isEqualTo(money);
    }

    @Test
    void 당첨_통계_계산() {
        Money money = new Money(3000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(7))),
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new LottoNumber(7)
        );

        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        Map<Rank, Long> winningCounts = statistics.getWinningCounts();

        assertThat(winningCounts.get(Rank.FIRST)).isEqualTo(1L);
        assertThat(winningCounts.get(Rank.SECOND)).isEqualTo(1L);
        assertThat(winningCounts.get(Rank.FIFTH)).isEqualTo(1L);
    }

    @Test
    void 당첨_없는_통계_계산() {
        Money money = new Money(2000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(10),
                        new LottoNumber(11), new LottoNumber(12), new LottoNumber(13))),
                new Lotto(List.of(new LottoNumber(14), new LottoNumber(15), new LottoNumber(16),
                        new LottoNumber(17), new LottoNumber(18), new LottoNumber(19)))
        );
        LottoBundle lottoBundle = new LottoBundle(money, lottos);

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new LottoNumber(7)
        );

        Statistics statistics = lottoBundle.calculateStatistics(winningLotto);
        Map<Rank, Long> winningCounts = statistics.getWinningCounts();

        assertThat(winningCounts.values().stream().mapToLong(Long::longValue).sum()).isEqualTo(0L);
    }
}
