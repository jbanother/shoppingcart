package com.xgen.interview;

import com.xgen.interview.carts.IShoppingCart;
import com.xgen.interview.carts.ShoppingCart;
import com.xgen.interview.pricers.Pricer;
import org.junit.Test;

import java.util.*;

public class ShoppingCartTest {

    private IShoppingCart createShoppingCart(Queue<String> itemsToScan) {
        return new ShoppingCart(new Pricer(), new MockDisplayer(itemsToScan));
    }

    @Test
    public void canAddAnItem() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("apple - 1 - €1.00");
        expectedResults.add("Total: €1.00");
        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.addItem("apple", 1);

        sc.printReceipt();
    }

    @Test
    public void canAddMoreThanOneItem() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("apple - 2 - €2.00");
        expectedResults.add("Total: €2.00");
        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.addItem("apple", 2);

        sc.printReceipt();
    }

    @Test
    public void canAddDifferentItems() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("apple - 2 - €2.00");
        expectedResults.add("banana - 1 - €2.00");
        expectedResults.add("Total: €4.00");
        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        sc.printReceipt();
    }

    @Test
    public void keepsScanOrder() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("banana - 11 - €22.00");
        expectedResults.add("cheese - 1 - €0.00");
        expectedResults.add("apple - 19 - €19.00");
        expectedResults.add("Total: €41.00");

        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.addItem("apple", 1);
        sc.addItem("banana", 3);
        sc.addItem("apple", 7);
        sc.addItem("banana", 1);
        sc.addItem("banana", 1);
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);
        sc.addItem("apple", 9);
        sc.addItem("banana", 5);
        sc.addItem("cheese", 1);
        sc.addItem("apple", 1);

        sc.printReceipt();
    }

    @Test
    public void doesntChargeIfNothingWasScanned() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("Total: €0.00");

        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.printReceipt();
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        Queue<String> expectedResults = new LinkedList<>();
        expectedResults.add("crisps - 2 - €0.00");
        expectedResults.add("Total: €0.00");

        IShoppingCart sc = createShoppingCart(expectedResults);

        sc.addItem("crisps", 2);
        sc.printReceipt();
    }
}
