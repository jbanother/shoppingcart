package com.xgen.interview;

import static java.util.stream.Collectors.toList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {

    private final Map<Product, Integer> contents;

    private final Pricer pricer;
    private final ReceiptPrinter receiptPrinter;

    public ShoppingCart(Pricer pricer, ReceiptPrinter receiptPrinter) {
        Objects.requireNonNull(pricer, "pricer cannot be null");
        Objects.requireNonNull(receiptPrinter, "receiptPrinter cannot be null");
        this.contents = new LinkedHashMap<>();
        this.pricer = pricer;
        this.receiptPrinter = receiptPrinter;
    }

    public void addItem(String itemType, int number) {
        Product product = new Product(itemType, pricer.getPrice(itemType));
        int quantity = contents.getOrDefault(product, 0);
        contents.put(product, quantity + number);
    }

    public void printReceipt() {
        List<Receipt.Item> items = contents.entrySet().stream().map(this::item).collect(toList());
        receiptPrinter.printReceipt(new Receipt(items));
    }

    private Receipt.Item item(Map.Entry<Product, Integer> entry) {
        return new Receipt.Item(entry.getKey(), entry.getValue());
    }
}
