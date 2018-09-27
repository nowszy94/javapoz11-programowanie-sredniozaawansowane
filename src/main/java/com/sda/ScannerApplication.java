package com.sda;

import java.util.Scanner;

public class ScannerApplication {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int number = scanner.nextInt();
//        int number2 = scanner.nextInt();
//        int number3 = scanner.nextInt();
//        String rest = scanner.nextLine();
//
//        System.out.println("Liczba: " + number);
//        System.out.println("Liczba2: " + number2);
//        System.out.println("Liczba3: " + number3);
//
//        if (rest.length() > 0) {
//            System.out.println("jest jeszcze wartosc na strumieniu");
//        } else {
//            System.out.println("strumien pusty");
//        }

        Scanner scanner1 = new Scanner(System.in);
//        scanner1.nextInt();
//        scanner1.nextInt();
//        scanner1.nextInt();
//
//        if (scanner1.hasNextInt()) {
//            System.out.println("ma kolejne liczby");
//        } else if (scanner1.hasNext()){
//            System.out.println("ma stringa");
//        } else {
//            System.out.println("strumien pusty");
//        }

        int sum = 0;
        while(scanner1.hasNextInt()) {
            sum += scanner1.nextInt();
        }
        System.out.println(sum);


    }
}
