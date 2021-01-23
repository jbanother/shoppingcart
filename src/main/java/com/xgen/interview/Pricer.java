package com.xgen.interview;

import java.util.Map;
import java.util.HashMap;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer implements IPricer {
    private final Map<String, Integer> pricingDatabase = new HashMap<>(); // stub
    private final String currency;

    public Pricer(String currency) {
        this.currency = currency;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public void addOrUpdatePrice(String itemType, int price) {
        pricingDatabase.put(itemType, price);
    }

    /**
     * Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    @Override
    public Integer getPrice(String itemType) {
        if (!pricingDatabase.containsKey(itemType)) {
            return 0;
        }
        return pricingDatabase.get(itemType) * 100;
    }

    @Override
    public void removeItem(String itemType) {
        pricingDatabase.remove(itemType);
    }

    @Override
    public Map<String, Integer> getItems() {
        return new HashMap<>(pricingDatabase);
    }

    @Override
    public void initialize() {
        pricingDatabase.put("apple", 100);
        pricingDatabase.put("banana", 200);
    }
}
