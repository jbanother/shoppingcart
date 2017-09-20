package com.xgen.interview;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.Assert.assertEquals;

public class ReceiptTest {

  @Test
  public void initializeForEmptyList() {
    Receipt receipt = new Receipt(emptyList());
    assertEquals(0, receipt.getTotal());
  }

  @Test
  public void shouldSumListOfProducts() {
    Receipt receipt = new Receipt(asList(
        new Receipt.Item(new Product("a", 300), 1),
        new Receipt.Item(new Product("a", 300), 2)
    ));
    assertEquals(900, receipt.getTotal());
  }

  @Test
  public void shouldAccept0AsPrice() {
    Receipt receipt = new Receipt(asList(
        new Receipt.Item(new Product("a", 0), 1),
        new Receipt.Item(new Product("a", 300), 2)
    ));
    assertEquals(600, receipt.getTotal());
  }

}