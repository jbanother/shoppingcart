package com.xgen.interview;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricerFactoryTest {
    @Test
    public void testGetPricerWithDefaultCurrency() {
        IPricer pricer = PricerFactory.getPricer();
        assertNotNull(pricer);
        assertEquals(pricer.getCurrency(), "€");
    }

    @Test
    public void testGetPricerWithCustomCurrency() {
        IPricer pricer = PricerFactory.getPricer("$");
        assertNotNull(pricer);
        assertEquals(pricer.getCurrency(), "$");
    }

    @Test
    public void testInitializedPricer() {
        IPricer pricer = PricerFactory.getPricer();
        assertNotNull(pricer);
        assertEquals(pricer.getItems().size(), 2);
    }
}
