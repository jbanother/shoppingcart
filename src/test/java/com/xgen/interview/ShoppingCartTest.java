package com.xgen.interview;

import com.xgen.interview.Pricer;
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
}
