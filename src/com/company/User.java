package com.company;

import java.util.Scanner;

public class User {


    private String userName;
    private String password;
    private String phoneNumber;
    private boolean broker;
    public static User[] users;
    private int postLimit;

    public int getPostLimit() {
        return postLimit;
    }

    public void setPostLimit(int postLimit) {
        this.postLimit = postLimit;
    }

    public User() {
        this.userName = userNameChecker();
        this.password = passwordValidation();
        this.phoneNumber = phoneNumberValidation();
        this.broker = isBrokerChecker();
    }

    void userLogin() {
        int userLoggedIn;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String userNameToCheck = scanner.nextLine();
        System.out.println("Enter your password:");
        String passwordToCheck = scanner.nextLine();
        isMember(passwordToCheck,userNameToCheck);
        for (int i = 0; i < User.users.length; i++) {
            if (!User.users[i].equals(userNameToCheck, passwordToCheck)) {
                System.out.println("Incorrect username or password! Returning to the main menu.");
                userLoggedIn = 0;
                Main.programMenu();
            } else {
                userLoggedIn = 1;
                System.out.println("[1] - Post a new property");
                System.out.println("[2] - Remove a property");
                System.out.println("[3] - Show all properties");
                System.out.println("[4] - Show all properties posted by you");
                System.out.println("[5] - Search");
                System.out.println("[6] - Logout and return to main menu");
                int userSelection;
                User user = new User(userNameToCheck, passwordToCheck, User.users[i].getPhoneNumber(), User.users[i].isBroker());
                userSelection = scanner.nextInt();
                switch (userSelection) {
                    case Constant.POST_NEW_PROPERTY:
                        RealEstate.postNewProperty(user);
                        if (RealEstate.postNewProperty(user)) {
                        } else {
                            System.out.println("You reached the limit of posts.");
                        }

                }
            }
        }

    }

    private boolean equals(String userName, String loginUserPassword) {
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
                if (c == Constant.DOLLAR_SIGN || c == Constant.PERCENT_SIGN || c == Constant.LOWER_MAKAF) {
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
        boolean isBroker = false;
        if (usersChoice == Constant.IS_BROKER) {
            isBroker = true;

        }
        return isBroker;

    }

    public static boolean isMember(String userNameToCheck, String userPasswordCheck) {
        boolean isMember = false;
        if (users.length > 0) {
            for (int i = 0; i < users.length; i++) {
                if (users[i].getUserName().equals(userNameToCheck)) {
                    if (users[i].getPassword().equals(userPasswordCheck)) {
                        isMember = true;
                    }
                }
            }
        }
        return isMember;
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

    public boolean isBroker() {
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
