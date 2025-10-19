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
        } else {
            System.out.println(input);
        }
    }
}