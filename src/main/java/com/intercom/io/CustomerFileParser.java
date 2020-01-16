package com.intercom.io;

import com.google.gson.Gson;
import com.intercom.model.Customer;

import java.io.*;
import java.util.ArrayList;

public class CustomerFileParser {

    private String filePath;
    private BufferedReader bufferedReader;

    public CustomerFileParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method for read the file and parse the data of customer and also make a customer list
     * @return list of customer
     * @throws IOException
     */

    public ArrayList<Customer> parseCustomerFromFile() throws IOException {

        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            Gson gson = new Gson();
            while ((line = bufferedReader.readLine())!=null) {
                Customer customer = gson.fromJson(line, Customer.class);
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return customers;
    }
}
