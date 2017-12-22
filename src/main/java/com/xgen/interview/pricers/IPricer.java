package com.xgen.interview.pricers;

/**
 * Interface for common Pricer
 * Handles prices for the specific item type
 * Implementation has to handle additional modification internally (f.e. tax rate)
 */
public interface IPricer {

    /**
     * @param itemType
     * @return Price of the item
     */
    Integer getPrice(String itemType);
}
