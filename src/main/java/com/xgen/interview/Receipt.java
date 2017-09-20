package com.xgen.interview;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

/**
 * Holds a order information, a order consists in a group of product + quantity, and the total of product prices.
 */
public class Receipt {

  private final int total;
  private final List<Item> items;

  public Receipt(final List<Item> items) {
    this.items = items != null ? items : emptyList();
    this.total = this.items.stream().map(Item::getTotal).reduce((p1, p2) -> p1 + p2).orElse(0);
  }

  public int getTotal() {
    return total;
  }

  public List<Item> getProducts() {
    return items;
  }


  public static class Item {

    final Product product;
    final int quantity;

    public Item(Product product, int quantity) {
      Objects.requireNonNull(product, "product cannot be null");
      this.product = product;
      this.quantity = quantity;
    }

    public String getName() {
      return product.getName();
    }

    public int getTotal() {
      return product.getPrice() * quantity;
    }

    public int getQuantity() {
      return this.quantity;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Item item = (Item) o;
      return quantity == item.quantity && product.equals(item.product);
    }

    @Override
    public int hashCode() {
      int result = product.hashCode();
      result = 31 * result + quantity;
      return result;
    }
  }

}
