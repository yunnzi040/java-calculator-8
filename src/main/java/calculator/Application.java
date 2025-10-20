package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        try {
            // 사용자로부터 문자열 입력 받기 (입력 문자열 앞뒤 공백 제거)
            String input = Console.readLine().trim();

            int result;
            if (input.startsWith("//")) {
                // 커스텀 구분자
                result = custom(input);
            } else {
                // 기본 구분자
                result = basic(input);
            }

            System.out.println("결과: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (NoSuchElementException e) {
            System.out.println("0");
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
            throw e;
        }
    }

    public static int custom(String input) {
        int startIndex = input.indexOf("\\n");

        // "\n"이 없을 경우
        if (startIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        String seperator = input.substring(2, startIndex);
        String numbers = input.substring(startIndex + 2);

        // 구분자가 비어있을 경우
        if (seperator.isEmpty()) {
            throw new IllegalArgumentException("구분자는 비어있을 수 없습니다.");
        }

        String[] numbersArray = numbers.split(seperator);

        // 기본 구분자가 포함되어 있을 경우
        if (numbers.contains(",") || numbers.contains(":")) {
            throw new IllegalArgumentException("커스텀 구분자와 기본 구분자를 혼용할 수 없습니다.");
        }

        return sum(numbersArray);
    }

    public static int basic(String input) {
        // 숫자와 기본 구분자, 공백만 허용
        if (!input.matches("^[0-9,\\s:]+$")) {
            String[] parts = input.split("[,:]");
            for (String part : parts) {
                // 기본 구분자로 분할했는데 숫자가 아닌 부분이 있을 경우
                if (!part.trim().matches("^\\d+$")) {
                    throw new IllegalArgumentException("기본 구분자(, 또는 :)만 사용할 수 있습니다.");
                }
            }
        }
        
        String[] numbersArray = input.split("[,:]");
        return sum(numbersArray);
    }

    public static int sum(String[] numbers) {
        int sum = 0;

        for (String number : numbers) {
            // 구분한 값이 비어있을 경우
            if (number.isBlank()) {
                throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            }
            try {
                int numberInt = Integer.parseInt(number);

                // 구분된 값이 음수일 경우
                if (numberInt < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않은 값입니다: " + numberInt);
                }
                sum += numberInt;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + number);
            }
        }
        return sum;
    }
}