package com.company;

import java.util.Arrays;

public class City {

    private final String cityName;
    private final String district;
    private final String[] streets;


    public City(String cityName, String district, String[] streets) {
        this.cityName = cityName;
        this.district = district;
        this.streets = streets;
    } //O(1)

    public String toString() {
        return "district: " + this.district + ", city: " + this.cityName + ", street: " + Arrays.toString(this.streets);
    } //O(1)

    public String[] getStreets() {
        return streets;
    } //O(1)

    public String getCityName() {
        return cityName;
    } //O(1)
}
