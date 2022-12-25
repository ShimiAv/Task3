package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        programMenu();


    }

    public static void programMenu() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            System.out.println("Welcome to my Real-Estate program");
            System.out.println("Enter |1| to create a new account");
            System.out.println("Enter |2| to sing in");
            System.out.println("Enter |3| to exit the program");
            userInput = scanner.nextInt();
            if (userInput == Constant.CREATE_ACCOUNT) {
                RealEstate.createAccount();
            } else if (userInput == Constant.SIGN_IN) {
                RealEstate.userLogin();

            } else if (userInput == Constant.EXIT_PROGRAM) System.exit(0);

        } while (true);


    }
}




