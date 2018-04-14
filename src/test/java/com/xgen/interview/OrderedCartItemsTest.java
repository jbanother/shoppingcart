package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.OrderedCartItems;

import java.util.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OrderedCartItemsTest {

    @Test
    public void canAddAnItem() {
        OrderedCartItems cartItems = new OrderedCartItems(new Pricer());

        cartItems.Add("apple", 1);

        assertEquals(1, cartItems.Items().size());
    }

    @Test
    public void canAddMoreThanOneItem() {
        OrderedCartItems cartItems = new OrderedCartItems(new Pricer());

        cartItems.Add("apple", 1);
        cartItems.Add("pear", 1);

        assertEquals(2, cartItems.Items().size());
    }

    @Test
    public void threeItemsInCorrectOrder() {
        OrderedCartItems cartItems = new OrderedCartItems(new Pricer());

        cartItems.Add("apple", 1);
        cartItems.Add("pear", 1);
        cartItems.Add("apple", 2);

        assertEquals("apple", cartItems.Items().get(0).getItemType());
        assertEquals(1, cartItems.Items().get(0).getQuantity());
        assertEquals("pear", cartItems.Items().get(1).getItemType());
        assertEquals(1, cartItems.Items().get(1).getQuantity());
        assertEquals("apple", cartItems.Items().get(2).getItemType());
        assertEquals(2, cartItems.Items().get(2).getQuantity());
    }

    @Test
    public void correctTotalComputed() {
        OrderedCartItems cartItems = new OrderedCartItems(new Pricer());

        cartItems.Add("apple", 1);
        cartItems.Add("pear", 1);
        cartItems.Add("banana", 3);

        assertEquals(700, cartItems.GetTotal());
    }

    @Test
    public void totalReturnedIfNoItems() {
        OrderedCartItems cartItems = new OrderedCartItems(new Pricer());

        assertEquals(0, cartItems.GetTotal());
    }

}

