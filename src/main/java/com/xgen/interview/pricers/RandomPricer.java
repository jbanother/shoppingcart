package com.xgen.interview.pricers;

import java.util.HashMap;
import java.util.Random;

/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class RandomPricer implements IPricer {
    private static int MIN_PRICE = 1;
    private static int MAX_PRICE = 999;

    HashMap<String, Integer> pricingDatabase = new HashMap<>(); // stub

    public RandomPricer() {
        Random randomGenerator = new Random();

        pricingDatabase.put("apple", randomGenerator.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE);
        pricingDatabase.put("banana", randomGenerator.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE);
    }

    /**
     * Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    public Integer getPrice(String itemType) {
        if (!pricingDatabase.containsKey(itemType)) {
            return 0;
        }
        return pricingDatabase.get(itemType);
    }
}
