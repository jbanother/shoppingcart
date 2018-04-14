package com.xgen.interview;
import java.util.ArrayList;
/**
 * This an the interface used to represent shopping cart items .
 */
public interface ICartItems {

    /**
     * Add an item.
     * @param itemType - The item being scanned by the hardware
     * @param number - The number of items the cashier is moving to the bagging area
     */
    void Add(String itemType, int number);
    /**
     * Return the shopping cart items.
     * TODO don't return ArrayList - user Iterable so we can hide the underlying collection
     */
    ArrayList<CartItem> Items();
    /**
     * Return the total of the cart items.
     */
    int GetTotal();

}
