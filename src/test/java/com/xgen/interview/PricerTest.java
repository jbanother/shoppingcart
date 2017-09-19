package com.xgen.interview;

import com.xgen.interview.Pricer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class PricerTest {

    @Test
    public void getValidItem() {
        Pricer pricer = new Pricer();
        
        assertEquals((Integer)100, pricer.getPrice("apple"));
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        Pricer pricer = new Pricer();
        
        assertEquals((Integer)0, pricer.getPrice("item_not_found"));
    }
}