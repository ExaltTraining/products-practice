package com.app.cart;

import com.app.products.Product;

public class CartItem {

    private Product product;
    private int quantity = 1;

    public CartItem(String name, double price, int quantity) {
        this(new Product(name, price), quantity);
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        CartItem other = (CartItem) obj;
        if (other.getQuantity() != quantity)
            return false;
        if (!this.getProduct().equals(other.getProduct()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CartItem [product=" + product + ", quantity=" + quantity + "]";
    }

}
