package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void 일등_당첨() {
        Optional<Rank> rank = Rank.of(6, false);
        assertThat(rank).contains(Rank.FIRST);
        assertThat(rank.get().prize()).isEqualTo(2_000_000_000L);
    }

    @Test
    void 이등_당첨() {
        Optional<Rank> rank = Rank.of(5, true);
        assertThat(rank).contains(Rank.SECOND);
        assertThat(rank.get().prize()).isEqualTo(30_000_000L);
    }

    @Test
    void 삼등_당첨() {
        Optional<Rank> rank = Rank.of(5, false);
        assertThat(rank).contains(Rank.THIRD);
        assertThat(rank.get().prize()).isEqualTo(1_500_000L);
    }

    @Test
    void 사등_당첨() {
        Optional<Rank> rank = Rank.of(4, false);
        assertThat(rank).contains(Rank.FOURTH);
        assertThat(rank.get().prize()).isEqualTo(50_000L);
    }

    @Test
    void 오등_당첨() {
        Optional<Rank> rank = Rank.of(3, false);
        assertThat(rank).contains(Rank.FIFTH);
        assertThat(rank.get().prize()).isEqualTo(5_000L);
    }

    @Test
    void 당첨되지_않음() {
        assertThat(Rank.of(2, false)).isEmpty();
        assertThat(Rank.of(1, false)).isEmpty();
        assertThat(Rank.of(0, false)).isEmpty();
    }

    @Test
    void 매치_카운트_확인() {
        assertThat(Rank.FIRST.matchCount()).isEqualTo(6);
        assertThat(Rank.SECOND.matchCount()).isEqualTo(5);
        assertThat(Rank.THIRD.matchCount()).isEqualTo(5);
        assertThat(Rank.FOURTH.matchCount()).isEqualTo(4);
        assertThat(Rank.FIFTH.matchCount()).isEqualTo(3);
    }
}
