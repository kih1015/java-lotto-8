package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6).stream().map(LottoNumber::new).toList());
    }
}
