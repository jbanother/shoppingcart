# ShoppingCart

This is a partial implementation of the software behind a supermarket till system.
It is intended for use as a take-home project by candidates. We don't consider the existing implementations high-quality code.

The goal of the software is to record items as they're swiped through a supermarket till, and then to print a receipt for the supermarket customer. The normal workflow is for there to be multiple IShoppingCart.addItem calls, then a single call to IShoppingCart.printReceipt.
You may make any technical decisions you would like, but must not change IShoppingCart, as is used by the shopping till hardware which cannot be easily updated.
Please treat this code as an element of a much larger production system which is being refactored for reliability and testability. Make any decisions you feel appropriate in the absence of sufficient information.

There are implementations in both Java and Python. You may build on top of one of these, or implement IShoppingCart in another language of your choice.

Please note; there are no intentional tricks/traps in this project. Please work-around any bugs you discover & note them code comments or a Notes.md file.

Please do not spend more than 3 hours on this project - leave TODO comments or a Notes.md file if you run out of time.

## Your Tasks
- Add a 'Total' line to the receipt. This should be the full price we should charge the customer.
- Make the receipt print items in the order that they were scanned
- In some branches of the store, customers want the receipt to show the price first on each line. Without changing the IShoppingCart interface, add a way to support this which allows for other formatting options in the future.
- One limitation of the codebase is that every time a change is made, many of the tests need updating. Update or replace the test suite to extend coverage and limit the number of tests which need updating when changes are introduced.
- Improve the solution & tests. There are no rules/limitations other than conforming to the IShoppingCart interface - go crazy.


## Submitting Your Work
Send us a link to a git repo, a zip file with your changes or a .patch file.

## Java implementation

### Dependencies
  [JDK 11](https://adoptium.net/en-GB/temurin/releases?version=11)
  [gradle 7.5](https://gradle.org/next-steps/?version=7.5.1&format=bin)

### Build
```
cd java/
gradle build
```

### Test
```
cd java
gradle test
```

## Python implementation

### Dependencies
  python3.7+

### Run tests
```
cd python
python tests.py
```
