package com.intercom.model;

import com.google.gson.annotations.SerializedName;

public class Customer {
    /**
     * customer id. given by customer.txt
     */
    @SerializedName(value = "user_id")
    private Long userId;

    /**
     * customer name. given by customer.txt
     */
    private String name;

    /**
     * customer Location latitude. given by customer.txt
     */
    private double latitude;

    /**
     * customer Location Longitude. given by customer.txt
     */
    private double longitude;

    public Customer(Long userId, String name, double latitude, double longitude) {
        this.userId = userId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return customer userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     * @param userId set for customer
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     * @return name for customer
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name set for customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return latitude gps location point for customer
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude gps location point for customer
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return longitude gps location point for customer
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude gps location point for customer
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
