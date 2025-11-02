package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.stream.IntStream;

public class VendingMachine {

    public LottoBundle buy(Money money) {
        int quantity = money.getPurchases();
        return generateLottoBundle(quantity);
    }

    private LottoBundle generateLottoBundle(int quantity) {
        return new LottoBundle(IntStream.range(0, quantity)
                .mapToObj(i -> new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)))
                .toList());
    }
}
