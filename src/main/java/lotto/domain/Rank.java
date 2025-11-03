package lotto.domain;

import java.util.Optional;

public enum Rank {

    FIFTH(3, 5_000L),
    FOURTH(4, 50_000L),
    THIRD(5, 1_500_000L),
    SECOND(5, 30_000_000L),
    FIRST(6, 2_000_000_000L),
    ;

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Optional<Rank> of(int matched, boolean bonusMatched) {
        if (matched == 6) {
            return Optional.of(FIRST);
        }
        if (matched == 5 && bonusMatched) {
            return Optional.of(SECOND);
        }
        if (matched == 5) {
            return Optional.of(THIRD);
        }
        if (matched == 4) {
            return Optional.of(FOURTH);
        }
        if (matched == 3) {
            return Optional.of(FIFTH);
        }
        return Optional.empty();
    }

    public long prize() {
        return prize;
    }

    public int matchCount() {
        return matchCount;
    }
}

