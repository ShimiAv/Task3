package com.company;

import java.util.Scanner;

public class Main {
    private static final RealEstate realEstate = new RealEstate();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        programMenu();


    }

    public static void programMenu() {
        int userInput;
        do {
            System.out.println("Welcome to my Real-Estate program");
            System.out.println("Enter |1| to create a new account");
            System.out.println("Enter |2| to sing in");
            System.out.println("Enter |3| to exit the program");
            userInput = scanner.nextInt();
            if (userInput == Constants.CREATE_ACCOUNT) {
                RealEstate.createAccount();

            } else if (userInput == Constants.SIGN_IN) {
                User current = RealEstate.userLogin();
                if (current != null) {
                    System.out.println("Welcome back" + current.getUserName());
                    RealEstate.loginMenu(current);

                }

            } else if (userInput == Constants.EXIT_PROGRAM) System.exit(0);

        } while (true);


    }
}




