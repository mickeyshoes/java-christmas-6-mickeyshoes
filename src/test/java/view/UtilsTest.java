package view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"안녕","안녕02-안넝", "안녕-123", "안녕-2"})
    void regexTest(String input){

    }
}
