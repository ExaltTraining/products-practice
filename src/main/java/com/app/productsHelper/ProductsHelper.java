package com.app.productsHelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.AppHelper;

public class ProductsHelper {

    public static final String PRODUCTS_LIST = "//ul[contains(@class, 'product_list')]";
    public static final String CENTER_COLUMN = "//div[@id='center_column']";
    public static final String SELECT_SORT_MENU = "//select[@id='selectProductSort']";
    // There are more sorting types, but I want to test this one ONLY.
    public static final String SELECT_SORT_ITEM_PRICE_ASC = "price:asc";
    public static final String SELECT_SORT_ITEM_NAME_DESC = "name:asc";
    public static final int PRODUCTS_VIEW_LIST = 0;
    public static final int PRODUCTS_VIEW_GRID = 1;
    public static final String PRODUCTS_LIST_VIEW = "//li[@id='list']/a";
    public static final String PRODUCTS_GRID_VIEW = "//li[@id='grid']/a";
    public static final String PRODUCTS_LIST_LOADING = PRODUCTS_LIST + "/p";

    public static WebElement getProduct(WebDriver dvr, int index) {
        return AppHelper.findElement(dvr, PRODUCTS_LIST + "/li[" + index + "]/div");
    }

    public static List<WebElement> getProducts(WebDriver dvr) {
        return AppHelper.findElements(dvr, PRODUCTS_LIST + "/li/div");
    }

    /**
     * Find a product by its name
     * 
     * @param dvr  webDriver
     * @param name of the product
     * @return if the product is found it returns its name, otherwise returns null
     */
    public static String findProduct(WebDriver dvr, String name) {
        try {
            WebElement rs = AppHelper.findElement(dvr,
                    PRODUCTS_LIST + "/li/div//div[2]//child::a[@class='product-name' and contains(text(), '" + name
                            + "')]");
            return rs.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getProductName(WebDriver dvr, WebElement product) {
        try {
            return product.findElement(By.xpath(".//div[2]//child::a[@class='product-name']")).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static double getProductPrice(WebDriver dvr, WebElement product) {
        return Double.parseDouble(
                product.findElement(By.xpath("./div[2]//child::span[contains(@class, 'price')]")).getText().replace("$",
                        ""));
    }

    public static boolean isProductAvailable(WebDriver dvr, WebElement product) {
        return "In stock"
                .equals(product.findElement(By.xpath(".//div[2]//child::span[@class='available-now']")).getText());
    }

    public static WebElement productAddToCartButton(WebDriver dvr, WebElement product) {
        try {
            return product.findElement(By.xpath(".//child::a[contains(@class, 'ajax_add_to_cart_button')]"));
        } catch (Exception e) {
            return null;
        }
    }

    public static WebElement productAddToCompareButton(WebDriver dvr, WebElement product) {
        try {
            return product.findElement(By.xpath(".//child::a[contains(@class, 'add_to_compare')]"));
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isProductListEmpty(WebDriver dvr) {
        return AppHelper.findElement(dvr, CENTER_COLUMN).findElements(By.xpath(".//div[@class='product_list']"))
                .size() == 1;
    }

    public static String getViewType(WebDriver dvr) {
        String type = AppHelper.findElement(dvr, PRODUCTS_LIST).getAttribute("class");
        type = type.replace("row", "");
        type = type.replace("product_list", "");
        return type.trim();
    }

    public static void setViewType(WebDriver dvr, String type) {
        AppHelper.findElement(dvr, type).click();
    }

    public static void selectSortType(WebDriver dvr, String value) {
        AppHelper.selectByValueFrom(dvr, value, AppHelper.findElement(dvr, SELECT_SORT_MENU));
    }

    public static boolean isProductsSortedAsPriceASC(WebDriver dvr) {
        double comp = Double.MIN_VALUE;
        boolean assertion = true;
        List<WebElement> products = ProductsHelper.getProducts(dvr);
        for (WebElement p : products) {
            double temp = ProductsHelper.getProductPrice(dvr, p);
            if (comp > temp) {
                assertion = false;
                break;
            }
            comp = temp;
        }
        return assertion;
    }

    public static boolean isProductsSortedAsNameDESC(WebDriver dvr) {
        String comp = "a";
        boolean assertion = true;
        List<WebElement> products = ProductsHelper.getProducts(dvr);
        for (WebElement p : products) {
            String temp = ProductsHelper.getProductName(dvr, p);
            System.out.println(temp);
            if (comp.compareToIgnoreCase(temp) > 0) {
                assertion = false;
                break;
            }
            comp = temp;
        }
        return assertion;
    }

}
