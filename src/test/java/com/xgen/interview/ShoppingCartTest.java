package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));

        assertEquals("apple - 1 - €1.00", lines[0]);
        assertEquals("total - €1.00", lines[1]);
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));
        
        assertEquals("apple - 2 - €2.00", lines[0]);
        assertEquals("total - €2.00", lines[1]);
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));

        assertEquals("apple - 2 - €2.00", lines[0]);
        assertEquals("banana - 1 - €2.00", lines[1]);
    }

    @Test
    public void preserveInsertionOrder() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);
        sc.addItem("banana", 1);
        sc.addItem("avocado", 1);
        sc.addItem("blueberry", 1);
        sc.addItem("cherry", 1);
        sc.addItem("kiwi", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));

        assertEquals("apple - 1 - €1.00", lines[0]);
        assertEquals("banana - 1 - €2.00", lines[1]);
        assertEquals("avocado - 1 - €3.00", lines[2]);
        assertEquals("blueberry - 1 - €4.00", lines[3]);
        assertEquals("cherry - 1 - €5.00", lines[4]);
        assertEquals("kiwi - 1 - €6.00", lines[5]);
        assertEquals("total - €21.00", lines[6]);
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));

        assertEquals("crisps - 2 - €0.00", lines[0]);
        assertEquals("total - €0.00", lines[1]);
    }

    @Test
    public void receiptWithoutItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] lines = myOut.toString().split(String.format("%n"));

        assertEquals("total - €0.00", lines[0]);
    }

    @Test(expected = NullPointerException.class)
    public void pricerNull() {
        new ShoppingCart(null);
    }
}


