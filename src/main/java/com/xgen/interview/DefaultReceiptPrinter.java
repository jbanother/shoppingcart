package com.xgen.interview;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Default printer that will output to a PrintStream.
 */
public class DefaultReceiptPrinter implements ReceiptPrinter {

  private final PrintStream printStream;

  public DefaultReceiptPrinter() {
    this(System.out);
  }

  public DefaultReceiptPrinter(OutputStream out) {
    this(new PrintStream(out));
  }

  public DefaultReceiptPrinter(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void printReceipt(Receipt receipt) {
    receipt.getProducts().forEach(this::printItem);
    printTotal(receipt.getTotal());
  }

  private void printItem(Receipt.Item item) {
    printStream.println(String.format("%s  - %d - €%.2f", item.getName(), item.getQuantity(), centsToEuro(item.getTotal())));
  }

  private void printTotal(int total) {
    printStream.println(String.format("\nTotal €%.2f", centsToEuro(total)));
  }

  private double centsToEuro(int value) {
    return value / 100.0;
  }

}
