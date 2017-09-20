package com.xgen.interview;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class DefaultReceiptPrinterTest {

  private DefaultReceiptPrinter receiptPrinter;
  private ByteArrayOutputStream outputStream;

  @Before
  public void before() {
    outputStream = new ByteArrayOutputStream();
    receiptPrinter = new DefaultReceiptPrinter(outputStream);
  }

  @Test
  public void shouldPrintSingleItemReceipt() {
    receiptPrinter.printReceipt(new Receipt(singletonList(new Receipt.Item(new Product("banana", 300), 2))));
    String result = "" +
        "banana  - 2 - €6.00\n" +
        "\n" +
        "Total €6.00" +
        "\n";
    assertEquals(result, outputStream.toString());
  }

  @Test
  public void shouldPrintOrderedReceipt() {
    List<Receipt.Item> products = asList(
        new Receipt.Item(new Product("banana", 100),1),
        new Receipt.Item(new Product("apple", 99),1),
        new Receipt.Item(new Product("crisps", 80),2)
    );
    receiptPrinter.printReceipt(new Receipt(products));
    String result = "" +
        "banana  - 1 - €1.00\n" +
        "apple  - 1 - €0.99\n" +
        "crisps  - 2 - €1.60\n" +
        "\n" +
        "Total €3.59" +
        "\n";
    assertEquals(result, outputStream.toString());
  }

}