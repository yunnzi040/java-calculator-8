package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        try {
            // 사용자로부터 문자열 입력 받기 (입력 문자열 앞뒤 공백 제거)
            String input = Console.readLine().trim();

            int result = calculate(input);

            System.out.println("결과 : " + result);


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

    // 전체 흐름
    public static int calculate(String input) {
        // input이 null이거나 빈 문자열일 경우
        if (input == null || input.isEmpty()) {
            return 0;
        }
        // 구분자 추출
        String separator = extractSeparator(input);

        // 숫자 분리
        String[] numbers = splitNumbers(input, separator);

        // 합계 계산
        return sumNumbers(numbers);
    }

    // 구분자 추출 로직
    private static String extractSeparator(String input) {
        if (input.startsWith("//")) { // 커스텀 구분자
            int startIndex = input.indexOf("\\n");
            if (startIndex == -1) { //
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            String separator = input.substring(2, startIndex);
            if (separator.isEmpty()) {
                throw new IllegalArgumentException("구분자는 비어있을 수 없습니다.");
            }
            return input.substring(2, startIndex);
        } else { // 기본 구분자
            return "[,:]";
        }
    }

    // 숫자 분리 로직
    private static String[] splitNumbers(String input, String separator) {
        if (separator.equals("[,:]")) { // 기본 구분자
//            if (!input.matches(".*[^\\d\\s,:\\-A-Za-z].*")) {
//                throw new IllegalArgumentException("기본 구분자(, 또는 :)만 사용할 수 있습니다.");
//            }
            return input.split(separator);
        } else {
            int startIndex = input.indexOf("\\n");
            String numbers = input.substring(startIndex + 2);

            if (numbers.contains(",") || numbers.contains(":")) { // 커스텀 구분자와 기본 구분자 혼용 검증
                throw new IllegalArgumentException("커스텀 구분자와 기본 구분자를 혼용할 수 없습니다.");
            }
            return numbers.split(separator);
        }
    }

    // 합계 계산 및 검증
    private static int sumNumbers(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            if (number.isBlank()) {
                throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            }
            try {
                int numberInt = Integer.parseInt(number.trim());
                if (numberInt < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않은 값입니다 : " + numberInt);
                }
                sum += numberInt;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다 : " + number.trim());
            }
        }
        return sum;
    }
}


