package lotto.domain;

public record LottoNumber(Integer number) {

    public LottoNumber {
        validate(number);
    }

    private void validate(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호의 숫자 범위는 1~45 입니다.");
        }
    }
}
