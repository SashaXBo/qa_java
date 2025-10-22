package com.example;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionParameterizedTest {

    @Mock
    Feline iFelineMock;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testLionManeBasedOnSex(String sex, boolean expectedMane) throws Exception {
        Lion lion = new Lion(sex, iFelineMock);
        assertEquals(expectedMane, lion.doesHaveMane());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Другой", "мужчина", "женщина", "123", "null", "САМЕЦ", "самец"})
    void testConstructorThrowsExceptionForInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> new Lion(invalidSex, iFelineMock));
        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 5, 10})
    void testGetKittensAlwaysReturnsZero(int inputValue) throws Exception {
        Lion lion = new Lion("Самец", iFelineMock);
        int result = lion.getKittens();
        assertEquals(0, result); // Lion всегда возвращает 0
    }

    @ParameterizedTest
    @MethodSource("provideFoodLists")
    void testGetFoodWithDifferentLists(List<String> foodList) throws Exception {
        Lion lion = spy(new Lion("Самка", iFelineMock));

        doReturn(foodList).when(lion).getFood();

        List<String> result = lion.getFood();
        assertEquals(foodList, result);
    }

    @ParameterizedTest
    @MethodSource("provideValidSexValues")
    void testValidSexValuesDoNotThrowException(String validSex) {
        assertDoesNotThrow(() -> {
            new Lion(validSex, iFelineMock);
        });
    }

    private static Stream<List<String>> provideFoodLists() {
        return Stream.of(
                Arrays.asList("Мясо"),
                Arrays.asList("Мясо", "Рыба"),
                Arrays.asList("Мясо", "Птица", "Рыба"),
                Arrays.asList("Антилопа", "Зебра", "Буйвол"),
                Arrays.asList() // пустой список
        );
    }

    private static Stream<String> provideValidSexValues() {
        return Stream.of("Самец", "Самка");
    }
}
