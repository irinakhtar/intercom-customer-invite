package com.intercom;

import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import com.intercom.service.CustomerInviteService;
import com.intercom.service.impl.CustomerInviteServiceImpl;

import java.io.*;
import java.util.ArrayList;

public class Application {
    /**
     *
     * @param outFile for output file location path with file name
     * @param customers list of customers within 100km from office location
     * @throws IOException for bufferedWriter
     */
    public static void writeToFile(String outFile, ArrayList<Customer> customers) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile))));
            for(Customer customer: customers) {
                String line = customer.getUserId() + "," + customer.getName();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }

    }

    /**
     * Main entry point for this project, you have to give 4 parameters if you want to see the output.
     * @param args
     * args[0] is input file path
     * args[1] and args[2] are GPS location point(latitude and longitude) of office location
     * args[3] is output file location
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("You must provide inputFile, latitude, longitude and outFile.");
        }
        String inputFile = args[0];
        double latitude = Double.parseDouble(args[1]);
        double longitude = Double.parseDouble(args[2]);
        String outFile = args[3];
//        String inputFile = "D:\\OnlineJavaCourse\\intercom-customer-invite\\src\\main\\resources\\customers.txt";
//        double latitude = 53.339428;
//        double longitude = -6.257664;
        CustomerInviteService customerInviteService = new CustomerInviteServiceImpl(new CustomerFileParser(inputFile));
        ArrayList<Customer> customersWithin100Km = customerInviteService.inviteCustomerWithin100Km(new GpsLocation(latitude, longitude));

//        for(Customer customer: customersWithin100Km) {
//            System.out.println(customer);
//        }
        writeToFile(outFile, customersWithin100Km);
    }
}