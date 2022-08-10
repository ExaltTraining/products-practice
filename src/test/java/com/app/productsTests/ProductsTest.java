package com.app.productsTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.AppHelper;
import com.app.productsHelper.CatalogHelper;
import com.app.productsHelper.ProductsHelper;

public class ProductsTest {

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
    public void validateViewTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_LIST_VIEW);
        Assert.assertTrue(ProductsHelper.getViewType(dvr).equalsIgnoreCase("list"),
                "View didn't change.");

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test
    public void validateSortingTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        ProductsHelper.selectSortType(dvr, ProductsHelper.SELECT_SORT_ITEM_PRICE_ASC);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);
        Assert.assertTrue(ProductsHelper.isProductsSortedAsPriceASC(dvr), "List was not sorted as desired.");

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test
    public void validateFilteringTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        AppHelper.click(dvr, CatalogHelper.CHECK_COTTON_COMPOSITIONS);
        AppHelper.click(dvr, CatalogHelper.CHECK_NEW_CONDITION);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);

        List<WebElement> products = ProductsHelper.getProducts(dvr);
        List<String> expected = Arrays.asList("Faded Short Sleeve T-shirts", "Printed Dress", "Blouse");
        List<String> actual = new ArrayList<>();
        for (WebElement p : products) {
            actual.add(ProductsHelper.getProductName(dvr, p));
        }
        Assert.assertTrue(actual.containsAll(expected), "List was not sorted as desired.");

        AppHelper.finish(new ProductsTest() {
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
