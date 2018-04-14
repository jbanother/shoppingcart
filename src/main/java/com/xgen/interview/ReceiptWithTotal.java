package com.xgen.interview;

import java.util.ArrayList;

public class ReceiptWithTotal implements IReceipt {

    private IPricer pricer;
    private IPriceFormatter priceFormatter;

    public ReceiptWithTotal(IPricer pricer, IPriceFormatter priceFormatter) {
        this.pricer = pricer;
        this.priceFormatter = priceFormatter;
    }

    /**
     * Returns the lines of the receipt with the item, quantity and price
     * Returns a total line at the end with the total value of all the items
     * Uses the priceFormatter to format the prices
     */
    public ArrayList<String> GenerateContents(ICartItems cartItems) {
        ArrayList<String> contents = new ArrayList<>();
        for (CartItem item : cartItems.Items()) {
            int rowPrice = pricer.getPrice(item.getItemType()) * item.getQuantity();
            contents.add(item.getItemType() + " - " + item.getQuantity() + " - " + priceFormatter.FormattedPrice(rowPrice));
        }
        //Add in the total
        contents.add("Total" + " - " +  priceFormatter.FormattedPrice(cartItems.GetTotal()));
        return contents;
    }
}
