package com.intercom.service.impl;

import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInviteServiceImplTest {

    @Test
    public void inviteCustomerWithin100Km() throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(10L, "Georgina Gallagher", 52.240382, -6.972413);
        Customer customer2 = new Customer(5L, "Nora Demsey", 53.1302756, -6.2397222);
        customers.add(customer1);
        customers.add(customer2);

        CustomerFileParser customerFileParser = mock(CustomerFileParser.class);
        when(customerFileParser.parseCustomerFromFile()).thenReturn(customers);

        CustomerInviteServiceImpl customerInviteService = new CustomerInviteServiceImpl(customerFileParser);

        ArrayList<Customer> actualCustomers = customerInviteService.inviteCustomerWithin100Km(
                new GpsLocation(53.339428, -6.257664));

        assertEquals(0, actualCustomers.size());
    }
}