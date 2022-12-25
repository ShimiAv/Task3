package com.company;

public class Property {

    private String city;

    private String street;

    private int roomNumbers;

    private Integer price;

    private String type;

    private boolean isRent;

    private int floorNumber;

    private int houseNumber;

    private User publisherUser;

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isRent() {
        return isRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getPrice() {
        return price;
    }

    public User getPublisherUser() {
        return publisherUser;
    }

    public String getStreet() {
        return street;
    }

    public int getRoomNumbers() {
        return roomNumbers;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPrice(int price) {

    }

    public void setPublisherUser(User publisherUser) {
        this.publisherUser = publisherUser;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    public void setRoomNumbers(int roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public void setType(String type) {
        this.type = type;
    }
}
