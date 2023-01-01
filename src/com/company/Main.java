package com.company;

import java.util.Scanner;

public class Main {
    private static final RealEstate realEstate = new RealEstate(); // O(1)
    private static final Scanner scanner = new Scanner(System.in); // O(1)

    public static void main(String[] args) {

        generalMenu();
    }

    public static void generalMenu() {
        int userInput;
        do {
            System.out.println("Welcome to my Real-Estate program");
            System.out.println("Enter |1| to create a new account");
            System.out.println("Enter |2| to sing in");
            System.out.println("Enter |3| to exit the program");
            userInput = scanner.nextInt();
            scanner.nextLine();


            if (userInput == Constants.CREATE_ACCOUNT) {
                RealEstate.createAccount();
                userInput = Constants.INVALID_VALUE;

            } else if (userInput == Constants.SIGN_IN) {
                User current = RealEstate.userLogin();
                if (current != null) {
                    System.out.println("Welcome back" + current.getUserName());
                    secondMenu(current);

                }
                userInput = Constants.INVALID_VALUE;

            } else if (userInput == Constants.EXIT_PROGRAM) System.exit(0);

        } while (true);


    } //O(n)

    public static void secondMenu(User user) {
        int userSelection;
        do {


            System.out.println("[1] - Post a new property");
            System.out.println("[2] - Remove a property");
            System.out.println("[3] - Show all properties");
            System.out.println("[4] - Show all your properties ");
            System.out.println("[5] - Search");
            System.out.println("[6] - Logout and return to main menu");
             userSelection = scanner.nextInt();
            scanner.nextLine();

            switch (userSelection) {
                case Constants.POST_NEW_PROPERTY -> {
                    if (RealEstate.postNewProperty(user)) {
                        System.out.println("The property has been successfully published");
                    } else {
                        System.out.println("The posting process failed");
                    }
                }
                case Constants.REMOVE_PROPERTY -> RealEstate.removeProperty(user);
                case Constants.SHOW_ALL_PROPERTIES -> RealEstate.printAllProperties();
                case Constants.SHOW_USERS_PROPERTIES -> RealEstate.printUsersProperties(user);
                case Constants.SEARCH_PROPERTY -> {
                    Property[] filteredProperties = RealEstate.searchProperty();
                    RealEstate.printFilteredProperties(filteredProperties);
                }
            }


        }while (userSelection!=Constants.LOG_OUT);
    } //O(n)
}




