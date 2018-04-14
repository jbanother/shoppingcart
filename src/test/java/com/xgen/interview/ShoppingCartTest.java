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
        //This is an integration test now using the EuroFormatter and the OrderedCartItems and the ReceiptWithTotal
        //implementations of the interfaces
        //I would normally use a mock library to mock returns for all of the interfaces as we have tested the individual
        //class with other unit tests
        IPriceFormatter priceFormatter = new EuroPriceFormatter();
        IPricer pricer = new Pricer();
        ICartItems cartItems = new OrderedCartItems(pricer);
        IReceipt receipt = new ReceiptWithTotal(pricer, priceFormatter);
        ShoppingCart sc = new ShoppingCart(cartItems, priceFormatter, receipt );

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%nTotal - €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        //This is an integration test now using the EuroFormatter and the OrderedCartItems and the ReceiptWithTotal
        //implementations of the interfaces
        //I would normally use a mock library to mock returns for all of the interfaces as we have tested the individual
        //class with other unit tests
        IPriceFormatter priceFormatter = new EuroPriceFormatter();
        IPricer pricer = new Pricer();
        ICartItems cartItems = new OrderedCartItems(pricer);
        IReceipt receipt = new ReceiptWithTotal(pricer, priceFormatter);
        ShoppingCart sc = new ShoppingCart(cartItems, priceFormatter, receipt );

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%nTotal - €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        //This is an integration test now using the EuroFormatter and the OrderedCartItems and the ReceiptWithTotal
        //implementations of the interfaces
        //I would normally use a mock library to mock returns for all of the interfaces as we have tested the individual
        //class with other unit tests
        IPriceFormatter priceFormatter = new EuroPriceFormatter();
        IPricer pricer = new Pricer();
        ICartItems cartItems = new OrderedCartItems(pricer);
        IReceipt receipt = new ReceiptWithTotal(pricer, priceFormatter);
        ShoppingCart sc = new ShoppingCart(cartItems, priceFormatter, receipt );

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();


        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%nTotal - €4.00%n"), result);
    }

        @Test
    public void doesntExplodeOnMysteryItem() {
            //This is an integration test now using the EuroFormatter and the OrderedCartItems and the ReceiptWithTotal
            //implementations of the interfaces
            //I would normally use a mock library to mock returns for all of the interfaces as we have tested the individual
            //class with other unit tests
            IPriceFormatter priceFormatter = new EuroPriceFormatter();
            IPricer pricer = new Pricer();
            ICartItems cartItems = new OrderedCartItems(pricer);
            IReceipt receipt = new ReceiptWithTotal(pricer, priceFormatter);
            ShoppingCart sc = new ShoppingCart(cartItems, priceFormatter, receipt );

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%nTotal - €0.00%n"), myOut.toString());
    }
}


