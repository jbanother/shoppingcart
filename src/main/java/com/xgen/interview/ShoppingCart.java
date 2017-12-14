package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;

public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Queue<String> contentsOrder = new LinkedList<String>();
    int numberOfItems = 0;

    Pricer pricer;
    IPrinter printer;

    /**
     * Backwards compatible constructor
     */
    public ShoppingCart(Pricer pricer) {
        this(pricer, new StandardOutPrinter());
    }

    /**
     * New constructor to allow overriding the printer
     */
    public ShoppingCart(Pricer pricer, IPrinter printer) {
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
        if (0 == this.pricer.getPrice(itemType)) {
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
    }

    public void printReceipt() {
        Object[] keys = contents.keySet().toArray();

        float totalCost = 0.0f;

        for (String itemType : this.contentsOrder) {
            Integer price = pricer.getPrice(itemType) * contents.get(itemType);
            Float priceFloat = new Float(new Float(price) / 100);
            String priceString = String.format("€%.2f", priceFloat);
            totalCost += priceFloat;

            this.printer.printLine(itemType + " - " + contents.get(itemType) + " - " + priceString);
        }

        this.printer.printLine(String.format("Total Cost - €%.2f", totalCost));
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
