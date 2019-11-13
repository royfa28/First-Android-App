package com.example.game4sell.Model;

public class Order {

    String ProductName;
    Integer Quantity;
    Double Price;

    public Order(String productName, Integer quantity, Double price) {
        ProductName = productName;
        Quantity = quantity;
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
