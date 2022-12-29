package com.company;

import java.util.Scanner;

public class User {


    private final String userName;
    private final String password;
    private final String phoneNumber;
    private static boolean broker;
    public static User[] users;
    private static int postLimit;


    public static int getPostLimit() {
        return postLimit;
    }


    public User() {
        this.userName = null;
        this.password = null;
        this.phoneNumber = null;
        this.broker = false;
    }

    public static void setPostLimit(int postLimit) {
        User.postLimit = postLimit;
    }


    private boolean equals(String userName) {
        boolean isEquals = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUserName().equals(userName)) {
                if (users[i].getPassword().equals(password)) {
                    isEquals = true;
                    break;
                }
            }
        }
        return isEquals;
    }

    public User(String userNameChecker, String passwordValidation, String phoneNumberValidation, boolean brokerChecker) {
        this.userName = userNameChecker();
        this.password = passwordValidation();
        this.phoneNumber = phoneNumberValidation();
        this.broker = isBrokerChecker();

    }

    public static String userNameChecker() {
        Scanner scanner = new Scanner(System.in);
        boolean isTaken = false;
        System.out.print("Enter a username: ");
        String userNameToCheck = scanner.nextLine();
        if (users != null) {


            while (true) {

                for (int i = 0; i < users.length; i++) {
                    if (users[i].equals(userNameToCheck)) {
                        isTaken = true;
                        break;
                    }
                }

                if (isTaken) {
                    System.out.println("Sorry, that username is already taken. Please try again.");
                } else {
                    System.out.println("Username is available. You can use it.");
                    break;
                }
            }
        }
        return userNameToCheck;
    }

    public static String passwordValidation() {
        Scanner scanner = new Scanner(System.in);
        String passwordToCheck;
        while (true) {
            System.out.print("Enter a password: ");
            passwordToCheck = scanner.nextLine();

            if (passwordToCheck.length() < 5) {
                System.out.println("Error: password must be at least 5 characters long.");
                continue;
            }

            boolean hasLetter = false;
            for (int i = 0; i < passwordToCheck.length(); i++) {
                if (Character.isLetter(passwordToCheck.charAt(i))) {
                    hasLetter = true;
                    break;
                }
            }
            if (!hasLetter) {
                System.out.println("Error: password must contain at least one letter.");
                continue;
            }

            boolean hasSpecialCharacter = false;
            for (int i = 0; i < passwordToCheck.length(); i++) {
                char c = passwordToCheck.charAt(i);
                if (c == Constants.DOLLAR_SIGN || c == Constants.PERCENT_SIGN || c == Constants.UNDERSCORE) {
                    hasSpecialCharacter = true;
                    break;
                }
            }
            if (!hasSpecialCharacter) {
                System.out.println("Error: password must contain at least one of the following special characters: $, %, _");
                continue;
            }

            break;
        }

        System.out.println("Password accepted!");
        return passwordToCheck;
    }

    public static String phoneNumberValidation() {
        Scanner scanner = new Scanner(System.in);
        String phoneNumberToCheck = "";

        while (true) {
            System.out.println("Please enter a phone number with the format 05xxxxxxxxx: ");
            phoneNumberToCheck = scanner.nextLine();

            if (phoneNumberToCheck.matches("05\\d{8}")) {
                break;
            } else {
                System.out.println("Invalid phone number. Please try again.");
            }
        }

        System.out.println("Valid phone number!");
        return phoneNumberToCheck;


    }

    public static boolean isBrokerChecker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a broker?");
        System.out.println("Enter |1| for No");
        System.out.println("Enter |2| for Yes");
        int usersChoice = scanner.nextInt();
        boolean isBroker = usersChoice == Constants.IS_BROKER;
        return isBroker;

    }

    public static int isMember(String userNameToCheck, String userPasswordCheck) {
        int result = Constants.INVALID_VALUE;
        if (users != null) {
            for (int i = 0; i < users.length; i++){
                if (users[i].getUserName().equals(userNameToCheck)){
                    if (users[i].getPassword().equals(userPasswordCheck)){
                        result = i;
                        break;
                    }
                }
            }
        }
        if (result == Constants.INVALID_VALUE) {
            System.out.println("One of the user details is incorrect");
        }
        return result;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static boolean isBroker() {
        return broker;
    }

    public String toString() {
        return "User name: " + this.userName + "\n" +
                " Phone number: " + this.phoneNumber + "\n" +
                " Is broker? " + this.broker;
    }

    public boolean equals(User other) {
        return this.userName.equals(other.userName);
    }
}
