package io.github.spencerduberry.hotelreservation.utils;

import java.util.function.Predicate;

public class Utils {

    public static int getValidInt(CustomInput inputSource, String prompt, String errorMessage, Predicate<Integer> condition) {
        boolean valid = false;
        int input = 0;

        while (!valid) {
            inputSource.printMessage(prompt);
            input = inputSource.inputInt();

            if (condition.test(input)) {
                valid = true;
            }
            else {
                inputSource.printMessage(errorMessage);
            }}

        return input;
        }
    }
