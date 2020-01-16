package com.intercom.model;

/**
 * GpsLocaation is a class for set and get the location of customer or office.
 */
public class GpsLocation {
    /**
     * latitude is a GPS location value
     */
    private double latitude;
    /**
     * longitude is another GPS location value
     */
    private double longitude;

    public GpsLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return latitude value return for any location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude value for any gps location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return longitude value for any gps location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude value for any gps location
     */
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
