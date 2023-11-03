package com.entertainment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TelevisionTest {
    private Television tv1;
    private Television tv2;

    @Before
    public void setUp() {
        tv1 = new Television("Samsung", 32, DisplayType.LED);
        tv2 = new Television("Samsung", 32, DisplayType.LED);
    }

    /* changeChannel() tests */
    @Test
    public void changeChannel_shouldStoreValue_whenValid_lowerBound() throws Exception {
        tv1.changeChannel(1);
        assertEquals(1, tv1.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreValue_whenValid_upperBound() throws Exception {
        tv1.changeChannel(999);
        assertEquals(999, tv1.getCurrentChannel());
    }

    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_lowerBound() throws Exception {
        tv1.changeChannel(0);
    }

    /*
     * You can use the 'expected' attribute AND try-catch-or-fail technique.
     * The 'expected' attribute clearly indicates that this is a test for exception.
     *  BUT the test fails if the exception is not propagated back to the JUnit test runner,
     *  i.e., if you catch it yourself.
     *  Solution: do your try-catch-and-check-e, then "throw e" back at the JUnit test runner.
     */
    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_upperBound() throws Exception {
        try {
            tv1.changeChannel(1000);
            fail("Should have thrown InvalidChannelException");
        }
        catch (InvalidChannelException e) {
            String expectedMessage = "Invalid channel: 1000. Allowed range: [1,999].";
            assertEquals(expectedMessage, e.getMessage());
            throw e;
        }
    }

    /* setVolume() tests */
    @Test
    public void setVolume_shouldStoreValue_whenValid_lowerBound() {
        tv1.setVolume(0);
        assertEquals(0, tv1.getVolume());
    }

    @Test
    public void setVolume_shouldStoreValue_whenValid_upperBound() {
        tv1.setVolume(100);
        assertEquals(100, tv1.getVolume());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_lowerBound() {
        tv1.setVolume(-1);  // trigger the exception
    }

    @Test
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_upperBound() {
        try {
            tv1.setVolume(101);
            fail("Should have thrown IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid volume: 101. Allowed range: [0,100].";
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /* compareTo() tests */
    @Test
    public void compareTo_shouldReturnZero_whenSameBrand() {
        assertTrue(tv1.compareTo(tv2) == 0);
    }

    @Test
    public void compareTo_shouldReturnNegativeNumber_when1stBrandLessThan2ndBrand() {
        tv1.setBrand("A_lessThan");
        tv2.setBrand("B_greaterThan");
        assertTrue(tv1.compareTo(tv2) < 0);
    }

    @Test
    public void compareTo_shouldReturnPositiveNumber_when1stBrandGreaterThan2ndBrand() {
        tv1.setBrand("B_greaterThan");
        tv2.setBrand("A_lessThan");
        assertTrue(tv1.compareTo(tv2) > 0);
    }

    /* equals() tests */
    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(tv1, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_sameVolume_differentDisplay() {
        tv2.setDisplay(DisplayType.PLASMA);
        assertNotEquals(tv1, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_differentVolume_sameDisplay() {
        tv2.setVolume(50);
        assertNotEquals(tv1, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_differentBrand_sameVolume_sameDisplay() {
        tv2.setBrand("DIFFERENT");
        assertNotEquals(tv1, tv2);
    }

    @Test
    public void hashCode_shouldReturnSameValue_equalObjects() {
        assertEquals(tv1.hashCode(), tv2.hashCode());
    }
}