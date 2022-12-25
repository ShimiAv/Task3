package com.company;

import java.util.Objects;

public class City {

    private static  City[] cities;
    private String cityName;
    private String district;
    private String[] street;


    private City(String city, String district, String[] street) {
        this.cityName = city;
        this.district = district;
        this.street = street;

    }

    public final City[] CITIES() {
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
        return CITIES();
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStreet(String[] street) {
        this.street = street;
    }

    public String getCityName(String cityName) {
        return this.cityName;
    }

    public String getDistrict() {
        return district;
    }

    public String[] getStreet() {
        return street;
    }


    public String toString() {
        return "district: " + this.district + ", city: " + this.cityName + ", street: " + this.street;
    }

}
