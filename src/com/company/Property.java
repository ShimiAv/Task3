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
        return roomAmount;
    }

    public String getCity() {
        return city;
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


    public void setPublisherUser(User publisherUser) {
        this.publisherUser = publisherUser;
    }


    public void setRoomAmount(int roomNumbers) {
        this.roomAmount = roomNumbers;
    }


    public void setType(int type) {
        if (checkType(type)) {
            this.type = type;
        }
    }

    private boolean checkType (int type) {
        return type >= 1 && type <= 3;
    }

    public int getType() {
        return type;
    }

    public void setForRent(int isRent) {
        switch (isRent) {
            case Constants.RENT -> this.isRent = true;
            case Constants.SALE -> this.isRent = false;
        }
    }

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
    }


    public void setPrice(Integer price) {
        this.price = price;
    }
}
