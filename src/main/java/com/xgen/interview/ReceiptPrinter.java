package com.xgen.interview;

/**
 * Holds a contract for printing a Receipt instance, implementation may or not decide
 * to apply custom format to output, and which output to use.
 */
public interface ReceiptPrinter {

  /**
   * Print the given receipt.
   *
   * @param receipt the receipt instance.
   */
  void printReceipt(Receipt receipt);

}
