package com.app.products;

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

import com.app.helper.AppHelper;
import com.app.products.helper.CatalogHelper;

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

    @Test(priority = 0)
    public void validateSortingNameDESCTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.selectSortType(dvr, ProductsHelper.SELECT_SORT_ITEM_NAME_DESC);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);
        Assert.assertTrue(ProductsHelper.isProductsSortedAsNameDESC(dvr), "List was not sorted as desired.");

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test(priority = 1)
    public void validateSortingPriceASCTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        ProductsHelper.selectSortType(dvr, ProductsHelper.SELECT_SORT_ITEM_PRICE_ASC);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);
        Assert.assertTrue(ProductsHelper.isProductsSortedAsPriceASC(dvr), "List was not sorted as desired.");

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test(priority = 7)
    public void validateFilteringTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        AppHelper.click(dvr, CatalogHelper.CHECK_COTTON_COMPOSITIONS);
        AppHelper.click(dvr, CatalogHelper.CHECK_NEW_CONDITION);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);

        Assert.assertTrue(
                CatalogHelper.isThisFilterEnabled(dvr,
                        CatalogHelper.getFilterName(dvr, CatalogHelper.CHECK_COTTON_COMPOSITIONS)),
                "This filter shall be enabled.");
        Assert.assertTrue(
                CatalogHelper.isThisFilterEnabled(dvr,
                        CatalogHelper.getFilterName(dvr, CatalogHelper.CHECK_NEW_CONDITION)),
                "This filter shall be enabled.");

        List<WebElement> products = ProductsHelper.getProducts(dvr);
        List<String> expected = Arrays.asList("Faded Short Sleeve T-shirts", "Printed Dress", "Blouse");
        List<String> actual = new ArrayList<>();
        for (WebElement p : products) {
            actual.add(ProductsHelper.getProductName(dvr, p));
        }
        Assert.assertTrue(actual.containsAll(expected), "List was not sorted as desired.");

        AppHelper.click(dvr, CatalogHelper.CHECK_COTTON_COMPOSITIONS);
        AppHelper.click(dvr, CatalogHelper.CHECK_NEW_CONDITION);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test(priority = 8)
    public void validateViewTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_LIST_VIEW);

        Assert.assertTrue(ProductsHelper.productAddToCartButton(dvr, ProductsHelper.getProduct(dvr, 1)) != null,
                "Element(Add to cart button) is not showing");
        Assert.assertTrue(ProductsHelper.productAddToCompareButton(dvr, ProductsHelper.getProduct(dvr, 1)) != null,
                "Element(Add to compare button) is not showing");
        Assert.assertTrue(ProductsHelper.getProductName(dvr, ProductsHelper.getProduct(dvr, 1)) != null,
                "Element(Name) is not showing");
        Assert.assertTrue(ProductsHelper.getViewType(dvr).equalsIgnoreCase("list"),
                "View didn't change.");

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test(priority = 9)
    public void validateOtherFiltersTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        AppHelper.click(dvr, CatalogHelper.CHECK_YELLOW_COLOR);
        CatalogHelper.setMaxPrice(dvr, 30);
        AppHelper.waitUntilNotPresenceOf(25, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);

        List<WebElement> products = ProductsHelper.getProducts(dvr);
        List<String> productsNameExpected = Arrays.asList("Printed Summer Dress", "Printed Chiffon Dress");
        List<String> productsNameActual = new ArrayList<>();
        for (WebElement p : products) {
            productsNameActual.add(ProductsHelper.getProductName(dvr, p));
        }
        Assert.assertTrue(productsNameExpected.containsAll(productsNameActual), "List shall has porducts.");
        Assert.assertTrue(products.size() == 2, "List shall has porducts.");

        AppHelper.finish(new ProductsTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test(priority = 10)
    public void validateInStockTest() {
        AppHelper.run(new ProductsTest() {
        }.getClass().getEnclosingMethod());

        ProductsHelper.setViewType(dvr, ProductsHelper.PRODUCTS_GRID_VIEW);
        AppHelper.click(dvr, CatalogHelper.CHECK_IN_STOCK_AVAILABILITY);
        AppHelper.waitUntilNotPresenceOf(15, dvr, ProductsHelper.PRODUCTS_LIST_LOADING);

        Assert.assertTrue(ProductsHelper.getProducts(dvr).size() == 7, "List shall has porducts.");

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
