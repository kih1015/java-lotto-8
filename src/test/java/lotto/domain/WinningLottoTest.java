package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 정상적인_당첨_로또_생성() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        assertThat(winningLotto).isNotNull();
    }

    @Test
    void 일등_등수_계산() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new LottoNumber(7)
        );

        Lotto myLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        Optional<Rank> rank = winningLotto.rank(myLotto);
        assertThat(rank).contains(Rank.FIRST);
    }

    @Test
    void 이등_등수_계산() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new LottoNumber(7)
        );

        Lotto myLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(7)
        ));

        Optional<Rank> rank = winningLotto.rank(myLotto);
        assertThat(rank).contains(Rank.SECOND);
    }

    @Test
    void 삼등_등수_계산() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new LottoNumber(7)
        );

        Lotto myLotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(8)
        ));

        Optional<Rank> rank = winningLotto.rank(myLotto);
        assertThat(rank).contains(Rank.THIRD);
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외_발생() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }
}
