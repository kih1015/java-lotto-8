package lotto.dto;

import lotto.domain.LottoBundle;
import lotto.domain.Money;

public record PurchaseHistoryDto(int purchaseCount, String lottoInfo) {

    public static PurchaseHistoryDto of(Money money, LottoBundle lottoBundle) {
        return new PurchaseHistoryDto(money.getPurchases(), formatLottoBundle(lottoBundle));
    }

    private static String formatLottoBundle(LottoBundle lottoBundle) {
        StringBuilder stringBuilder = new StringBuilder();
        lottoBundle.getLottos()
                .forEach(lotto -> stringBuilder
                        .append(lotto.toString())
                        .append('\n')
                );
        return stringBuilder.toString();
    }
}
