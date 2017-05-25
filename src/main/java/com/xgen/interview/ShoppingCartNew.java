package com.xgen.interview;

import java.util.*;

/**
 * IShoppingCart implementation
 *
 * - Will print receipt in the same order as the items are added
 * - Can print a "Total" line at the end of the receipt
 */
public class ShoppingCartNew implements IShoppingCart {
    private Map<String, Integer> contents = new LinkedHashMap<>();
    private Pricer pricer;

    /**
     * ShoppingCartNew constructor.
     * @param pricer - Pricer object to use.
     */
    public ShoppingCartNew(Pricer pricer) {
        this.pricer = pricer;
    }

    /**
     * Adds a new item to the receipt.
     * @param itemType - Type of the item, should be recognizable by the pricer,
     *                   otherwise it will have zero price.
     * @param number - Quantity of items of this type to add. Should be positive.
     */
    @Override
    public void addItem(String itemType, int number) {
        if (number < 1) return;

        int currentQty = contents.getOrDefault(itemType, 0);
        contents.put(itemType, currentQty + number);
    }

    /**
     * Prints receipt to standard output. Will print "Total" line.
     */
    @Override
    public void printReceipt() {
        printReceipt(true);
    }

    /**
     * Prints receipt to standard output.
     * @param printTotal - indicates if "Total" line should be printed
     */
    public void printReceipt(boolean printTotal) {
        int priceTotal = 0;

        for (Map.Entry<String, Integer> e : contents.entrySet()) {
            String item = e.getKey();
            int qty = e.getValue();

            int price = getArticlePrice(item, qty);
            priceTotal += price;

            printLine(item, qty, formatPrice(price));
        }

        if (printTotal) {
            printLine("Total", formatPrice(priceTotal));
        }
    }

    /**
     * Returns the price for a given row.
     */
    private int getArticlePrice(String key, int qty) {
        return pricer.getPrice(key) * qty;
    }

    /**
     * Prints the expected receipt output for each line.
     */
    private void printLine(Object... args) {
        List<String> lst = new ArrayList<>();
        for (Object obj: args) {
            lst.add(obj.toString());
        }

        System.out.format("%s\n", String.join(" - ", lst));
    }

    /**
     * Returns the String representation of the price.
     * @param price - Integer price to be returned as String.
     */
    private String formatPrice(int price) {
        return String.format("€%.2f", price / 100.0f);
    }

    /**
     * Returns number of items in the receipt.
     *
     * NOTE: this is to facilitate testing.
     */
    public int getReceiptSize() {
        return contents.size();
    }
}
