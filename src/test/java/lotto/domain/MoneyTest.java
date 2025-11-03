package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 정상적인_금액으로_생성() {
        Money money = new Money(5000);
        assertThat(money.amount()).isEqualTo(5000);
    }

    @Test
    void 구매_가능한_로또_개수_계산() {
        Money money = new Money(5000);
        assertThat(money.getPurchases()).isEqualTo(5);
    }

    @Test
    void 금액이_0원_이하면_예외_발생() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0원보다 많아야 합니다");
    }

    @Test
    void 금액이_음수면_예외_발생() {
        assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0원보다 많아야 합니다");
    }

    @Test
    void 금액이_1000원_단위가_아니면_예외_발생() {
        assertThatThrownBy(() -> new Money(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 단위만 가능합니다");
    }
}
