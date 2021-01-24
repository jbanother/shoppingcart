package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    private final Map<String, Integer> contents = new LinkedHashMap<>();
    private IPricer pricer;
    private String priceFormat; // examples : "$4.09", "€2.1"

    public ShoppingCart(IPricer pricer) {
        this.pricer = pricer;
    }

    /**
     * adds an item if item is new
     * updates quantity of an item if item exists
     * negative quantity is tolerated if resulting item count is more or equal to zero
     * otherwise throws RuntimeException
     * @param itemType - The item being scanned by the hardware
     * @param number - The number of items the cashier is moving to the bagging area
     * @throws RuntimeException if quantity becomes negative
     */
    public void addItem(String itemType, int number) {
        validatePricer();

        int newCount = 0;
        if (!contents.containsKey(itemType)) {
            newCount = number;
        } else {
            newCount = contents.get(itemType) + number;
        }
        if (newCount < 0) {
            throw new RuntimeException("quantity can not be zero");
        }
        contents.put(itemType, newCount);
    }

    /**
     * prints receipt of items, with total at the line
     * item line : <item_name> - <quantity> - <total_price_with_currency>
     * total line : Total - <total_cart_price_with_currency>
     * @throws IllegalArgumentException if pricer is invalid
     */
    public void printReceipt() {
        validatePricer();

        Float totalPrice = 0.0f;
        String priceFormat = pricer.getPriceFormat();
        StringBuilder receiptBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> itemEntry : contents.entrySet()) {
            String item = itemEntry.getKey();
            int quantity = itemEntry.getValue();
            Float price = ((pricer.getPrice(item) / 100.0f) * quantity) ;
            totalPrice += price;
            receiptBuilder.append(item)
                    .append(" - ")
                    .append(quantity)
                    .append(" - ")
                    .append(String.format(priceFormat, price))
                    .append("\n");
        }
        receiptBuilder.append("Total - ").append(String.format(priceFormat, totalPrice));
        System.out.println(receiptBuilder);
    }

    /**
     * validates if pricer exists
     */
    private void validatePricer() {
        if (this.pricer == null) {
            throw new IllegalArgumentException("pricer can not be null");
        }
    }
}
