package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;

public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
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
        Object[] keys = contents.keySet().toArray();

        float totalCost = 0.0f;

        for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(new Float(price) / 100);
            String priceString = String.format("€%.2f", priceFloat);
            totalCost += priceFloat;

            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }

        System.out.println(String.format("Total Cost - €%.2f", totalCost));
    }
}
