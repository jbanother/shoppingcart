package com.xgen.interview;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


public class PricerTest {
    private Pricer pricer;

    @Before
    public void initialize() {
        pricer = new Pricer("$");
    }

    @Test
    public void testAddNewItem() {
        pricer.addOrUpdatePrice("new_item", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("new_item").intValue(), 2000);
    }

    @Test
    public void testUpdateExistingItem() {
        pricer.addOrUpdatePrice("item", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("item").intValue(), 2000);

        pricer.addOrUpdatePrice("item", 10);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("item").intValue(), 1000);
    }

    @Test
    public void testGetPriceExistingItem() {
        pricer.addOrUpdatePrice("item", 20);
        assertEquals(pricer.getPrice("item").intValue(), 2000);
    }

    @Test
    public void testGetPriceUnavailableItem() {
        assertEquals(pricer.getPrice("item").intValue(), 0);
    }

    @Test
    public void testRemoveExistingItem() {
        pricer.addOrUpdatePrice("apple", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("apple").intValue(), 2000);

        pricer.removeItem("apple");
        assertEquals(pricer.getItems().size(), 0);
        assertEquals(pricer.getPrice("apple").intValue(), 0);
    }

    @Test
    public void testRemoveUnavailableItem() {
        pricer.initialize();
        assertEquals(pricer.getItems().size(), 2);

        pricer.removeItem("non_item");
        assertEquals(pricer.getItems().size(), 2);
    }

    @Test
    public void testGetItems() {
        pricer.initialize();
        Map<String, Float> items = pricer.getItems();
        assertEquals(items.size(), 2);

        Object[] itemKeys = items.keySet().toArray();
        Arrays.sort(itemKeys);
        assertArrayEquals("should return all items", itemKeys, new Object[]{"apple", "banana"});
    }

    @Test
    public void testGetItemsOnEmptyPricer() {
        assertEquals(pricer.getItems().size(), 0);
    }

    @Test
    public void testGetCurrency() {
        Pricer newPricer = new Pricer("€");
        assertEquals(newPricer.getCurrency(), "€");
    }
}
