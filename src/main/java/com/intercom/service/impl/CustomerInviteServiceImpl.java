package com.intercom.service.impl;

import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import com.intercom.service.CustomerInviteService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * An implementation of CustomerInviteService.
 */
public class CustomerInviteServiceImpl implements CustomerInviteService {

    private CustomerFileParser customerFileParser;

    public CustomerInviteServiceImpl(CustomerFileParser customerFileParser) {
        this.customerFileParser = customerFileParser;
    }

    /**
     * This method makes a list of Customer (sorted by id) who are within 100km from
     * given Geo location (latitude and longitude).
     *
     * @param latitude represents the latitude of a given Geo Location.
     * @param longitude represents the longitude of a given Geo Location.
     * @return an ArrayList of customer, who are within 100km from the given Geo Location.
     * @throws IOException an Exception.
     */
    public ArrayList<Customer> inviteCustomerWithin100Km(double latitude, double longitude)
            throws IOException {
        ArrayList<Customer> customers = customerFileParser.parseCustomerFromFile();
        ArrayList<Customer> customersWithin100Km = new ArrayList<Customer>();
        for(Customer customer: customers) {
            if(isWithin100Km(customer, latitude, longitude)){
                customersWithin100Km.add(customer);
            }
        }
        sortCustomerById(customersWithin100Km);
        return customersWithin100Km;
    }

    /**
     * This method checks if the given customer within 100km or not from a given Geo location.
     * @param customer an instance of a Customer class.
     * @param latitude represents the latitude of a given Geo Location.
     * @param longitude represents the longitude of a given Geo Location.
     * @return true or false for is this customer within 100km or not from dublin office
     */
    private boolean isWithin100Km(Customer customer, double latitude, double longitude) {
        double distance = distance(customer.getLatitude(), customer.getLongitude(),
                latitude, longitude);
        return distance <= 100;
    }

    /**
     * This method calculates the distance (in Kilometre) between given two Geo locations.
     *
     * @param givenLatitude Gps location latitude of office
     * @param givenLongitude Gps location longitude of office
     * @param customerLatitude Gps location latitude of customer
     * @param customerLongitude Gps location longitude of customer
     * @return a distance in kilometre.
     */
    private double distance(double givenLatitude, double givenLongitude,
                            double customerLatitude, double customerLongitude)
    {
        final double earthRadius = 6371;

        givenLatitude = Math.toRadians(givenLatitude);
        givenLongitude = Math.toRadians(givenLongitude);
        customerLatitude = Math.toRadians(customerLatitude);
        customerLongitude = Math.toRadians(customerLongitude);

        double diffLatitude = customerLatitude - givenLatitude;
        double diffLongitude = customerLongitude - givenLongitude;
        double a = Math.pow(Math.sin(diffLatitude / 2), 2)
                + Math.cos(givenLatitude) * Math.cos(customerLatitude)
                * Math.pow(Math.sin(diffLongitude / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return(c * earthRadius);
    }

    /**
         *
         * sort the customer arraylist by userId
         * @param customers list of customer
         */
    private void sortCustomerById(ArrayList<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer customerOne, Customer customerTwo) {
                return customerOne.getUserId().compareTo(customerTwo.getUserId());
            }
        });
    }
}
