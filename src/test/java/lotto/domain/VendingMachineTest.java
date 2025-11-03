package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.LottoGenerator;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @Test
    void 로또_구매() {
        LottoGenerator generator = () -> new Lotto(
                java.util.List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        VendingMachine vendingMachine = new VendingMachine(generator);
        Money money = new Money(3000);

        LottoBundle bundle = vendingMachine.buy(money);

        assertThat(bundle.getLottos()).hasSize(3);
        assertThat(bundle.getMoney()).isEqualTo(money);
    }

    @Test
    void 로또_단일_구매() {
        LottoGenerator generator = () -> new Lotto(
                java.util.List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        VendingMachine vendingMachine = new VendingMachine(generator);
        Money money = new Money(1000);

        LottoBundle bundle = vendingMachine.buy(money);

        assertThat(bundle.getLottos()).hasSize(1);
    }

    @Test
    void 로또_다량_구매() {
        LottoGenerator generator = () -> new Lotto(
                java.util.List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        VendingMachine vendingMachine = new VendingMachine(generator);
        Money money = new Money(10000);

        LottoBundle bundle = vendingMachine.buy(money);

        assertThat(bundle.getLottos()).hasSize(10);
    }
}
