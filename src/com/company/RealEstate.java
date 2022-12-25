package com.company;

import java.util.Scanner;

public class RealEstate {


    private final Property[] PROPERTIES = new Property[0];

    public RealEstate() {

    }

    public static void createAccount() {
        User newUser = new User(User.userNameChecker(), User.passwordValidation(), User.phoneNumberValidation(), User.isBrokerChecker());
        if (newUser != null) {

            User[] updatedUsersList = new User[User.users.length + 1];
            System.arraycopy(User.users, 0, updatedUsersList, 0, User.users.length);
            updatedUsersList[updatedUsersList.length - 1] = newUser;
            User.users = updatedUsersList;

        } else {
            User.users[0] = newUser;
        }

    }

    public static User userLogin() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if (User.isMember(username, password)) {
            for (int i = 0; i < User.users.length; i++) {
                user = User.users[i];
            }

        } else user = null;
        return user;


    }

    static boolean postNewProperty(User user) {
        boolean postingLimitation = false;
        if (user.isBroker()) {
            if (user.getPostLimit() < Constant.BROKER_LIMIT) {
                postingLimitation = true;
            }
        } else if (!user.isBroker()) {
            if (user.getPostLimit() < Constant.REGULAR_USER_LIMIT) {
                postingLimitation = true;
            }
        }
        return postingLimitation;
    }
}