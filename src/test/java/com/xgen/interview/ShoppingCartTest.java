package com.xgen.interview;

import com.xgen.interview.printers.StandardOutPrinter;
import com.xgen.interview.pricers.Pricer;
import com.xgen.interview.pricers.RandomPricer;
import com.xgen.interview.ShoppingCart;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    @Test
    public void canCountTotalNumberOfItems() {
        ShoppingCart sc = new ShoppingCart(new RandomPricer());

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
        ShoppingCart sc = new ShoppingCart(new RandomPricer());
        sc.addItem("apple", 1);

        assertEquals(sc.hasItem("apple"), 1);
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new RandomPricer());

        sc.addItem("apple", 2);

        assertEquals(sc.hasItem("apple"), 2);

        sc.addItem("banana", 5);

        assertEquals(sc.hasItem("apple"), 2);
        assertEquals(sc.hasItem("banana"), 5);
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new RandomPricer());

        sc.addItem("crisps", 2);

        assertEquals(sc.countItems(), 0);
    }

    @Test
    public void canMaintainRunningTotalCost() {
        RandomPricer randomPricer = new RandomPricer();

        ShoppingCart sc = new ShoppingCart(randomPricer);

        int appleCost = randomPricer.getPrice("apple");
        int bananaCost = randomPricer.getPrice("banana");

        assertEquals(sc.getTotalCost(), 0.0f, 0);

        sc.addItem("apple", 1);

        assertEquals(sc.getTotalCost(), (float)(appleCost * 1) / 100, 0);

        sc.addItem("apple", 2);

        assertEquals(sc.getTotalCost(), (float)(appleCost * 3) / 100, 0);

        sc.addItem("banana", 6);

        float totalCost = (float) ((appleCost * 3) + (bananaCost * 6)) / 100;

        assertEquals(sc.getTotalCost(), totalCost, 0);
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
