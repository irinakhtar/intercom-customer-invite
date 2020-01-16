package com.intercom.service.impl;

import com.intercom.io.CustomerFileParser;
import com.intercom.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CustomerInviteServiceImpl class.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerInviteServiceImplTest {

    @Test
    public void inviteCustomerWithin100Km_success() throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(10L, "Georgina Gallagher", 52.240382, -6.972413);
        Customer customer2 = new Customer(5L, "Nora Demsey", 53.2451022, -6.238335);
        customers.add(customer1);
        customers.add(customer2);

        CustomerFileParser customerFileParser = mock(CustomerFileParser.class);
        when(customerFileParser.parseCustomerFromFile()).thenReturn(customers);

        CustomerInviteServiceImpl customerInviteService = new CustomerInviteServiceImpl(customerFileParser);

        ArrayList<Customer> actualCustomers = customerInviteService
                .inviteCustomerWithin100Km(53.339428, -6.257664);

        assertEquals(1, actualCustomers.size());
    }

    @Test(expected = IOException.class)
    public void inviteCustomerWithin100Km_throwsException() throws Exception {
        CustomerFileParser customerFileParser = mock(CustomerFileParser.class);
        when(customerFileParser.parseCustomerFromFile()).thenThrow(new IOException());

        CustomerInviteServiceImpl customerInviteService = new CustomerInviteServiceImpl(customerFileParser);

        customerInviteService.inviteCustomerWithin100Km(53.339428, -6.257664);
    }
}