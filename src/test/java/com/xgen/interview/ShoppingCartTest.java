package com.xgen.interview;

import com.xgen.interview.mock.MockReceiptPrinter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ShoppingCartTest {

  private ShoppingCart shoppingCart;
  private MockReceiptPrinter mockReceiptPrinter;
  private Pricer pricer;

  @Before
  public void before() {
    pricer = new Pricer();
    mockReceiptPrinter = new MockReceiptPrinter();
    shoppingCart = new ShoppingCart(pricer, mockReceiptPrinter);
  }

  @Test
  public void canAddAnItem() {

    shoppingCart.addItem("apple", 1);

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myOut));

    shoppingCart.printReceipt();
    assertNotNull(mockReceiptPrinter.items);
    assertEquals(1, mockReceiptPrinter.items.size());
    assertEquals(item("apple", 1), mockReceiptPrinter.items.get(0));
    assertEquals(pricer.getPrice("apple"), (Integer) mockReceiptPrinter.total);
  }

  @Test
  public void canAddMoreThanOneItem() {

    shoppingCart.addItem("apple", 2);

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myOut));

    shoppingCart.printReceipt();
    assertNotNull(mockReceiptPrinter.items);
    assertEquals(1, mockReceiptPrinter.items.size());
    assertEquals(item("apple", 2), mockReceiptPrinter.items.get(0));
    assertEquals(pricer.getPrice("apple") * 2, mockReceiptPrinter.total);
  }

  @Test
  public void canAddDifferentItemsAndKeepOrder() {

    shoppingCart.addItem("apple", 2);
    shoppingCart.addItem("banana", 1);

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myOut));

    shoppingCart.printReceipt();
    assertNotNull(mockReceiptPrinter.items);
    assertEquals(2, mockReceiptPrinter.items.size());
    assertEquals(item("apple", 2), mockReceiptPrinter.items.get(0));
    assertEquals(item("banana", 1), mockReceiptPrinter.items.get(1));
    assertEquals((pricer.getPrice("apple") * 2) + pricer.getPrice("banana"), mockReceiptPrinter.total);
  }

  @Test
  public void doesntExplodeOnMysteryItem() {

    shoppingCart.addItem("crisps", 1);

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(myOut));

    shoppingCart.printReceipt();
    assertNotNull(mockReceiptPrinter.items);
    assertEquals(1, mockReceiptPrinter.items.size());
    assertEquals(item("crisps", 1), mockReceiptPrinter.items.get(0));
    assertEquals(0, mockReceiptPrinter.total);
  }


  private Receipt.Item item(String name, int quantity) {
    return new Receipt.Item(new Product(name, pricer.getPrice(name)), quantity);
  }

}


