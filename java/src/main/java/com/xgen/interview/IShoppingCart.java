package com.xgen.interview;

/**
 * This is the interface used by the existing shopping tills in our supermarket
 * It cannot be changed without upgrading/replacing legacy hardware.
 */
interface IShoppingCart {

  /**
   * Called once for every item/collection of items that is scanned by the till.
   * @param itemType - The item being scanned by the hardware
   * @param number - The number of items the cashier is moving to the bagging area
   */
  void addItem(String itemType, int number);

  /**
   * Prints the receipt to stdout. In the supermarket, stdout is captured by the receipt printer, which then prints
   * it for tue customer
   */
  void printReceipt();
}
