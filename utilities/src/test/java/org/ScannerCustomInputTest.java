package org;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScannerCustomInputTest {

    private ScannerCustomInput input;

    @BeforeEach
    void setup() {
        input = new ScannerCustomInput();
    }
    
    @Test
    @DisplayName("String Input")
    void throwsInputMismatchExceptionWhenGivenString() {
        String inputData = "hello\n";

        InputStream originalSystemIn = System.in;
        
        try{
            ByteArrayInputStream testIn = new ByteArrayInputStream(inputData.getBytes());

            System.setIn(testIn);

            ScannerCustomInput inputReader = new ScannerCustomInput();

            assertThrows(InputMismatchException.class, () -> {
                inputReader.inputInt();
        });
    }   finally {
            System.setIn(originalSystemIn);
    }
    }
}

