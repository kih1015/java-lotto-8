package lotto;

import java.util.List;

public class LottoBundle {

    private final List<Lotto> lottoBundle;

    public LottoBundle(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        lottoBundle.forEach(lotto -> stringBuilder.append(lotto.toString()).append('\n'));
        return stringBuilder.toString();
    }
}
