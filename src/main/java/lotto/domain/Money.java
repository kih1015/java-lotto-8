package lotto.domain;

public record Money(Integer amount) {

    public Money {
        validateAmount(amount);
    }

    public int getPurchases() {
        return amount / 1000;
    }

    private void validateAmount(Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매금액은 0원보다 많아야 합니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구매금액은 1000원 단위만 가능합니다.");
        }
    }
}
