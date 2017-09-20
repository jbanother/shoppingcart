package com.xgen.interview;

import java.util.Objects;

/**
 * A product represented by name and price.
 */
public class Product {

  private final String name;
  private final int price;

  public Product(String name, int price) {
    Objects.requireNonNull(name, "product name cannot be null");
    this.name = name;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return price == product.price && name.equals(product.name);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + price;
    return result;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }
}
