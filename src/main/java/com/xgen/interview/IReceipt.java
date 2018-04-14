package com.xgen.interview;

import java.util.ArrayList;
/**
 * This is the interface represents a receipt.
 */
public interface IReceipt {
    /**
     * Called to generate the contents of the receipt.
     * @param cartItems - The items in the shopping cart
     */
    ArrayList<String> GenerateContents(ICartItems cartItems);
}
