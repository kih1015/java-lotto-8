package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("구매금액을 입력해 주세요.");
        String purchasePrice = Console.readLine();
        Money money = new Money(Integer.parseInt(purchasePrice));
    }
}
