package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void 정상적인_로또_번호_생성() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertThat(lottoNumber.number()).isEqualTo(1);
    }

    @Test
    void 최대값_로또_번호_생성() {
        LottoNumber lottoNumber = new LottoNumber(45);
        assertThat(lottoNumber.number()).isEqualTo(45);
    }

    @Test
    void 범위_미만_번호_예외_발생() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }

    @Test
    void 범위_초과_번호_예외_발생() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }
}
