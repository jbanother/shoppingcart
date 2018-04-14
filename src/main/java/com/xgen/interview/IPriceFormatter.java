package com.xgen.interview;

/**
 * This an the interface used to format a price .
 */
public interface IPriceFormatter {
    /**
     * Called to format a price
     * @param price - The price to be formatted
     */
    String FormattedPrice(int price);
}
