package org.gradle;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCartNew;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("apple - 1 - €1.00\n", myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("apple - 2 - €2.00\n", myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("bananna", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("apple - 2 - €2.00\nbananna - 1 - €2.00\n", myOut.toString());
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("crisps - 2 - €0.00\n", myOut.toString());
    }

    @Test
    public void willPrintReceiptsInOrder() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 2);
        sc.addItem("apple", 3);
        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("bananna - 2 - €4.00\napple - 3 - €3.00\ncrisps - 2 - €0.00\n",
                     myOut.toString());
    }

    @Test
    public void willKeepOrderOnMultipleInsertionsSameItem() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 1);
        sc.addItem("apple", 3);
        sc.addItem("crisps", 2);
        sc.addItem("apple", 2);
        sc.addItem("bananna", 2);


        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(true);
        String details = "bananna - 3 - €6.00\napple - 5 - €5.00\ncrisps - 2 - €0.00\n";
        String total = "Total - €11.00\n";
        assertEquals(details + total, myOut.toString());
    }

    @Test
    public void printsReceiptTotal() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 2);
        sc.addItem("apple", 3);
        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(true);
        String details = "bananna - 2 - €4.00\napple - 3 - €3.00\ncrisps - 2 - €0.00\n";
        String total = "Total - €7.00\n";
        assertEquals(details + total, myOut.toString());
    }

    @Test
    public void willNotPrintReceiptIfNotAskedTo() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 2);
        sc.addItem("apple", 3);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        String details = "bananna - 2 - €4.00\napple - 3 - €3.00\n";
        assertEquals(details, myOut.toString());
    }

    @Test
    public void contentsSizeIsMaintained() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);

        assertEquals(sc.getReceiptSize(), 1);
    }

    @Test
    public void contentsSizeIsAmountOfItemsAdded() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);
        assertEquals(sc.getReceiptSize(), 1);

        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);
        sc.addItem("bananna", 1);
        assertEquals(sc.getReceiptSize(), 1);

        sc.addItem("apple", 1);
        sc.addItem("apple", 3);
        assertEquals(sc.getReceiptSize(), 2);

        sc.addItem("crisps", 1);
        sc.addItem("apple", 3);
        assertEquals(sc.getReceiptSize(), 3);
    }

    @Test
    public void negativeQtyIsNotAdded() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        sc.addItem("bananna", 0);
        assertEquals(sc.getReceiptSize(), 0);

        sc.addItem("bananna", -1);
        assertEquals(sc.getReceiptSize(), 0);

        sc.addItem("bananna", 10);
        assertEquals(sc.getReceiptSize(), 1);
    }

    @Test
    public void canPrintEmptyReceipt() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt(false);
        assertEquals("", myOut.toString());
    }

    @Test
    public void canPrintEmptyTotal() {
        ShoppingCartNew sc = new ShoppingCartNew(new Pricer());

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals("Total - €0.00\n", myOut.toString());
    }

}
