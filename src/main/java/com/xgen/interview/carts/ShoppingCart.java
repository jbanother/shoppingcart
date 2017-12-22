package com.xgen.interview.carts;

import com.xgen.interview.displayers.IDisplayer;
import com.xgen.interview.pricers.IPricer;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 */
public class ShoppingCart implements IShoppingCart {
    private LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    private IPricer pricer;
    private IDisplayer displayer;

    public ShoppingCart(IPricer pricer, IDisplayer displayer) {
        this.pricer = pricer;
        this.displayer = displayer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.remove(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();

        float totalSum = 0.0f;
        for (int i = 0; i < Array.getLength(keys) ; i++) {
            Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            Float priceFloat = new Float(price) / 100;
            totalSum += priceFloat;
            String priceString = String.format("€%.2f", priceFloat);

            displayer.Display(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
        }
        displayer.Display("Total: " + String.format("€%.2f", totalSum));
    }
}
