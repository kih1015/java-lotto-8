package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumber;
import lotto.domain.Money;

public record PurchaseHistoryDto(int purchaseCount, String lottoInfo) {

    public static PurchaseHistoryDto of(Money money, LottoBundle lottoBundle) {
        return new PurchaseHistoryDto(money.getPurchases(), formatLottoBundle(lottoBundle));
    }

    private static String formatLottoBundle(LottoBundle lottoBundle) {
        StringBuilder stringBuilder = new StringBuilder();
        lottoBundle.getLottos()
                .forEach(lotto -> stringBuilder
                        .append(formatLotto(lotto))
                        .append('\n')
                );
        return stringBuilder.toString();
    }

    private static String formatLotto(Lotto lotto) {
        List<String> lottoNumbers = lotto.getNumbers()
                .stream()
                .map(LottoNumber::number)
                .map(String::valueOf)
                .toList();
        String format = String.join(", ", lottoNumbers);
        return String.format("[%s]", format);
    }
}
