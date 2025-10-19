package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        // 사용자로부터 문자열 입력 받기 (입력 문자열 앞뒤 공백 제거)
        String input = Console.readLine().trim();

        // null 또는 빈 문자열("") 입력 시 0 반환
        if (input.isBlank()) {
            System.out.println("0");
        }

        if (input.startsWith("//")) {
            // 커스텀 구분자
            System.out.println("결과 : " + custom(input));
        } else {
            // 기본 구분자
        }
    }

    public static int custom(String input) {
        String seperator = input.substring(2, input.indexOf("\\n"));
        String numbers = input.substring(input.indexOf("\\n") + 2);
        String[] numbersArray = numbers.split(seperator);
        int sum = 0;

        for (String number : numbersArray) {
            int numberInt = Integer.parseInt(number);
            sum += numberInt;
        }
        return sum;
    }
}