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
    private Float totalPrice;
    private final String priceFormat;

    public ShoppingCart(IPricer pricer) {
        this.pricer = pricer;
        this.priceFormat = pricer.getCurrency() + "%.2f";
        this.totalPrice = 0.0f;
    }

    /**
     * adds an item
     * @param itemType - The item being scanned by the hardware
     * @param number - The number of items the cashier is moving to the bagging area
     */
    public void addItem(String itemType, int number) {
        int oldCount = 0;
        int newCount = 0;
        if (!contents.containsKey(itemType)) {
            newCount = number;
        } else {
            oldCount = contents.get(itemType);
            newCount = oldCount + number;
        }
        contents.put(itemType, newCount);
        updateTotalPrice(itemType, oldCount, newCount);
    }

    public void printReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> itemEntry : contents.entrySet()) {
            String item = itemEntry.getKey();
            int quantity = itemEntry.getValue();
            Float price = ((pricer.getPrice(item) / 100.0f) * quantity) ;
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

    private void updateTotalPrice(String itemType, int oldItemCount, int newItemCount) {
        Float itemPrice = pricer.getPrice(itemType) / 100.f;
        this.totalPrice -= itemPrice * oldItemCount;
        this.totalPrice += itemPrice * newItemCount;
    }
}
