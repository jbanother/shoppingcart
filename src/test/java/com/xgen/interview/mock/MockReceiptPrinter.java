package com.xgen.interview.mock;

import com.xgen.interview.Receipt;
import com.xgen.interview.ReceiptPrinter;

import java.util.ArrayList;
import java.util.List;

public class MockReceiptPrinter implements ReceiptPrinter {

  public List<Receipt.Item> items;
  public int total;

  @Override
  public void printReceipt(Receipt receipt) {
    this.items = new ArrayList<>(receipt.getProducts());
    this.total = receipt.getTotal();
  }

}
