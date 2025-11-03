package lotto.domain;

import lotto.LottoGenerator;

import java.util.stream.IntStream;

public class VendingMachine {

    private final LottoGenerator generator;

    public VendingMachine(LottoGenerator generator) {
        this.generator = generator;
    }

    public LottoBundle buy(Money money) {
        int quantity = money.getPurchases();
        return generateLottoBundle(money, quantity);
    }

    private LottoBundle generateLottoBundle(Money money, int quantity) {
        return new LottoBundle(money, IntStream.range(0, quantity)
                .mapToObj(i -> generator.generateLotto())
                .toList());
    }
}
