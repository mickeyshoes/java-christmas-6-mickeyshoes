package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static view.InputValidator.INPUT_PATTERN;

public class UtilsTest {

    static Stream<Arguments> generateOrderArgs(){
        return
                Stream.of(
                        Arguments.of("안녕", false),
                        Arguments.of("안녕02-안넝", false),
                        Arguments.of("안녕-123", true),
                        Arguments.of("안녕-2", true)
                );
    }

    static Stream<Arguments> generateIntValueArgs(){
        return
                Stream.of(
                        Arguments.of(222, "222"),
                        Arguments.of(2222, "2,222"),
                        Arguments.of(2222222, "2,222,222")
                );
    }

    static Stream<Arguments> generateStrings(){
        return
                Stream.of(
                        Arguments.of("Hello", "NOTIN", "Hello"),
                        Arguments.of("", "NOTIN", "NOTIN"),
                        Arguments.of(null, "NOTIN", "NOTIN")
                );
    }

    @DisplayName("사용자의 메뉴 주문이 올바른지 확인")
    @ParameterizedTest
    @MethodSource("generateOrderArgs")
    void regexTest(String input, boolean expect){
        assertThat(utils.isMatchString(INPUT_PATTERN, input)).isEqualTo(expect);
    }

    @DisplayName("숫자를 세자리마다 끊어주는지 확인")
    @ParameterizedTest
    @MethodSource("generateIntValueArgs")
    void valueFormatStringTest(int input, String expect){
        assertThat(utils.valueToFormatString(input)).isEqualTo(expect);
    }

    @DisplayName("유효하지 않은 문자 검증")
    @ParameterizedTest
    @MethodSource("generateStrings")
    void checkBlakString(String target, String replace, String expect){
        assertThat(utils.checkTargetExist(target, replace)).isEqualTo(expect);
    }
}
