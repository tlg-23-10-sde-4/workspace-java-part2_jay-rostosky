package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class SalariedEmployeeTest {
    // fixture -> business object(s) under test
    private SalariedEmployee emp;
    private SalariedEmployee emp2;

    @Before
    public void setUp() {
        emp  = new SalariedEmployee("Logan", Date.valueOf("2020-02-06"), 1500.0);
        emp2 = new SalariedEmployee("Logan", Date.valueOf("2020-02-06"), 1500.0);
    }

    @Test
    public void hashCode_equalObjectsMustHaveEqualHashCodes() {
        assertEquals(emp.hashCode(), emp2.hashCode());
    }

    @Test
    public void equals_shouldReturnFalse_differentName_sameHireDate_sameSalary() {
        emp2.setName("DIFFERENT");
        assertNotEquals(emp, emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_differentHireDate_sameSalary() {
        emp2.setHireDate(Date.valueOf("2023-11-02"));

        assertNotEquals(emp, emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_sameHireDate_differentSalary() {
        emp2.setSalary(1000.0);

        assertNotEquals(emp, emp2);
        assertFalse(emp.equals(emp2));
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(emp, emp2);       // does an equals() check
        assertTrue(emp.equals(emp2));  // alternative assertion
    }

    @Test
    public void testPay() {
        assertEquals(1500.0, emp.pay(), .001);      // expected value: salary
    }

    @Test
    public void testPayTaxes() {
        assertEquals(450.0, emp.payTaxes(), .001);  // expected value: salary * 0.30
    }
}