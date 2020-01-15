package com.intercom;

import com.google.gson.Gson;
import com.intercom.model.Customer;

import java.io.*;
import java.util.ArrayList;

public class Application {

    public static ArrayList<Customer> getCustomersFromFile() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        BufferedReader br = null;
        String filePath = "D:\\OnlineJavaCourse\\intercom-customer-invite\\src\\main\\resources\\customers.txt";
        Gson gson = new Gson();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
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

    public static ArrayList<Customer> getClosestCustomer(ArrayList<Customer> customers) {
        ArrayList<Customer> nearestCustomer = new ArrayList<Customer>();
        double latitude = 53.339428;
        double longitude = -6.257664;
        for(Customer customer: customers) {
            double dist = distance(latitude, customer.getLatitude(), longitude, customer.getLongitude());
            if (dist <= 100) {
                nearestCustomer.add(customer);
            }
        }
        return nearestCustomer;
    }

    public static double distance(double lat1,
                                 double lat2, double lon1,
                                 double lon2)
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

    //

    public static void main(String[] args) throws Exception{
        ArrayList<Customer> customers = getCustomersFromFile();

        ArrayList<Customer> nearestCustomer = getClosestCustomer(customers);
        for(Customer customer: nearestCustomer) {
            System.out.println(customer);
        }
    }
}
