package com.xgen.interview;

import org.junit.Test;

import java.util.*;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


public class PricerTest {

    @Test
    public void testAddNewItem() {
        Pricer pricer = new Pricer();
        pricer.addOrUpdatePrice("new_item", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("new_item").intValue(), 2000);
    }

    @Test
    public void testUpdateExistingItem() {
        Pricer pricer = new Pricer();
        pricer.addOrUpdatePrice("item", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("item").intValue(), 2000);

        pricer.addOrUpdatePrice("item", 10);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("item").intValue(), 1000);
    }

    @Test
    public void testGetPriceExistingItem() {
        Pricer pricer = new Pricer();
        pricer.addOrUpdatePrice("item", 20);
        assertEquals(pricer.getPrice("item").intValue(), 2000);
    }

    @Test
    public void testGetPriceUnavailableItem() {
        Pricer pricer = new Pricer();
        assertEquals(pricer.getPrice("item").intValue(), 0);
    }

    @Test
    public void testRemoveExistingItem() {
        Pricer pricer = new Pricer();
        pricer.addOrUpdatePrice("apple", 20);
        assertEquals(pricer.getItems().size(), 1);
        assertEquals(pricer.getPrice("apple").intValue(), 2000);

        pricer.removeItem("apple");
        assertEquals(pricer.getItems().size(), 0);
        assertEquals(pricer.getPrice("apple").intValue(), 0);
    }

    @Test
    public void testRemoveUnavailableItem() {
        Pricer pricer = new Pricer();
        pricer.initialize();
        assertEquals(pricer.getItems().size(), 2);

        pricer.removeItem("non_item");
        assertEquals(pricer.getItems().size(), 2);
    }

    @Test
    public void testGetItems() {
        Pricer pricer = new Pricer();
        pricer.initialize();
        Map<String, Integer> items = pricer.getItems();
        assertEquals(items.size(), 2);

        Object[] itemKeys = items.keySet().toArray();
        Arrays.sort(itemKeys);
        assertArrayEquals("should return all items", itemKeys, new Object[]{"apple", "banana"});
    }

    @Test
    public void testGetItemsOnEmptyPricer() {
        Pricer pricer = new Pricer();
        assertEquals(pricer.getItems().size(), 0);
    }

}
