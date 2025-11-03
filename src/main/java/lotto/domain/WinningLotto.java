package lotto.domain;

import java.util.Optional;

public class WinningLotto {

    private final Lotto winningNumber;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumber, int bonusNumber) {
        validateBonusNumber(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningNumber, int bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Optional<Rank> rank(Lotto lotto) {
        int matched = lotto.countMatches(winningNumber);
        boolean bonusMatched = lotto.contains(bonusNumber);
        return Rank.of(matched, bonusMatched);
    }
}
