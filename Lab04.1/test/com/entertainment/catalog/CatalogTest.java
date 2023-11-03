/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.entertainment.catalog;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import com.entertainment.Television;

public class CatalogTest {

    @Test(expected=UnsupportedOperationException.class)
    public void getInventory_shouldReturnReadOnlyCollection() {
        Collection<Television> tvs = Catalog.getInventory();  // should be read-only
        tvs.clear();  // should throw exception
    }

    @Test
    public void findByBrands_shouldReturnPopulatedMap_brandsPassed_2() {
        // recall: you can pass an array for a varargs parameter
        String[] brands = { "Sony", "Zenith", "RCA", "Magnavox" };

        Map<String,Collection<Television>> tvMap = Catalog.findByBrands(brands);

        assertEquals(brands.length, tvMap.size());

        for (String brand : brands) {
            verifyCollection(brand, tvMap.get(brand));
        }
    }

    @Test
    public void findByBrands_shouldReturnPopulatedMap_brandsPassed_1() {
        Map<String,Collection<Television>> tvMap = Catalog.findByBrands("Sony", "Zenith");

        assertEquals(2, tvMap.size());

        Collection<Television> sonyTvs = tvMap.get("Sony");
        verifyCollection("Sony", sonyTvs);

        Collection<Television> zenithTvs = tvMap.get("Zenith");
        verifyCollection("Zenith", zenithTvs);
    }

    private void verifyCollection(String brand, Collection<Television> tvs) {
        for (Television tv : tvs) {
            assertEquals(brand, tv.getBrand());
        }
    }

    @Test
    public void findByBrands_shouldReturnEmptyMap_noBrandsPassed() {
        Map<String,Collection<Television>> tvMap = Catalog.findByBrands();

        assertTrue(tvMap.isEmpty());
    }

    @Test
    public void findByBrand_shouldReturnPopulatedCollection_brandFound() {
        Collection<Television> tvs = Catalog.findByBrand("Sony");

        assertEquals(7, tvs.size());
        for (Television tv : tvs) {
            assertEquals("Sony", tv.getBrand());
        }
    }

    /**
     * Contract: a no-matches result should be an empty collection (not null).
     */
    @Test
    public void findByBrand_shouldReturnEmptyCollection_brandNotFound() {
        Collection<Television> tvs = Catalog.findByBrand("NO-MATCHES");
        // assertNotNull(tvs);
        assertTrue(tvs.isEmpty());
    }
}