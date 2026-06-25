package io.github.spencerduberry.hotelreservation.utils;
import java.util.Scanner;

public class ScannerCustomInput implements CustomInput{
    Scanner sc = new Scanner(System.in);

    public String inputString(){
        return sc.nextLine();
    }

    public int inputInt(){
        int intInput = sc.nextInt();
        sc.nextLine();
        return intInput;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}