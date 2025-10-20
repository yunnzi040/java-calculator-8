package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    // 기본 입력 처리 테스트
    @Test
    void 빈_문자열_입력_테스트() {
        assertSimpleTest(() -> {
            run("");  // 빈 문자열
            assertThat(output()).contains("0");
        });
    }

    @Test
    void null_입력_테스트() {
        assertSimpleTest(() -> {
            run("   ");  // 공백만 있는 문자열
            assertThat(output()).contains("0");
        });
    }

    // 커스텀 구분자 테스트
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("1");
        });
    }

    @Test
    void 커스텀_구분자_형식_오류() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("커스텀 구분자 형식이 올바르지 않습니다.")
        );
    }

    @Test
    void 커스텀_구분자_비어있음() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("구분자는 비어있을 수 없습니다.")
        );

    }

    // 구분자 혼용 테스트
    @Test
    void 커스텀_기본_구분자_혼용() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1,2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("커스텀 구분자와 기본 구분자를 혼용할 수 없습니다.")
        );
    }

    @Test
    void 기본_구분자_외_문자_사용() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("기본 구분자(, 또는 :)만 사용할 수 있습니다.")
        );
    }

    // 숫자 검증 테스트
    @Test
    void 빈_값_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("빈 값은 허용되지 않습니다.")
        );
    }

    @Test
    void 음수_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,-2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("음수는 허용되지 않은 값입니다: -2")
        );
    }

    @Test
    void 숫자가_아닌_값_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("숫자가 아닌 값이 포함되어 있습니다: a")
        );
    }

    // 정상 케이스 테스트
    @Test
    void 기본_구분자_정상_사용() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과: 6");
        });
    }

    @Test
    void 콜론_구분자_정상_사용() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과: 6");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
