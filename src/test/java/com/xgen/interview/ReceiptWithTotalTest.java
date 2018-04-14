package com.xgen.interview;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReceiptWithTotalTest {
    @Test
    public void receiptWithItemsGeneratedCorrectly() {
        IPricer pricer = new Pricer();
        IPriceFormatter euroFormatter = new EuroPriceFormatter();
        ReceiptWithTotal receipt = new ReceiptWithTotal(pricer, euroFormatter);
        ICartItems cartItems = new OrderedCartItems(pricer);
        cartItems.Add("apple", 2);
        cartItems.Add("banana", 1);
        cartItems.Add("cabbage", 4);
        cartItems.Add("banana", 2);

        ArrayList<String> receiptContents = receipt.GenerateContents(cartItems);
        assertEquals(5, receiptContents.size());
        assertEquals("apple - 2 - €2.00", receiptContents.get(0));
        assertEquals("banana - 1 - €2.00", receiptContents.get(1));
        assertEquals("cabbage - 4 - €0.00", receiptContents.get(2));
        assertEquals("banana - 2 - €4.00", receiptContents.get(3));
        assertEquals("Total - €8.00", receiptContents.get(4));
    }

    @Test
    public void receiptWithNoItemsGeneratedCorrectly() {
        IPricer pricer = new Pricer();
        IPriceFormatter euroFormatter = new EuroPriceFormatter();
        ReceiptWithTotal receipt = new ReceiptWithTotal(pricer, euroFormatter);
        ICartItems cartItems = new OrderedCartItems(pricer);

        ArrayList<String> receiptContents = receipt.GenerateContents(cartItems);
        assertEquals(1, receiptContents.size());
        assertEquals("Total - €0.00", receiptContents.get(0));
    }
}
