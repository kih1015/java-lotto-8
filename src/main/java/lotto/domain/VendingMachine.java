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
        return generateLottoBundle(quantity);
    }

    private LottoBundle generateLottoBundle(int quantity) {
        return new LottoBundle(IntStream.range(0, quantity)
                .mapToObj(i -> generator.generateLotto())
                .toList());
    }
}
