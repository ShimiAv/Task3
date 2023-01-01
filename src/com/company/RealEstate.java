package com.company;

import java.util.Scanner;

public class RealEstate {
    private static Property[] properties;
    private static City[] cities;
    private User[] users;

    private final Property[] PROPERTIES = new Property[0]; //O(1)

    public RealEstate() {
        users = new User[]{
                new User("shimi", "1234K_", "0547473738", false)
        };

        cities = new City[]{

                new City("tel aviv", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("haifa", "north", new String[]{"yaffo, hertzel, alenbi"}),
                new City("jerusalem", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("eilat", "south", new String[]{"yaffo, hertzel, alenbi"}),
                new City("ashdod", "south", new String[]{"yaffo, hertzel, alenbi"}),
                new City("ramat gan", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("lod", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("ramla", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("holon", "center", new String[]{"yaffo, hertzel, alenbi"}),
                new City("yaffo", "center", new String[]{"yaffo, hertzel, alenbi"}),
        };
    } //O(n)

    public static void createAccount() {
        User user = new User();
        User.userNameChecker();
        User.passwordValidation();
        User.phoneNumberValidation();
        User.isBrokerChecker();
        User.users = addUser(User.users, user);
        System.out.println("Registration completed successfully");
    } //O(n)

    private static User[] addUser(User[] users, User newUser) {
        int length;
        if (users == null) {
            length = 0;
        } else {
            length = users.length;
        }
        User[] tempUsersList = new User[length + 1];

        for (int i = 0; i < length; i++) {
            tempUsersList[i] = users[i];
        }

        tempUsersList[length] = newUser;

        return tempUsersList;
    } //O(n)

    public static boolean postNewProperty(User user) {
        boolean isAllowed = false;
        if (!isAllowedToPost()) {
            System.out.println("you have reached the post limitation ");
        } else {
            Property property = new Property();
            String city = usersCityInput();
            int cityIndex = cityChecker(city);
            if (cityIndex == Constants.INVALID_VALUE) {
                System.out.println("Requested city not found");
            } else {
                property.setCity(cities[cityIndex].getCityName());
                String street = usersStreetInput(cityIndex);
                int streetIndex = streetChecker(street, cityIndex);
                if (streetIndex == Constants.INVALID_VALUE) {
                    System.out.println("Requested street not found");
                } else {
                    property.setStreet(cities[cityIndex].getStreets()[streetIndex]);
                    int propertyType = propertyType();
                    property.setType(propertyType);

                    if (property.getType() != propertyType) {
                        System.out.println("Invalid Input");
                    } else {
                        if (property.getType() == Constants.REGULAR_APARTMENT ||
                                property.getType() == Constants.PENTHOUSE) {

                            usersInputFloorNumber(property);
                        }

                        usersInputRoomAmount(property);
                        setHouseNumberFromUser(property);
                        setForRentFromUser(property);

                        if (!property.isRent()) {
                            System.out.println("Invalid input");
                        } else {
                            setPropertyPriceFromUser(property);
                            property.setPublisherUser(user);
                            properties = addProperty(properties, property);
                            isAllowed = true;
                        }
                    }
                }
            }
        }
        return isAllowed;
    } //O(n)

    public static void removeProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        int userPosts = publishesCounter(user);
        if (userPosts == 0) {
            System.out.println("There are no ads for you to delete");
        } else {
            int[][] postIdArray = new int[userPosts][];
            System.out.println("Which property do you want to delete?");

            int option = 0;
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getPublisherUser().getUserName().equals(user.getUserName())) {
                    postIdArray[option] = new int[]{
                            option + 1, i
                    };
                    option++;
                    System.out.println(option + ") " + properties[i].getCity() + " - " + properties[i].getStreet() + " " + properties[i].getHouseNumber());
                }
            }

            int userInput = scanner.nextInt();
            scanner.nextLine();
            int postIndex = getPostIndex(postIdArray, userInput);
            if (postIndex != Constants.INVALID_VALUE) {
                properties = deletePost(postIndex);
                System.out.println("The ad has been successfully deleted");
            } else {
                System.out.println("Error The ad has not been deleted");
            }
        }
    } //O(n)

    private static int propertyType() {
        Scanner scanner = new Scanner(System.in);
        int propertyType;
        System.out.println("|1| for a regular apartment");
        System.out.println("|2| for a penthouse apartment");
        System.out.println("|3| for a house");
        propertyType = scanner.nextInt();
        scanner.nextLine();

        return propertyType;
    } //O(1)

    private static Property[] addProperty(Property[] properties, Property property) {
        int arrLength;

        if (properties == null) {
            arrLength = 0;
        } else {
            arrLength = properties.length;
        }

        Property[] tempProperties = new Property[arrLength + 1];

        for (int i = 0; i < arrLength; i++) {
            tempProperties[i] = properties[i];
        }

        tempProperties[arrLength] = property;
        return tempProperties;
    } //O(n)

    private static Property[] deletePost(int postIndex) {
        int arrLength;

        if (properties == null) {
            arrLength = 0;
        } else {
            arrLength = properties.length;
        }

        Property[] tempProperties = new Property[arrLength - 1];

        for (int i = 0; i < properties.length; i++) {
            if (i != postIndex) {
                tempProperties[i] = properties[i];
            }
        }

        return tempProperties;
    } //O(n)

    private static int cityChecker(String cityName) {
        int indexOfCity = Constants.INVALID_VALUE;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getCityName().toLowerCase().equals(cityName)) {
                indexOfCity = i;
                break;
            }
        }
        return indexOfCity;
    } //O(n)

    private static void setForRentFromUser(Property property) {
        Scanner scanner = new Scanner(System.in);
        int forRent;
        System.out.println();
        System.out.println("|1| for rent");
        System.out.println("|2| for sale");

        forRent = scanner.nextInt();
        scanner.nextLine();
        property.setForRent(forRent);
    } //O(1)

    private static void setHouseNumberFromUser(Property property) {
        Scanner scanner = new Scanner(System.in);
        int houseNumber;
        System.out.println("House number ?");
        houseNumber = scanner.nextInt();
        scanner.nextLine();
        property.setHouseNumber(houseNumber);
    } //O(1)

    private static void usersInputFloorNumber(Property property) {
        Scanner scanner = new Scanner(System.in);
        int floor;
        System.out.println("Floor number ?");
        floor = scanner.nextInt();
        scanner.nextLine();
        property.setFloorNumber(floor);
    } //O(1)

    private static void usersInputRoomAmount(Property property) {
        Scanner scanner = new Scanner(System.in);
        int roomsAmount;
        System.out.println("How many rooms?");
        roomsAmount = scanner.nextInt();
        scanner.nextLine();
        property.setRoomAmount(roomsAmount);
    } //O(1)

    private static int streetChecker(String streetName, int cityIndex) {
        int streetIndex = Constants.INVALID_VALUE;

        for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
            if (cities[cityIndex].getStreets()[i].toLowerCase().equals(streetName)) {
                streetIndex = i;
                break;
            }
        }
        return streetIndex;
    } //O(n)

    private static int publishesCounter(User user) {
        int counter = 0;
        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getPublisherUser().getUserName().equals(user.getUserName())) {
                    counter++;
                }
            }
        }
        return counter;
    } //O(n)

    private static int getPostIndex(int[][] postId, int userInput) {
        int index = Constants.INVALID_VALUE;
        if (postId != null) {
            for (int i = 0; i < postId.length; i++) {
                if (postId[i][0] == userInput) {
                    index = postId[i][1];
                    break;
                }
            }
        }
        return index;
    } //O(n)

    private static String usersCityInput() {
        Scanner scanner = new Scanner(System.in);
        String cityName;
        for (int i = 0; i < cities.length; i++) {
            int num = i + 1;
            System.out.println(num + ". " + cities[i].getCityName());
        }

        System.out.println("Enter the name of the city");
        cityName = scanner.nextLine();
        cityName = cityName.toLowerCase().trim();
        return cityName;
    } //O(n)

    public static Property[] searchProperty() {
        Property[] filteredProperties = null;
        if (properties != null && properties.length > 0) {
            System.out.println("If you want to skip the search enter " + Constants.INVALID_VALUE);
            Boolean isRent = rentFilter();
            Integer type = typeFilter();
            Integer roomAmount = roomAmountFilter();
            Integer minPrice = minPriceFilter();
            Integer maxPrice = maxPriceFilter(minPrice);


            filteredProperties = adsFilter(properties, isRent, type, roomAmount, minPrice, maxPrice);

            if (filteredProperties == null) {
                System.out.println("No results found");
            }
        } else {
            System.out.println("There are no properties to search");
        }

        return filteredProperties;
    } //O(n)

    private static Integer roomAmountFilter() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        Integer roomAmount = null;
        System.out.println("How many rooms do you want");
        userInput = scanner.nextInt();
        scanner.nextLine();

        if (userInput != Constants.TO_SKIP) {
            roomAmount = userInput;
        }

        return roomAmount;
    } //O(1)

    private static Property[] adsFilter(Property[] properties, Boolean forRent, Integer type, Integer roomNumber, Integer minPrice, Integer maxPrice) {
        Property[] propertyListAfterFiltration = null;
        for (int i = 0; i < properties.length; i++) {

            if ((forRent == null || properties[i].isRent() == forRent) &&
                    (type == null || properties[i].getType() == type) &&
                    (roomNumber == null || properties[i].getRoomNumbers() == roomNumber) &&
                    (minPrice == null || properties[i].getPrice() >= minPrice) &&
                    (maxPrice == null || properties[i].getPrice() <= maxPrice)
            ) {
                propertyListAfterFiltration = addProperty(propertyListAfterFiltration, properties[i]);
            }
        }
        return propertyListAfterFiltration;
    } //O(n)

    public static void printAllProperties() {
        int count = 0;
        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                count++;
                System.out.println(count + ") " + properties[i]);
            }
        } else {
            System.out.println("Currently there are no publications");
        }
    } //O(n)

    public static void printUsersProperties(User user) {
        int counter = 0;
        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getPublisherUser().getUserName().equals(user.getUserName())) {
                    counter++;
                    System.out.println(counter + ") " + properties[i]);
                }
            }
            if (counter == 0) {
                System.out.println("No ads were found that you posted");
            }
        } else {
            System.out.println("No ads were found that you posted");
        }
    } //O(n)

    private static String usersStreetInput(int cityIndex) {
        Scanner scanner = new Scanner(System.in);
        String streetName;
        for (int i = 0; i < cities[cityIndex].getStreets().length; i++) {
            System.out.println(cities[cityIndex].getStreets()[i]);
        }

        System.out.println("Enter the name of the street");
        streetName = scanner.nextLine();
        streetName = streetName.toLowerCase().trim();

        return streetName;
    } //O(n)

    private static void setPropertyPriceFromUser(Property property) {
        Scanner scanner = new Scanner(System.in);
        int price;
        System.out.println("Enter the desired price");
        price = scanner.nextInt();
        scanner.nextLine();
        property.setPrice(price);
    } //O(n)

    private static boolean isAllowedToPost() {
        boolean postingLimitation = false;
        if (User.isBroker()) {
            if (User.getPostLimit() < Constants.BROKER_LIMIT) {
                postingLimitation = true;
            }
        } else if (!User.isBroker()) {
            if (User.getPostLimit() < Constants.REGULAR_USER_LIMIT) {
                postingLimitation = true;
            }
        }
        return postingLimitation;
    }  //O(n)

    public static User userLogin() {
        Scanner scanner = new Scanner(System.in);
        User current = null;
        System.out.println("Enter your username:");
        String userNameToCheck = scanner.nextLine();
        System.out.println("Enter your password:");
        String passwordToCheck = scanner.nextLine();
        int userToShowIndex = User.isMember(userNameToCheck, passwordToCheck);
        if (userToShowIndex != Constants.INVALID_VALUE) {
            current = User.users[userToShowIndex];

        } else Main.generalMenu();
        return current;
    }  //O(n)

    private static Boolean rentFilter() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        Boolean forRent = null;
        boolean endLoop = false;

        do {
            System.out.println("Enter a choice:");
            System.out.println("|1| For rent");
            System.out.println("|2| For Sale");

            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == Constants.RENT || userInput == Constants.SALE
                    || userInput == Constants.INVALID_VALUE) {
                endLoop = true;

                switch (userInput) {
                    case Constants.RENT -> forRent = true;
                    case Constants.SALE -> forRent = false;
                }
            } else {
                System.out.println("Select the relevant option or skip by entering " + Constants.INVALID_VALUE);
            }
        } while (!endLoop);
        return forRent;
    }  //O(1)

    private static Integer typeFilter() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        Integer type = null;
        boolean endLoop = false;

        do {
            System.out.println("Enter your choice :");
            System.out.println("|1| Regular apartment");
            System.out.println("|2| Penthouse");
            System.out.println("|3| House");
            userInput = scanner.nextInt();
            scanner.nextLine();


            if (userInput >= Constants.REGULAR_APARTMENT && userInput <= Constants.HOUSE
                    || userInput == Constants.INVALID_VALUE) {
                switch (userInput) {
                    case Constants.REGULAR_APARTMENT, Constants.HOUSE, Constants.PENTHOUSE -> type = userInput;
                }
                endLoop = true;
            } else {
                System.out.println("Select the relevant option or skip by entering " + Constants.INVALID_VALUE);
            }
        } while (!endLoop);
        return type;
    }  //O(1)

    private static Integer minPriceFilter() {
        Scanner scanner = new Scanner(System.in);
        Integer minPrice = null;
        int userInput;

        System.out.println("Enter a minimum price");
        userInput = scanner.nextInt();
        scanner.nextLine();

        if (userInput != Constants.TO_SKIP) {
            minPrice = userInput;
        }

        return minPrice;
    }  //O(1)

    private static Integer maxPriceFilter(Integer minimumPrice) {
        Scanner scanner = new Scanner(System.in);
        Integer maxPrice = null;
        int userInput;
        boolean endLoop = false;

        do {
            System.out.println("Enter a maximum price");
            userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput != Constants.TO_SKIP) {
                if (minimumPrice != null) {
                    if (minimumPrice >= userInput) {
                        System.out.println("The maximum price must be higher than the minimum price");
                    } else {
                        maxPrice = userInput;
                        endLoop = true;
                    }
                } else {
                    maxPrice = userInput;
                    endLoop = true;
                }
            } else {
                endLoop = true;
            }
        } while (!endLoop);

        return maxPrice;
    }  //O(1)

    public static void printFilteredProperties(Property[] properties) {
        int count = 0;

        if (properties != null) {
            for (int i = 0; i < properties.length; i++) {
                count++;
                System.out.println(count + ") " + properties[i] + "\n");
            }
        }


    }  //O(n)
}