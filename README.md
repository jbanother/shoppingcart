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

## Improvements made by Udita

### Assumptions made for improvements: 
- infinite number of any item can be added to cart
- item units are integer, no fraction values allowed (example: 1.2lb)
- we don't fail silently, runtime exceptions are thrown when action leads to invalid state

### List of improvements
- Added an Interface named IPricer to help build a more robust pricing store solution
- Updated Pricer to implement IPricer
    - moved initialization to it's own method instead of constructor
    - added capability to add new items
    - added capability to remove items
    - added checks for negative item price
    - added capability to set new currencies
- Added a slew of tests for Pricer
- Added a PricerFactory to separate out object building from initialization
- Added tests for PricerFactory
- Updated ShoppingCart
    - added a line for total cart price
    - cleaner version of printReceipt
    - better utilization of memory by using StringBuilder in printReceipt
    - added checks for invalid pricer
    - updated addItem method to check for negative quantity
- Added slew of tests for ShoppingCart to validate
  - items can be added randomly
  - item order would be preserved
  - total will be printed at the last line
  - receipt line items will update if pricer state updates
  - total cart price will update if pricer state updates
  - handles different currency well
  
## Improvements can be made

- Remove the assumption that infinite quantity of items are available
  - add an available quantity to pricer
  - on item add, reduce available quantity by item quantity
  - allow available quantity to be updated in pricer
  - raise exception if available quantity becomes negative
- Remove assumption that unit of items is integer
- A shopping cart client/UI component that can handle errors
- Allow multiple/batch item add
- Add time of purchase/receipt creation
- Add store information to ShoppingCart class
- Customize receipt format via a decorator 

