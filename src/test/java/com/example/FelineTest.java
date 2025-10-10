package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FelineTest {

    @Test
    void testGetFamilyReturnsCats() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void testGetKittensDefaultReturnsOne() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @Test
    void testGetKittensWithParamReturnsValue() {
        Feline feline = new Feline();
        assertEquals(5, feline.getKittens(5));
    }

    // Мокаем метод getFood у Feline
    @Test
    void testEatMeatReturnsMockedFood() throws Exception {
        Feline feline = Mockito.spy(new Feline());
        List<String> mockList = Arrays.asList("Мясо", "Рыба");
        doReturn(mockList).when(feline).getFood("Хищник");
        List<String> result = feline.eatMeat();
        assertEquals(mockList, result);
        verify(feline, times(1)).getFood("Хищник");
    }
}






