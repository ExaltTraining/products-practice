package com.app.cart.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.cart.Cart;
import com.app.helper.AppHelper;
import com.app.products.Product;
import com.app.products.ProductsHelper;

public class CartHelper {

    public static final String BUTTON_CART = "//div[@class='shopping_cart')]/a";
    public static final String LAYER_PRODUCT_ADDED = "//div[@id='layer_cart']";
    public static final String CART_PRODUCT_NAME = "//div[@class='product-name']";
    public static final String CART_PRODUCTS_LIST = "//table[@id='cart_summary']/tbody/tr";
    public static final String TOTAL_PRODUCTS = "//td[@id='total_product']";
    public static final String TOTAL_SHIPPING = "//td[@id='total_shipping']";
    public static final String TOTAL_WITHOUT_TAX = "//td[@id='total_price_without_tax']";
    public static final String TOTAL_TAX = "//td[@id='total_tax']";
    public static final String TOTAL = "//span[@id='total_price']";

    public static Product addToCart(WebDriver dvr, int productIndex) {
        WebElement product = ProductsHelper.getProduct(dvr, productIndex);
        Product p = new Product(ProductsHelper.getProductName(dvr, product),
                ProductsHelper.getProductPrice(dvr, product));
        ProductsHelper.productAddToCartButton(dvr, product).click();
        return p;
    }

    public static void goToCart(WebDriver dvr) {
        AppHelper.click(dvr, BUTTON_CART);
    }

    public static WebElement productsList(WebDriver dvr) {
        return AppHelper.findElement(dvr, CART_PRODUCTS_LIST);
    }

    public static WebElement getProduct(WebDriver dvr, int index) {
        return productsList(dvr).findElement(By.xpath("[" + index + "]"));
    }

    public static String getProductName(WebDriver dvr, WebElement product) {
        return product.findElement(By.xpath("./td[@class='cart_description']/p/a")).getText();
    }

    public static String getProductUnitPrice(WebDriver dvr, WebElement product) {
        return product.findElement(By.xpath("./td[@class='cart_unit']/span/span")).getText();
    }

    public static String getProductTotalPrice(WebDriver dvr, WebElement product) {
        return product.findElement(By.xpath("./td[@class='cart_total']/span")).getText();
    }

    public static String getProductQuantity(WebDriver dvr, WebElement product) {
        return product.findElement(By.xpath("./td[contains(@class, 'cart_quantity')]/input[1]")).getAttribute("value");// getCssValue("value")
    }

    public static boolean checkSummation(WebDriver dvr, Cart cart) {

        AppHelper.findElements(dvr, CART_PRODUCTS_LIST).forEach(p -> {

        });
        return false;
    }
}
