package com.intercom.service.impl;

import com.google.gson.Gson;
import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import com.intercom.service.CustomerInviteService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class CustomerInviteServiceImpl implements CustomerInviteService {

    public ArrayList<Customer> inviteCustomerWithin100Km(String path, GpsLocation dublinOfficeLocation)
            throws IOException {
        ArrayList<Customer> customers = parseCustomerFromFile(path);
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
     *
     * @param customer
     * @param dublinOfficeLocation
     * @return
     */
    private boolean isWithin100Km(Customer customer, GpsLocation dublinOfficeLocation) {
        double distance = distance(customer.getLatitude(), dublinOfficeLocation.getLatitude(),
                customer.getLongitude(), dublinOfficeLocation.getLongitude());
        return distance <= 100;
    }

    /**
     *
     * @param path
     * @return
     * @throws IOException
     */
    private ArrayList<Customer> parseCustomerFromFile(String path) throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader br = null;

        Gson gson = new Gson();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            while ((line = br.readLine())!=null) {
                Customer customer = gson.fromJson(line, Customer.class);
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return customers;
    }

    /**
     *
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @return
     */
    private double distance(double lat1, double lat2, double lon1, double lon2)
    {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    /**
     *
     * @param customers
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
