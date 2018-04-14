package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * I have removed the HashMap, I know it does not guarantee order and there is a requirment to have items in order
 * In doing so I know we now do not group items but have made the decision not group items. If this requirement is needed
 * it can be added in the future
 */
public class ShoppingCart implements IShoppingCart {
    private ICartItems cartItems;
    private IReceipt receipt;

    public ShoppingCart(ICartItems cartItems, IPriceFormatter priceFormatter, IReceipt receipt) {
        this.cartItems = cartItems;
        this.receipt = receipt;
    }

    public void addItem(String itemType, int number) {
        cartItems.Add(itemType, number);
    }

    public void printReceipt() {
        for (String line : receipt.GenerateContents(cartItems)) {
            System.out.println(line);
        }



    }
}
