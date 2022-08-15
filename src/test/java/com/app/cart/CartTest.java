package com.app.cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.cart.helper.CartHelper;
import com.app.helper.AppHelper;
import com.app.products.helper.ProductsHelper;

public class CartTest {

    private WebDriver dvr;

    @BeforeTest
    public void setup() {
        AppHelper.print("<<------ Setting up ------>>");
        System.setProperty(AppHelper.CHROME_DRIVER_KEY, AppHelper.CHROME_DRIVER_PATH_103);
        dvr = new ChromeDriver();
        dvr.get(AppHelper.WEBSITE_WOMEN_PRODUCTS_URL);
        AppHelper.print("<<------ Completed ------>>");
    }

    @BeforeMethod
    public void updatesBefore() {
        AppHelper.print("--------------------------");
        AppHelper.log("Getting ready for the test");
        AppHelper.log("READY :)");
        AppHelper.print("--------------------------");
    }

    @AfterMethod
    public void updatesAfter() {
        AppHelper.print("--------------------------");
        AppHelper.log("Cleaning up after test");

        AppHelper.log("DONE :)");
        AppHelper.print("--------------------------");
    }

    @Test
    public void validateAddingProductsToCartSummationTest() {
        AppHelper.run(new CartTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_LIST_VIEW);

        CartHelper.addToCart(dvr, 1);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.click(dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        CartHelper.addToCart(dvr, 1);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.click(dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        CartHelper.addToCart(dvr, 1);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.click(dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        CartHelper.addToCart(dvr, 2);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.click(dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        CartHelper.addToCart(dvr, 3);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_PRODUCT_ADDED);
        AppHelper.waitUntilVisibilityOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilPresenceOf(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.waitUntilToBeClickable(15, dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);
        AppHelper.click(dvr, CartHelper.LAYER_BUTTON_PRODUCT_ADDED_CONTINUE);

        WebElement product1 = ProductsHelper.getProduct(dvr, 1);
        WebElement product2 = ProductsHelper.getProduct(dvr, 2);
        WebElement product3 = ProductsHelper.getProduct(dvr, 3);

        Cart cart = new Cart();
        cart.addItem(new CartItem(ProductsHelper.getProductName(dvr, product1),
                ProductsHelper.getProductPrice(dvr, product1), 3));
        cart.addItem(new CartItem(ProductsHelper.getProductName(dvr, product2),
                ProductsHelper.getProductPrice(dvr, product2), 1));
        cart.addItem(new CartItem(ProductsHelper.getProductName(dvr, product3),
                ProductsHelper.getProductPrice(dvr, product3), 1));

        CartHelper.goToCart(dvr);

        Assert.assertTrue(CartHelper.checkSummation(dvr, cart), "Summation failed!");

        AppHelper.finish(new CartTest() {
        }.getClass().getEnclosingMethod());
    }

    @AfterTest
    public void teardown() throws InterruptedException {
        long time = 5000;
        AppHelper.log("----- Teardown, quiting after " + (time / 1000) + "secs!");
        Thread.sleep(time);
        // dvr.quit();
    }
}
