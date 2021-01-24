package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ShoppingCartTest {
    private ShoppingCart sc;
    private IPricer pricer;

    @Before
    public void initialize() {
        pricer = PricerFactory.getPricer();
        sc = new ShoppingCart(pricer);
    }

    @Test
    public void invalidPricer() throws IOException {
        ShoppingCart cart = new ShoppingCart(null);
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        cart.printReceipt();
        assertEquals(dataLines(myOut).length, 0);
    }

    @Test
    public void willPrintTotalForEmptyShoppingCart() throws IOException {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        assertEquals(String.format("Total - €0.00"), dataLines(myOut)[0]);
    }

    @Test
    public void canAddAnItem() throws IOException {
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        assertEquals(String.format("apple - 1 - €1.00"), dataLines(myOut)[0]);
    }

    @Test
    public void calculateTotalOnAddItem() throws IOException {
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        assertEquals(String.format("Total - €1.00"), dataLines(myOut)[1]);
    }

    @Test
    public void ensureTotalOnLastLine() throws IOException {
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);
        assertEquals(result.length, 2);
        assertEquals(String.format("Total - €1.00"), result[1]);
    }

    @Test
    public void canAddMoreThanOneItem() throws IOException {
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00"), dataLines(myOut)[0]);
    }

    @Test
    public void calculateTotalOnAddMoreThanOneItem() throws IOException {
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("Total - €2.00"), dataLines(myOut)[1]);
    }

    @Test
    public void canAddDifferentItems() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("apple - 2 - €2.00"), result[0]);
        assertEquals(String.format("banana - 1 - €2.00"), result[1]);
    }

    @Test
    public void calculateTotalOnAddDifferentItems() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("Total - €4.00"), result[2]);
    }

    @Test
    public void doesntExplodeOnMysteryItem() throws IOException {
        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00"), dataLines(myOut)[0]);
    }

    @Test
    public void calculateTotalOnMysteryItem() throws IOException {
        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("Total - €0.00"), dataLines(myOut)[1]);
    }

    @Test
    public void doesntExplodeOnDeletingItem() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("apple", -1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00"), dataLines(myOut)[0]);
    }

    @Test
    public void calculateTotalOnDeletingItem() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("apple", -1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("Total - €1.00"), dataLines(myOut)[1]);
    }

    @Test
    public void calculateTotalOnMixedItems() throws IOException {
        sc.addItem("crisp", 1);
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("Total - €3.00"), result[3]);
    }

    @Test
    public void maintainsInsertionOrder() throws IOException {
        sc.addItem("crisp", 1);
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("crisp - 1 - €0.00"), result[0]);
        assertEquals(String.format("banana - 1 - €2.00"), result[1]);
        assertEquals(String.format("apple - 1 - €1.00"), result[2]);
    }

    @Test
    public void maintainsItemOrder() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("apple - 4 - €4.00"), result[0]);
        assertEquals(String.format("banana - 1 - €2.00"), result[1]);
    }

    @Test
    public void calculateTotalOnAddingItemsMoreThanOnce() throws IOException {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("Total - €6.00"), result[2]);
    }

    @Test
    public void willUpdateReceiptIfMysteryItemAddedToPricer() throws IOException {
        sc.addItem("crisp", 1);
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("crisp - 1 - €0.00"), result[0]);
        assertEquals(String.format("banana - 1 - €2.00"), result[1]);
        assertEquals(String.format("apple - 1 - €1.00"), result[2]);
        assertEquals(String.format("Total - €3.00"), result[3]);

        // update price in pricer
        pricer.addOrUpdatePrice("crisp", 4);

        final ByteArrayOutputStream anotherOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(anotherOut));

        sc.printReceipt();

        result = dataLines(anotherOut);
        assertEquals(String.format("crisp - 1 - €4.00"), result[0]);
        assertEquals(String.format("banana - 1 - €2.00"), result[1]);
        assertEquals(String.format("apple - 1 - €1.00"), result[2]);
        assertEquals(String.format("Total - €7.00"), result[3]);

    }

    @Test
    public void willUpdateReceiptIfItemDeletedFromPricer() throws IOException {
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("banana - 1 - €2.00"), result[0]);
        assertEquals(String.format("apple - 1 - €1.00"), result[1]);
        assertEquals(String.format("Total - €3.00"), result[2]);

        // update price in pricer
        pricer.removeItem("apple");

        final ByteArrayOutputStream anotherOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(anotherOut));

        sc.printReceipt();

        result = dataLines(anotherOut);
        assertEquals(String.format("banana - 1 - €2.00"), result[0]);
        assertEquals(String.format("apple - 1 - €0.00"), result[1]);
        assertEquals(String.format("Total - €2.00"), result[2]);

    }

    @Test
    public void willUpdateReceiptIfItemPriceIsUpdated() throws IOException {
        sc.addItem("banana", 1);
        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("banana - 1 - €2.00"), result[0]);
        assertEquals(String.format("apple - 1 - €1.00"), result[1]);
        assertEquals(String.format("Total - €3.00"), result[2]);

        // update price in pricer
        pricer.addOrUpdatePrice("apple", 3);

        final ByteArrayOutputStream anotherOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(anotherOut));

        sc.printReceipt();

        result = dataLines(anotherOut);
        assertEquals(String.format("banana - 1 - €2.00"), result[0]);
        assertEquals(String.format("apple - 1 - €3.00"), result[1]);
        assertEquals(String.format("Total - €5.00"), result[2]);

    }

    @Test
    public void pricerWithCustomCurrency() throws IOException {
        ShoppingCart cart = new ShoppingCart(PricerFactory.getPricer("$"));
        cart.addItem("crisp", 1);
        cart.addItem("banana", 1);
        cart.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        cart.printReceipt();

        String[] result = dataLines(myOut);

        assertEquals(String.format("crisp - 1 - $0.00"), result[0]);
        assertEquals(String.format("banana - 1 - $2.00"), result[1]);
        assertEquals(String.format("apple - 1 - $1.00"), result[2]);
        assertEquals(String.format("Total - $3.00"), result[3]);
    }

    private String[] dataLines(final ByteArrayOutputStream outputStream) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new StringReader(outputStream.toString()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            lines.add(line);
        }
        return lines.toArray(new String[]{});
    }
}


