package org;
import java.util.Scanner;

class ScannerCustomInput implements CustomInput{
    Scanner sc = new Scanner(System.in);

    public String inputString(){
        String stringInput = sc.nextLine();
        return stringInput;
    }

    public int inputInt(){
        int intInput = sc.nextInt();
        sc.nextLine();
        return intInput;
    }
}