package com.xgen.interview;

import java.util.Map;
import java.util.HashMap;


/**
 * A stub implementation - for this exercise, you may disregard that this is incomplete.
 */
public class Pricer implements IPricer {
    private final Map<String, Float> pricingDatabase = new HashMap<>(); // stub
    private final String currency;
    private final String priceFormat;

    public Pricer(String currency) {
        this.currency = currency;
        this.priceFormat = this.currency + "%.2f";
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String getPriceFormat() {
        return this.priceFormat;
    }

    @Override
    public void addOrUpdatePrice(String itemType, float price) {
        if (price < 0) {
            throw new IllegalArgumentException("price can not be negative");
        }
        pricingDatabase.put(itemType, price);
    }

    /**
     * Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
     * If itemType is an unknown string, store policy is that the item is free.
     */
    @Override
    public Float getPrice(String itemType) {
        if (!pricingDatabase.containsKey(itemType)) {
            return 0.0f;
        }
        return pricingDatabase.get(itemType) * 100;
    }

    @Override
    public void removeItem(String itemType) {
        pricingDatabase.remove(itemType);
    }

    @Override
    public Map<String, Float> getItems() {
        return new HashMap<>(pricingDatabase);
    }

    @Override
    public void initialize() {
        pricingDatabase.put("apple", 1.0f);
        pricingDatabase.put("banana", 2.0f);
    }
}
