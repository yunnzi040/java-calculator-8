package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.math.BigInteger;
import java.util.NoSuchElementException;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        try {
            // 사용자로부터 문자열 입력 받기 (입력 문자열 앞뒤 공백 제거)
            String input = Console.readLine().trim();

            BigInteger result = calculate(input);

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
    public static BigInteger calculate(String input) {
        // input이 null이거나 빈 문자열일 경우
        if (input == null || input.isEmpty()) {
            return BigInteger.ZERO;
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
            if (startIndex == -1) { // "\n"이 없을 경우
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            String separator = input.substring(2, startIndex);
            if (separator.isEmpty()) { // 구분자가 비어있을 경우
                throw new IllegalArgumentException("구분자는 비어있을 수 없습니다.");
            }
            return separator;
        } else { // 기본 구분자
            return "[,:]";
        }
    }

    // 숫자 분리 로직
    private static String[] splitNumbers(String input, String separator) {
        if (separator.equals("[,:]")) { // 기본 구분자
            return input.split(separator);
        } else {
            int startIndex = input.indexOf("\\n");
            String numbers = input.substring(startIndex + 2);

            if (!separator.equals("[,:]") && (numbers.contains(",") || numbers.contains(
                    ":"))) { // 커스텀 구분자와 기본 구분자 혼용 검증
                throw new IllegalArgumentException("커스텀 구분자와 기본 구분자를 혼용할 수 없습니다.");
            }
            return numbers.split(separator);
        }
    }

    // 합계 계산 및 검증
    private static BigInteger sumNumbers(String[] numbers) {
        BigInteger sum = BigInteger.ZERO;
        for (String number : numbers) {
            if (number.isBlank()) { // 구분한 값이 비어있을 경우
                throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            }
            try {
                BigInteger value = new BigInteger(number.trim());
                if (value.signum() < 0) { // 구분한 값이 음수일 경우
                    throw new IllegalArgumentException("음수는 허용되지 않은 값입니다 : " + value);
                }
                sum = sum.add(value);
            } catch (NumberFormatException e) { // 숫자가 아닌 값이 포함되어 있을 경우
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다 : " + number.trim());
            }
        }
        return sum;
    }
}


