package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;

public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Queue<String> contentsOrder = new LinkedList<String>();

    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
            this.contentsOrder.add(itemType);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();

        float totalCost = 0.0f;

        for (String itemType : this.contentsOrder) {
            Integer price = pricer.getPrice(itemType) * contents.get(itemType);
            Float priceFloat = new Float(new Float(price) / 100);
            String priceString = String.format("€%.2f", priceFloat);
            totalCost += priceFloat;

            System.out.println(itemType + " - " + contents.get(itemType) + " - " + priceString);
        }

        System.out.println(String.format("Total Cost - €%.2f", totalCost));
    }
}
