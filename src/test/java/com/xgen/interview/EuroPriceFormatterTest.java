package com.xgen.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EuroPriceFormatterTest {
    @Test
    public void priceFormattedCorrectlyEvenEuro() {
        EuroPriceFormatter priceFormatter = new EuroPriceFormatter();

        assertEquals("€7.00", priceFormatter.FormattedPrice(700));
    }

    @Test
    public void priceFormattedCorrectlyWithEuroAndCent() {
        EuroPriceFormatter priceFormatter = new EuroPriceFormatter();

        assertEquals("€7.75", priceFormatter.FormattedPrice(775));
    }

    @Test
    public void priceFormattedCorrectlyWithCent() {
        EuroPriceFormatter priceFormatter = new EuroPriceFormatter();

        assertEquals("€0.04", priceFormatter.FormattedPrice(4));
    }

    @Test
    public void priceFormatterDoesntFailWithNegativePrice() {
        EuroPriceFormatter priceFormatter = new EuroPriceFormatter();

        assertEquals("€0.00", priceFormatter.FormattedPrice(-4));
    }
}
