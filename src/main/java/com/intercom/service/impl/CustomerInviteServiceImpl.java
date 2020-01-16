package com.intercom.service.impl;

import com.google.gson.Gson;
import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import com.intercom.service.CustomerInviteService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementation of CustomerInviteService
 * from customer info calculate the distance of customer from the office and if distance between 100 km than make a list of customer
 */
public class CustomerInviteServiceImpl implements CustomerInviteService {

    private CustomerFileParser customerFileParser;

    public CustomerInviteServiceImpl(CustomerFileParser customerFileParser) {
        this.customerFileParser = customerFileParser;
    }

    /**
     *
     * @param dublinOfficeLocation Gps location of dublin office - latitude and longitude
     * @return an ArrayList of customer, who are within 100km from dublin office
     * @throws IOException parseCustomerFromFile throws IO exception
     */
    public ArrayList<Customer> inviteCustomerWithin100Km(GpsLocation dublinOfficeLocation)
            throws IOException {
        ArrayList<Customer> customers = customerFileParser.parseCustomerFromFile();
        ArrayList<Customer> customersWithin100Km = new ArrayList<Customer>();
        for(Customer customer: customers) {
            if(isWithin100Km(customer, dublinOfficeLocation)){
                customersWithin100Km.add(customer);
            }
        }
        sortCustomerById(customersWithin100Km);
        return customersWithin100Km;
    }

    /**
     * This method call the distance method and make decision is this customer within 100km or not from dublin office
     * @param customer info of one customer
     * @param dublinOfficeLocation is gps location of dublin office - latitude and longitude
     * @return true or false for is this customer within 100km or not from dublin office
     */
    private boolean isWithin100Km(Customer customer, GpsLocation dublinOfficeLocation) {
        double distance = distance(customer.getLatitude(), dublinOfficeLocation.getLatitude(),
                customer.getLongitude(), dublinOfficeLocation.getLongitude());
        return distance <= 100;
    }

    /**
     *
     * @param officeLat Gps location latitude of office
     * @param officeLong Gps location longitude of office
     * @param customerLat Gps location latitude of customer
     * @param customerLong Gps location longitude of customer
     * @return a distance value of office and customer
     */
    private double distance(double officeLat, double officeLong, double customerLat, double customerLong)
    {
        // Radius of earth in kilometers 6371
        double r = 6371;
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        officeLat = Math.toRadians(officeLat);
        officeLong = Math.toRadians(officeLong);
        customerLat = Math.toRadians(customerLat);
        customerLong = Math.toRadians(customerLong);

        // Haversine formula
        double dlat = customerLat - officeLat;
        double dlon = customerLong - officeLong;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(officeLat) * Math.cos(customerLat)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // calculate the result
        return(c * r);
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
