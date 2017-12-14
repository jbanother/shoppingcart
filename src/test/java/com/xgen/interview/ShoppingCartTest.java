package com.xgen.interview;

import com.xgen.interview.printers.StandardOutPrinter;
import com.xgen.interview.pricers.Pricer;
import com.xgen.interview.ShoppingCart;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    @Test
    public void canCountTotalNumberOfItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        assertEquals(sc.countItems(), 0);

        sc.addItem("apple", 1);

        assertEquals(sc.countItems(), 1);

        sc.addItem("apple", 2);

        assertEquals(sc.countItems(), 3);

        sc.addItem("banana", 6);

        assertEquals(sc.countItems(), 9);
    }

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.addItem("apple", 1);

        assertEquals(sc.hasItem("apple"), 1);
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        assertEquals(sc.hasItem("apple"), 2);

        sc.addItem("banana", 5);

        assertEquals(sc.hasItem("apple"), 2);
        assertEquals(sc.hasItem("banana"), 5);
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        assertEquals(sc.countItems(), 0);
    }

    @Test
    public void canMaintainRunningTotalCost() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        assertEquals(sc.getTotalCost(), 0.0f, 0);

        sc.addItem("apple", 1);

        assertEquals(sc.getTotalCost(), 1.0f, 0);

        sc.addItem("apple", 2);

        assertEquals(sc.getTotalCost(), 3.0f, 0);

        sc.addItem("banana", 6);

        assertEquals(sc.getTotalCost(), 15.0f, 0);
    }

    @Test
    public void canPrintAReceipt() {
        ShoppingCart sc = new ShoppingCart(new Pricer(), new StandardOutPrinter());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nTotal Cost - €1.00%n"), myOut.toString());
    }
}
