from abc import ABC

class IShoppingCart(ABC):
    """
    Interface used by the existing shopping tills in our supermarket.
    Cannot be changed without upgrading/replacing legacy hardware.
    """

    def add_item(self, item_type: str, number: int):
        # Called once for every item/collection of items that is scanned by the till.
        # itemType - The item being scanned by the hardware
        # number - The number of items the cashier is moving to the bagging area
        pass

    def print_receipt(self):
        # Prints the receipt to stdout. In the supermarket, stdout is captured
        # by the receipt printer, which then prints
        # it for the customer
        pass
