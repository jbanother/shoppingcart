# ShoppingCart

This is a partial implementation of the software behind a supermarket till system. It is intended for use as a take-home project by candidates. The existing implementation is not intended to be high-quality code.

The goal of the software is to record items as they're swiped through a supermarket till, and then to print a receipt for the supermarket customer (multiple ShoppingCart.addItem calls, then a call to ShoppingCart.printReceipt). Candidates should reimplement ShoppingCart, but must adhere to the existing interface, IShoppingCart. Please make any decisions you feel appropriate in the absence of sufficient information.

Please note; there are no intentional tricks/traps in this project. Please work-around any bugs you discover & note them for the discussion during the onsite.

## Dependencies
  gradle (https://gradle.org/install)

## Build
  gradle build

## Test
  gradle test
