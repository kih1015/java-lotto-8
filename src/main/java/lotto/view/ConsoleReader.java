package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class ConsoleReader {

    public int readPurchaseAmount() {
        System.out.println("구매금액을 입력해 주세요.");
        return parseInt(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        System.out.println();
        return Arrays.stream(Console.readLine().split(","))
                .map(this::parseInt)
                .toList();
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        System.out.println();
        return parseInt(Console.readLine());
    }

    private int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }
}
