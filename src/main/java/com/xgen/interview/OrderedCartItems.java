package com.xgen.interview;

import java.util.ArrayList;

public class OrderedCartItems implements ICartItems{

    private ArrayList<CartItem> contents = new ArrayList<>();

    private IPricer pricer;

    public OrderedCartItems(IPricer pricer) {
        this.pricer = pricer;
    }

    public void Add(String itemType, int number) {
        CartItem item = new CartItem();
        item.setItemType(itemType);
        item.setQuantity(number);
        this.contents.add(item);
    }

    public ArrayList<CartItem> Items() {
        return this.contents;
    }

    public int GetTotal() {
        int total = 0;
        for (CartItem item : this.contents) {
            total = (total
                    + (this.pricer.getPrice(item.getItemType()) * item.getQuantity()));
        }

        return total;
    }
}
