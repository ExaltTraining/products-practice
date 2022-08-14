package com.app.cart;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> products;
    private int counter = 0;
    private double totalProductsPrice = 0;
    private double totalShipping = 2;
    private double totalPriceWithOutTax = 0;
    private double totalTax = 0;
    private double total = 0;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        products.add(item);
        counter++;
        totalProductsPrice += item.getQuantity() * item.getProduct().getPrice();
        totalPriceWithOutTax = totalProductsPrice + totalShipping;
        total = totalPriceWithOutTax + totalTax;
    }

    public void removeItem(CartItem item) {
        products.remove(item);
    }

    public void removeItem(int index) {
        products.remove(index);
    }

    public ArrayList<CartItem> getProducts() {
        return products;
    }

    public int getCounter() {
        return counter;
    }

    public double getTotalProductsPrice() {
        return totalProductsPrice;
    }

    public double getTotalShipping() {
        return totalShipping;
    }

    public double getTotalPriceWithOutTax() {
        return totalPriceWithOutTax;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public double getTotal() {
        return total;
    }

}
