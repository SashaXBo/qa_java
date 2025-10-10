package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    Feline felineMock;

    @Test
    void testGetFoodThrowsExceptionWhenFelineThrows() throws Exception {
        Lion lion = spy(new Lion("Самец", felineMock));

        doThrow(new Exception("Ошибка получения еды")).when(lion).getFood();

        Exception exception = assertThrows(Exception.class, () -> lion.getFood());

        assertEquals("Ошибка получения еды", exception.getMessage());
    }

    @Test
    void testGetKittensReturnsZero() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        assertEquals(0, lion.getKittens());
    }

    @Test
    void testGetFoodWithRealImplementation() throws Exception {
        Lion lion = spy(new Lion("Самка", felineMock));
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");

        doReturn(expectedFood).when(lion).getFood();

        List<String> result = lion.getFood();
        assertEquals(expectedFood, result);
    }
}