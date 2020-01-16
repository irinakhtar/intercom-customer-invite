package com.intercom.service;

import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This service will be use to invite customers.
 */
public interface CustomerInviteService {
    /**
     *
     * @param dublinOfficeLocation
     * @return
     */
    ArrayList<Customer> inviteCustomerWithin100Km(GpsLocation dublinOfficeLocation) throws IOException;
}
