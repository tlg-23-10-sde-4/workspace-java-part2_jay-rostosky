package com.javatunes.billing;

import org.junit.Test;
import static org.junit.Assert.*;

public class TaxCalculatorFactoryTest {

    @Test
    public void getTaxCalculator_shouldReturnSameInstance_multipleInvocations() {
        TaxCalculator calc1 = TaxCalculatorFactory.getTaxCalculator(Location.ONLINE);
        TaxCalculator calc2 = TaxCalculatorFactory.getTaxCalculator(Location.ONLINE);

        // prove that a reference to the same object was returned each time
        assertSame(calc1, calc2);  // this does an == check on references 'calc1' and 'calc2'
    }
}