package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

import java.util.Arrays;

public class ConsoleReader {

    public Money inputMoney() {
        System.out.println("구매금액을 입력해 주세요.");
        String purchasePrice = Console.readLine();
        return new Money(parseInt(purchasePrice));
    }

    public WinningLotto inputWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");
        Lotto winningNumber = new Lotto(Arrays.stream(Console.readLine().split(",")).map(this::parseInt).toList());
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        return new WinningLotto(winningNumber, parseInt(bonusNumber));
    }

    private Integer parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }
}
