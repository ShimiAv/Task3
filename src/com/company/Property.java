package com.company;

public class Property {

    private String city;
    private String street;
    private int roomAmount;
    private Integer price;
    private int type;
    private boolean isRent;
    private int floorNumber;
    private int houseNumber;
    private User publisherUser;


    public boolean isRent() {
        return isRent;
    }  //O(1)

    public int getHouseNumber() {
        return houseNumber;
    }  //O(1)

    public int getPrice() {
        return price;
    }  //O(1)

    public User getPublisherUser() {
        return publisherUser;
    }  //O(1)

    public String getStreet() {
        return street;
    }  //O(1)

    public int getRoomNumbers() {
        return roomAmount;
    }  //O(1)

    public String getCity() {
        return city;
    }  //O(1)

    public void setStreet(String street) {
        this.street = street;
    }  //O(1)

    public void setCity(String city) {
        this.city = city;
    }  //O(1)

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }  //O(1)

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }  //O(1)


    public void setPublisherUser(User publisherUser) {
        this.publisherUser = publisherUser;
    }  //O(1)


    public void setRoomAmount(int roomNumbers) {
        this.roomAmount = roomNumbers;
    }  //O(1)


    public void setType(int type) {
        if (checkType(type)) {
            this.type = type;
        }
    }  //O(1)

    private boolean checkType (int type) {
        return type >= 1 && type <= 3;
    }  //O(1)

    public int getType() {
        return type;
    }  //O(1)

    public void setForRent(int isRent) {
        switch (isRent) {
            case Constants.RENT -> this.isRent = true;
            case Constants.SALE -> this.isRent = false;
        }
    }  //O(1)

    public String toString () {
        String output = this.city + " - " + this.street + " " + this.houseNumber + ". \n";
        switch(this.type){
            case Constants.REGULAR_APARTMENT -> output += "Regular apartment ";
            case Constants.PENTHOUSE -> output += "Penthouse apartment ";
            case Constants.HOUSE -> output += "House ";
        }
        if(isRent()){
            output += "- for rent: ";
        }else{
            output += "- for sale: ";
        }
        output += this.roomAmount + " rooms, floor " + this.floorNumber + ".\n";
        output += "Price: " + this.price + "$.\n";
        output += "Contact info: " + this.publisherUser + " ";
        if(User.isBroker()){
            output += "(real estate broker).";
        }else{
            output += "(regular user).";
        }

        return output;
    }  //O(1)


    public void setPrice(Integer price) {
        this.price = price;
    } //O(1)
}
