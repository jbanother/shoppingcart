package com.xgen.interview;

/**
 * Factory class to create Pricer
 */
public class PricerFactory {
    /**
     * returns a concrete instance of IPricer
     * @param currency currency value of pricer
     * @return concrete instance of IPricer
     */
    public static IPricer getPricer(String... currency) {
        String theCurrency = null;
        if (currency.length == 0) {
            theCurrency = "€"; // default currency is euro
        } else {
            theCurrency = currency[0];
        }
        IPricer pricer = new Pricer(theCurrency);
        pricer.initialize(); // initializes pricer with some items
        return pricer;
    }
}
