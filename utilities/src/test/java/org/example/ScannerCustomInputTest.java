package utilities.src.test.java.org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import org.ScannerCustomInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScannerCustomInputTest {
    private InputStream originalInput; //variable to hold original System.in value (read from keyboard)
 
    @BeforeEach
    void setup() {
        originalInput = System.in;
    }
 
    @AfterEach
    void teardown(){
        System.setIn(originalInput); //reset System.in
    }
 
    @Test
    @DisplayName("given user input string, when input string, then return user string")
    public void throwsInputMismatchExceptionWhenGivenString() {
        setInputWithNewline("hello"); //set System.in to "hello"
        final ScannerCustomInput input = new ScannerCustomInput();
        assertEquals("hello", input.inputString(), ""); //calls sc.nextline, but replaces default System.in with "hello"
    }
 
    @Test
    void anExceptionTest() {
        setInputWithNewline("hello");
        final ScannerCustomInput input = new ScannerCustomInput();
        assertThrows(InputMismatchException.class, () -> {
            //..
            input.inputInt();
        });
    }
 
    private static void setInputWithNewline(String input) {
        System.setIn(new ByteArrayInputStream((input + "\n").getBytes()));
    }
}


