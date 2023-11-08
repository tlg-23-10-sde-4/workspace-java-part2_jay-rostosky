package com.javatunes.billing;

import java.util.HashMap;
import java.util.Map;

public class TaxCalculatorFactory {
    /*
     * Cache of previously-created TaxCalculator objects, keyed by Location (initially empty).
     *
     * Location         TaxCalculator
     * --------         -------------
     * ONLINE           OnlineTax object
     * USA              USATax    object
     * EUROPE           EuropeTax object
     */
    // private static final Map<Location,TaxCalculator> calcMap = new HashMap<>();

    private static final Map<Location,TaxCalculator> calcMap = Map.of(
            Location.ONLINE, new OnlineTax(),
            Location.USA,    new USATax(),
            Location.EUROPE, new EuropeTax()
    );

    // prevent instantiation from outside, this is an all-static class
    private TaxCalculatorFactory() {
    }

    /*
     * TODO: Consider implementing a "cache" of TaxCalculator objects.
     *
     * If I have not previously created the object (e.g., USATax), i.e., it's not in the cache,
     * then I'll create it here (with "new"), add it to my cache, and then return it.
     *
     * However, if my cache already contains it, then I just fetch it
     * from the cache and return it - no need to create another one.
     *
     * Hint: you could use a simple Map<Location,TaxCalculator> for the cache.
     * It would be a 3-row Map, each row has Location | TaxCalculator
     */
    public static TaxCalculator getTaxCalculator(Location location) {
        /*
        if (!calcMap.containsKey(location)) {
            switch (location) {
                case ONLINE:
                    calcMap.put(location, new OnlineTax());
                    break;
                case USA:
                    calcMap.put(location, new USATax());
                    break;
                case EUROPE:
                    calcMap.put(location, new EuropeTax());
            }
        }
        */
        return calcMap.get(location);
    }
}