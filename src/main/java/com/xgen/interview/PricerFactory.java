package com.xgen.interview;

/**
 * Factory class to create Pricer
 */
public class PricerFactory {
    public static IPricer getPricer(String... currency) {
        String theCurrency = null;
        if (currency.length == 0) {
            theCurrency = "€";
        } else {
            theCurrency = currency[0];
        }
        IPricer pricer = new Pricer(theCurrency);
        pricer.initialize();
        return pricer;
    }
}
