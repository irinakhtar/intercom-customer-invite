package com.intercom.service;

import com.intercom.model.Customer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This service will be use to invite customers.
 */
public interface CustomerInviteService {

    /**
     * This method makes a list of Customer (sorted by id) who are within 100km from given
     * Geo location (latitude and longitude).
     *
     * @param latitude represents the latitude of a given Geo Location.
     * @param longitude represents the longitude of a given Geo Location.
     * @return an ArrayList of customer, who are within 100km from the given Geo Location.
     * @throws IOException an Exception.
     */
    ArrayList<Customer> inviteCustomerWithin100Km(double latitude, double longitude)
            throws IOException;
}
