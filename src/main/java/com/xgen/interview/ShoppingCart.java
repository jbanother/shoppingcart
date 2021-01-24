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
    private String priceFormat;

    public ShoppingCart(IPricer pricer) {
        this.pricer = pricer;
    }

    /**
     * adds an item
     * @param itemType - The item being scanned by the hardware
     * @param number - The number of items the cashier is moving to the bagging area
     */
    public void addItem(String itemType, int number) {
        if (!validatePricer()) {
            return;
        }
        int newCount = 0;
        if (!contents.containsKey(itemType)) {
            newCount = number;
        } else {
            newCount = contents.get(itemType) + number;
        }
        contents.put(itemType, newCount);
    }

    public void printReceipt() {
        if (!validatePricer()) {
            return;
        }
        Float totalPrice = 0.0f;
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

    private boolean validatePricer() {
        if (this.pricer == null) {
            return false;
        }
        if (this.priceFormat == null) {
            this.priceFormat = pricer.getCurrency() + "%.2f";
        }
        return true;
    }
}
