package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        if (pricer == null) {
            throw new NullPointerException("pricer cannot be null");
        }

        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Integer totalPrice = 0;

        for (Map.Entry<String,Integer> entry : contents.entrySet()) {
            String itemType = entry.getKey();
            Integer quantity = entry.getValue();
            Integer price = pricer.getPrice(itemType) * quantity;
            
            totalPrice += price;

            System.out.println(itemType + " - " + quantity + " - " + formatMoney(price));
        }

        System.out.println("total - " + formatMoney(totalPrice));
    }

    private String formatMoney(Integer value) {
        return String.format("€%.2f", value / 100f); 
    }
}
