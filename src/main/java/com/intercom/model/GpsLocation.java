package com.intercom.model;

public class GpsLocation {
    private double latitude;
    private double longitude;

    public GpsLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GpsLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
