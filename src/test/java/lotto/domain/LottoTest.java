package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 정상적인_로또_생성() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 일치하는_번호_개수_확인() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        Lotto winning = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
        ));

        assertThat(lotto.countMatches(winning)).isEqualTo(3);
    }

    @Test
    void 특정_번호_포함_여부_확인() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
        assertThat(lotto.contains(new LottoNumber(7))).isFalse();
    }

    @Test
    void 번호_개수가_6개가_아니면_예외_발생() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야 합니다");
    }

    @Test
    void 중복된_번호가_있으면_예외_발생() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1), new LottoNumber(1), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다");
    }
}
