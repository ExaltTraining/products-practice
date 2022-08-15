package com.app.cart.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.cart.Cart;
import com.app.cart.CartItem;
import com.app.helper.AppHelper;
import com.app.products.Product;
import com.app.products.helper.ProductsHelper;

public class CartHelper {

    public static final String BUTTON_CART = "//div[@class='shopping_cart']/a";
    public static final String LAYER_PRODUCT_ADDED = "//div[@id='layer_cart']";
    public static final String LAYER_PRODUCT_ADDED_BUTTONS = LAYER_PRODUCT_ADDED + "//div[@class='button-container']";
    public static final String LAYER_BUTTON_PRODUCT_ADDED_CONTINUE = LAYER_PRODUCT_ADDED_BUTTONS + "/span/span";
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

    public static double getProductUnitPrice(WebDriver dvr, WebElement product) {
        return Double.parseDouble(
                product.findElement(By.xpath("./td[@class='cart_unit']/span/span")).getText().replace("$", ""));
    }

    public static double getProductTotalUnitsPrice(WebDriver dvr, WebElement product) {
        return Double.parseDouble(
                product.findElement(By.xpath("./td[@class='cart_total']/span")).getText().replace("$", ""));
    }

    public static int getProductQuantity(WebDriver dvr, WebElement product) {
        return Integer.parseInt(
                product.findElement(By.xpath("./td[contains(@class, 'cart_quantity')]/input[1]"))
                        .getAttribute("value"));
    }

    public static double getCartTotalProducts(WebDriver dvr) {
        return Double.parseDouble(AppHelper.findElement(dvr, TOTAL_PRODUCTS).getText().replace("$", ""));
    }

    public static double getCartTotalShipping(WebDriver dvr) {
        return Double.parseDouble(AppHelper.findElement(dvr, TOTAL_SHIPPING).getText().replace("$", ""));
    }

    public static double getCartTotalWithoutTax(WebDriver dvr) {
        return Double.parseDouble(AppHelper.findElement(dvr, TOTAL_WITHOUT_TAX).getText().replace("$", ""));
    }

    public static double getCartTotalTax(WebDriver dvr) {
        return Double.parseDouble(AppHelper.findElement(dvr, TOTAL_TAX).getText().replace("$", ""));
    }

    public static double getCartTotal(WebDriver dvr) {
        return Double.parseDouble(AppHelper.findElement(dvr, TOTAL).getText().replace("$", ""));
    }

    public static boolean checkSummation(WebDriver dvr, Cart expectedCart) {
        double actualTotalProducts = 0, actualTotalShipping = 0, actualTotalWithoutTax = 0,
                actualTotal = 0, actualUnitPrice = 0, actualUnitsTotal = 0;
        int actualQuantity = 0;
        String actualProductName = "";

        actualTotalProducts = getCartTotalProducts(dvr);
        actualTotalShipping = getCartTotalShipping(dvr);
        actualTotalWithoutTax = getCartTotalWithoutTax(dvr);
        actualTotal = getCartTotal(dvr);

        if (actualTotalProducts != expectedCart.getTotalProductsPrice() ||
                actualTotalShipping != expectedCart.getTotalShipping() ||
                actualTotalWithoutTax != expectedCart.getTotalPriceWithOutTax() ||
                actualTotal != expectedCart.getTotal()) {
            return false;
        }

        double actualUnitsTotalSummation = 0;
        for (WebElement product : AppHelper.findElements(dvr, CART_PRODUCTS_LIST)) {
            actualUnitPrice = getProductUnitPrice(dvr, product);
            actualUnitsTotal = getProductTotalUnitsPrice(dvr, product);
            actualQuantity = getProductQuantity(dvr, product);
            actualProductName = getProductName(dvr, product);
            actualUnitsTotalSummation += actualUnitPrice * actualQuantity;
            if (actualUnitsTotal != actualUnitPrice * actualQuantity) {
                return false;
            }
            if (!expectedCart.find(new CartItem(actualProductName, actualUnitPrice, actualQuantity))) {
                return false;
            }
        }
        if (actualUnitsTotalSummation != expectedCart.getTotalProductsPrice() ||
                actualUnitsTotalSummation + actualTotalShipping != expectedCart.getTotalProductsPrice()
                        + expectedCart.getTotalShipping()
                ||
                actualUnitsTotalSummation + actualTotalShipping != actualTotal) {
            return false;
        }
        return true;
    }
}
