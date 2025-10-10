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
class CatTest {

    @Mock
    Feline felineMock;

    @Test
    void testGetSoundReturnsMeow() {
        Cat cat = new Cat(felineMock);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFoodDelegatesToFelineEatMeat() throws Exception {
        List<String> expectedFood = Arrays.asList("Мясо", "Птица", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(felineMock);
        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);

        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void testGetFoodThrowsExceptionWhenFelineThrows() throws Exception {
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));

        Cat cat = new Cat(felineMock);

        Exception exception = assertThrows(Exception.class, cat::getFood);

        assertEquals("Ошибка получения еды", exception.getMessage());
        verify(felineMock, times(1)).eatMeat();
    }

    @Test
    void testConstructorSetsPredatorField() {
        Cat cat = new Cat(felineMock);

        assertEquals(felineMock, cat.predator);
    }
}
