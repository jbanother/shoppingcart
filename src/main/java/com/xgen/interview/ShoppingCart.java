package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;

import com.xgen.interview.pricers.IPricer;
import com.xgen.interview.printers.IPrinter;
import com.xgen.interview.printers.StandardOutPrinter;

public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Queue<String> contentsOrder = new LinkedList<String>();

    int numberOfItems = 0;
    float totalCost = 0.0f;

    IPricer pricer;
    IPrinter printer;

    /**
     * Backwards compatible constructor
     */
    public ShoppingCart(IPricer pricer) {
        this(pricer, new StandardOutPrinter());
    }

    /**
     * New constructor to allow overriding the printer
     */
    public ShoppingCart(IPricer pricer, IPrinter printer) {
        this.pricer = pricer;
        this.printer = printer;
    }

    /**
     * When are able to change this interface, this should probably throw
     * an UnknownItemException and should accept an encapsulated type as it's only
     * parameter: Product and return another type: ShoppingCartItem
     */
    public void addItem(String itemType, int number) {
        // Unkown item. I don't think we should assume a 0 priced item is unknown,
        // but that's how it is for now.
        int price = this.pricer.getPrice(itemType).intValue();

        if (0 == price) {
            return;
        }

        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
            this.contentsOrder.add(itemType);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }

        this.numberOfItems += number;
        this.totalCost += (float)(price * number) / 100;
    }

    public void printReceipt() {
        for (String itemType : this.contentsOrder) {
            this.printReceiptLineItem(itemType);
        }

        this.printReceiptTotal();
    }

    private void printReceiptLineItem(String itemType) {
        int quantity = contents.get(itemType);

        float priceFloat = (float)(this.pricer.getPrice(itemType) * quantity) / 100;

        this.printer.printLine(String.format("%s - %d - €%.2f", itemType, quantity, priceFloat));
    }

    private void printReceiptTotal() {
        this.printer.printLine("Total Cost - €" + String.format("%.2f", this.totalCost));
    }

    @Override
    public float getTotalCost() {
        return this.totalCost;
    }

	@Override
	public int countItems() {
		return this.numberOfItems;
	}

	@Override
	public int hasItem(String itemType) {
		return this.contents.get(itemType);
	}
}
