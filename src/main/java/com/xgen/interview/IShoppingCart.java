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

  /**
   * Return how many items we have in our shopping cart
   */
  int countItems();

  /**
   * Check if an item is in our shopping cart by returning how many there are,
   * or 0 if none
   */
  int hasItem(String itemType);

  /**
   * Return the total cost of the items in the shopping cart
   */
  float getTotalCost();
}
