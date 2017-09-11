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
        
        Integer total = 0;
        
        for (int i = 0; i < Array.getLength(keys) ; i++) {
        	Integer price = pricer.getPrice((String)keys[i]) * contents.get(keys[i]);
            
            Float priceFloat = new Float(price / 100);
            String priceString = String.format("€%.2f", priceFloat);
            System.out.println(keys[i] + " - " + contents.get(keys[i]) + " - " + priceString);
            
            total = total + price;
        }
        
        Float totalFloat = new Float(total/100);
        String totalPriceString = String.format("€%.2f", totalFloat);
        System.out.println("Total - " + totalPriceString);

    }
}
