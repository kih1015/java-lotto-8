package lotto.repository;

import lotto.domain.LottoBundle;

public class InMemoryLottoBundleRepository implements LottoBundleRepository {

    private LottoBundle lottoBundle;

    @Override
    public void save(LottoBundle lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    @Override
    public LottoBundle find() {
        if (lottoBundle == null) {
            throw new IllegalStateException("[ERROR] 저장된 구매 정보가 없습니다.");
        }
        return lottoBundle;
    }
}
