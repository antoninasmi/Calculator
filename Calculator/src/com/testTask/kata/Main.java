package com.testTask.kata;

import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("Insert your expression:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        Calculator calculator = new Calculator();
        return calculator.calc(input);
    }
}
