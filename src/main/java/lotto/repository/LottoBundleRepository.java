package lotto.repository;

import lotto.domain.LottoBundle;

public interface LottoBundleRepository {

    void save(LottoBundle lottoBundle);

    LottoBundle find();
}
