
class Pricer:
    """
    a datastore for the available products in the supermarket
    """
    def __init__(self):
        self.__pricing_database = {"apple": 100, "banana": 200}

    def get_price(self, itemtype: str):
        # Returns the price of the item passed, in Euro-cent. Eg. if an item costs €1, this will return 100
        # If itemType is an unknown string, store policy is that the item is free.
        if itemtype not in self.__pricing_database:
            return 0
        return self.__pricing_database[itemtype]


