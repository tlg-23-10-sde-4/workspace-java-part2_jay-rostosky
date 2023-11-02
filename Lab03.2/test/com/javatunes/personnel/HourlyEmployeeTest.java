package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class HourlyEmployeeTest {
    // fixture -> business object(s) under test
    private HourlyEmployee emp;

    @Before
    public void setUp() {
        emp = new HourlyEmployee("Amilia", Date.valueOf("2020-06-02"), 25.0, 30.0);
    }

    @Test
    public void testPay() {
        assertEquals(750.0, emp.pay(), .001);       // expected value: rate * hours
    }

    @Test
    public void testPayTaxes() {
        assertEquals(187.5, emp.payTaxes(), .001);  // expected value: rate * hours * 0.25
    }
}