package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoBundle {

    private final Money money;
    private final List<Lotto> lottoBundle;

    public LottoBundle(Money money, List<Lotto> lottoBundle) {
        this.money = money;
        this.lottoBundle = lottoBundle;
    }

    public Money getMoney() {
        return money;
    }

    public List<Lotto> getLottos() {
        return lottoBundle;
    }

    public Statistics calculateStatistics(WinningLotto winningLotto) {
        Map<Rank, Long> winningCounts = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        rank -> 0L,
                        (a, b) -> a,
                        () -> new EnumMap<>(Rank.class)
                ));

        lottoBundle.stream()
                .map(winningLotto::rank)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(rank -> winningCounts.merge(rank, 1L, Long::sum));

        return new Statistics(winningCounts);
    }
}
