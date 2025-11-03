package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
