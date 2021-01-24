package com.xgen.interview;

import java.util.Map;

/**
 * This is the interface used by the existing shopping cart as pricing database
 */
public interface IPricer {
    /**
     * returns currency value
     * @return currency symbol
     */
    String getCurrency();

    /**
     * returns <currency%.2f>
     * examples : "$4.09", "€2.1"
     * @return price formatting
     */
    String getPriceFormat();

    /**
     * initializes pricing database
     */
    void initialize();

    /**
     * adds or update an item with it's price
     * @param itemType an item to be added or updated
     * @param price the price of the item
     */
    void addOrUpdatePrice(String itemType, float price);

    /**
     * returns price of an item if item exists
     * @param itemType an item stored in database
     * @return integer unit price for the item
     */
    Float getPrice(String itemType);

    /**
     * removes an item from the data store
     * @param itemType name of the item
     */
    void removeItem(String itemType);

    /**
     * returns all items stored in pricing db
     * @return return dictionary of <item, price>
     */
    Map<String, Float> getItems();


}
