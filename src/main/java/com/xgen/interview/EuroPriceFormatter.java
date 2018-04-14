package com.xgen.interview;

public class EuroPriceFormatter implements IPriceFormatter {
    public String FormattedPrice(int price) {
        Float priceFloat;
        if (price > 0) {
            priceFloat = (float) (price) / 100;
        }
        else {
            priceFloat = 0.0f;
        }
        return String.format("€%.2f", priceFloat);
    }

}
