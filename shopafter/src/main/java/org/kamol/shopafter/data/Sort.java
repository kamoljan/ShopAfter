package org.kamol.shopafter.data;

public enum Sort {
  PRICE("price"),
  DATE("date");
  public final String value;

  Sort(String value) {
    this.value = value;
  }

  @Override public String toString() {
    return value;
  }
}
