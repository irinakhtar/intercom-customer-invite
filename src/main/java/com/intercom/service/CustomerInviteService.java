package com.intercom.service;

import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This service will be use to invite customers.
 */
public interface CustomerInviteService {
    /**
     *
     * @param path
     * @param dublinOfficeLocation
     * @return
     */
    ArrayList<Customer> inviteCustomerWithin100Km(String path, GpsLocation dublinOfficeLocation) throws IOException;
}
