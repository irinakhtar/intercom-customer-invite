package com.intercom;

import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import com.intercom.service.CustomerInviteService;
import com.intercom.service.impl.CustomerInviteServiceImpl;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new IllegalArgumentException("You must provide inputFile, latitude and longitude.");
        }
        String inputFile = args[0];
        double latitude = Double.parseDouble(args[1]);
        double longitude = Double.parseDouble(args[2]);
//        String inputFile = "D:\\OnlineJavaCourse\\intercom-customer-invite\\src\\main\\resources\\customers.txt";
//        double latitude = 53.339428;
//        double longitude = -6.257664;
        CustomerInviteService customerInviteService = new CustomerInviteServiceImpl();
        ArrayList<Customer> customersWithin100Km = customerInviteService.inviteCustomerWithin100Km(
                inputFile, new GpsLocation(latitude, longitude));
        for(Customer customer: customersWithin100Km) {
            System.out.println(customer);
        }
    }
}