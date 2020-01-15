package com.intercom.model;

import org.junit.Assert;
import org.junit.Test;

public class TestCustomer {
    Customer customer = new Customer();
    @Test
    public void customerTest(){
        customer.setUserId(5);
        customer.setName("Test");
        customer.setLatitude(12.973947394);
        customer.setLongitude(-4.94738473);

        Assert.assertEquals(5, customer.getUserId());
        Assert.assertEquals("Test", customer.getName());
    }
}
