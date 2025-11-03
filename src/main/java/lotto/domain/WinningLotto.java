package lotto.domain;

import java.util.Optional;

public class WinningLotto {

    private final Lotto winningNumber;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public Optional<Rank> rank(Lotto lotto) {
        int matched = lotto.countMatches(winningNumber);
        boolean bonusMatched = lotto.contains(bonusNumber);
        return Rank.of(matched, bonusMatched);
    }
}
