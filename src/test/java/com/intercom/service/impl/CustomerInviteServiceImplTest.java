package com.intercom.service.impl;

import com.google.gson.Gson;
import com.intercom.model.Customer;
import com.intercom.model.GpsLocation;
import com.sun.deploy.util.ReflectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInviteServiceImplTest {

    @Test
    public void inviteCustomerWithin100Km() throws Exception {
        BufferedReader mockBufferReader = mock(BufferedReader.class);
        InputStreamReader mockInputStreamReader = mock(InputStreamReader.class);
        FileInputStream mockFileInputStream = mock(FileInputStream.class);

//        doReturn(mockFileInputStream).when(new FileInputStream(anyString()));
//        doReturn(mockInputStreamReader).when(new InputStreamReader(mockFileInputStream));
//        doReturn(mockBufferReader).when(new BufferedReader(mockInputStreamReader));

        String customerOne = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}";
        String customerTwo = "{\"latitude\": \"52.986375\", \"user_id\": 11, \"name\": \"Christina Ronaldo\", \"longitude\": \"-6.043701\"}";
        when(mockBufferReader.readLine()).thenReturn(customerOne.toString())
                .thenReturn(customerTwo.toString()).thenReturn(null);
        CustomerInviteServiceImpl customerInviteService = new CustomerInviteServiceImpl();
        String path = "D:\\OnlineJavaCourse\\intercom-customer-invite\\src\\main\\resources\\customers.txt";
        ArrayList<Customer> customers = customerInviteService.inviteCustomerWithin100Km(
                path, new GpsLocation(10.21, -10.33));
        assertEquals(11, customers.get(0).getUserId().longValue());
    }
}