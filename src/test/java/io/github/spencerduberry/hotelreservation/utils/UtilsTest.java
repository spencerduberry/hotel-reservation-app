package io.github.spencerduberry.hotelreservation.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import static io.github.spencerduberry.hotelreservation.utils.Utils.*;

import java.util.function.Predicate;

class UtilsTest {

    private CustomInput mockInput;
    Predicate<Integer> condition;
    String prompt;
    String errorMessage;

    @BeforeEach
    public void setup() {
        mockInput = mock(CustomInput.class);
        condition = num -> num > 0;
        prompt = "Please enter the minimum room number.";
        errorMessage = "Error: number must be greater than zero.";
    }


    @Test
    public void whenInvalidInput_thenErrorMessage() {
        when(mockInput.inputInt()).thenReturn(0, 5);

        getValidInt(mockInput, prompt, errorMessage, condition);

        verify(mockInput, times(1)).printMessage(errorMessage);
        verify(mockInput, times(2)).printMessage(prompt);
    }

    @Test
    public void whenValidInput_thenReturnInput() {
        when(mockInput.inputInt()).thenReturn(5);

        int result = getValidInt(mockInput, prompt, errorMessage, condition);

        verify(mockInput, times(1)).printMessage(prompt);
        Assertions.assertEquals(5, result);
    }

}
