package org.gradle;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nTotal - €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nTotal - €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal - €4.00%n"), myOut.toString());
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        
        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nTotal - €0.00%n"), myOut.toString());
    }
    
    @Test
    public void addingManyItemsPreservestheOrder() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("cucumber", 1);
        sc.addItem("watermelon", 1);
        sc.addItem("okonomiyaki", 1);
        sc.addItem("carrot", 1);
        sc.addItem("broccoli", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        
        sc.printReceipt();
       
        String[] receiptContent = myOut.toString().split(String.format("%n"));
        	
        assertEquals(receiptContent[0], "crisps - 2 - €0.00");
        assertEquals(receiptContent[1], "apple - 2 - €2.00");
        assertEquals(receiptContent[2], "banana - 1 - €2.00");
        assertEquals(receiptContent[3], "cucumber - 1 - €0.00");
        assertEquals(receiptContent[4], "watermelon - 1 - €0.00");
        assertEquals(receiptContent[5], "okonomiyaki - 1 - €0.00");
        assertEquals(receiptContent[6], "carrot - 1 - €0.00");
        assertEquals(receiptContent[7], "broccoli - 1 - €0.00");
        	
        assertEquals(receiptContent[8], "Total - €4.00");

    }
  
}


