package com.sda;

import java.util.Scanner;

public class WarmupApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. dodawanie");
        System.out.println("2. odejmowanie");
        System.out.println("3. dzielenie");
        System.out.println("4. mnozenie");
        System.out.println("5. suma wielu");

        int operation = scanner.nextInt();
        if (operation > 0 && operation < 5) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            double result = performOperation(operation, a, b);
            System.out.println("Result: " + result);
        } else {
            int sum = 0;
            while (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            }
            System.out.println("Suma wielu: " + sum);
        }
    }

    private static double performOperation(int operation, int a, int b) {
        double result;
        switch (operation) {
            case 1:
                result = a + b;
                break;
            case 2:
                result = a - b;
                break;
            case 3:
                result = a / (double) b;
                break;
            case 4:
                result = a * b;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
