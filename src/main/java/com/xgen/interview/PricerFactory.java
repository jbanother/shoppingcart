package com.xgen.interview;

public class PricerFactory {
    public IPricer getPricer() {
        IPricer pricer = new Pricer();
        pricer.initialize();
        return pricer;
    }
}
