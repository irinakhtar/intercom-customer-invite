package com.intercom;

import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import com.intercom.service.CustomerInviteService;
import com.intercom.service.impl.CustomerInviteServiceImpl;

import java.io.*;
import java.util.ArrayList;

/**
 * This application use the <>com.intercom.service.CustomerInviteService</> for finding the
 * customers within 100km from a given location (i.e, Intercom office location in dublin).
 * The output of this application is written in a file.
 */
public class Application {
    /**
     * Main entry point for this project. This method takes four parameters
     * and writes the output in a given location.
     *
     * @param args args[0] is input file path
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

        CustomerInviteService customerInviteService = new CustomerInviteServiceImpl(
                new CustomerFileParser(inputFile));
        ArrayList<Customer> customersWithin100Km = customerInviteService
                .inviteCustomerWithin100Km(latitude, longitude);

        writeToFile(outFile, customersWithin100Km);
    }

    /**
     * This method writes the customers (id,name) in a file.
     *
     * @param outFile for output file location path with file name
     * @param customers list of customers within 100km from given location
     * @throws IOException an Exception.
     */
    private static void writeToFile(String outFile, ArrayList<Customer> customers) throws IOException {
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
}