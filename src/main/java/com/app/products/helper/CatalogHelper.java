package com.app.products.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.app.helper.AppHelper;

public class CatalogHelper {

    public static final String CHECK_TOP_CATEGORY = "//input[@id='layered_category_4']";
    public static final String CHECK_DRESSES_CATEGORY = "//input[@id='layered_category_8']";
    public static final String CHECK_S_SIZE = "//input[@id='layered_id_attribute_group_1']";
    public static final String CHECK_M_SIZE = "//input[@id='layered_id_attribute_group_2']";
    public static final String CHECK_L_SIZE = "//input[@id='layered_id_attribute_group_3']";
    public static final String CHECK_BEIGE_COLOR = "//input[@id='layered_id_attribute_group_7']";
    public static final String CHECK_WHITE_COLOR = "//input[@id='layered_id_attribute_group_8']";
    public static final String CHECK_BLACK_COLOR = "//input[@id='layered_id_attribute_group_11']";
    public static final String CHECK_ORANGE_COLOR = "//input[@id='layered_id_attribute_group_13']";
    public static final String CHECK_BLUE_COLOR = "//input[@id='layered_id_attribute_group_14']";
    public static final String CHECK_GREEN_COLOR = "//input[@id='layered_id_attribute_group_15']";
    public static final String CHECK_YELLOW_COLOR = "//input[@id='layered_id_attribute_group_16']";
    public static final String CHECK_PINK_COLOR = "//input[@id='layered_id_attribute_group_24']";
    public static final String CHECK_COTTON_COMPOSITIONS = "//input[@id='layered_id_feature_5']";
    public static final String CHECK_POLYESTER_COMPOSITIONS = "//input[@id='layered_id_feature_1']";
    public static final String CHECK_VISCOSE_COMPOSITIONS = "//input[@id='layered_id_feature_3']";
    public static final String CHECK_CASUAL_STYLES = "//input[@id='layered_id_feature_11']";
    public static final String CHECK_DRESSY_STYLES = "//input[@id='layered_id_feature_16']";
    public static final String CHECK_GIRLY_STYLES = "//input[@id='layered_id_feature_13']";
    public static final String CHECK_COLORFUL_DRESS_PROPERTIES = "//input[@id='layered_id_feature_18']";
    public static final String CHECK_MAXI_DRESS_PROPERTIES = "//input[@id='layered_id_feature_21']";
    public static final String CHECK_MIDI_DRESS_PROPERTIES = "//input[@id='layered_id_feature_20']";
    public static final String CHECK_SHORT_DRESS_PROPERTIES = "//input[@id='layered_id_feature_19']";
    public static final String CHECK_SHORT_SLEEVE_PROPERTIES = "//input[@id='layered_id_feature_17']";
    public static final String CHECK_IN_STOCK_AVAILABILITY = "//input[@id='layered_quantity_1']";
    public static final String CHECK_FASHION_MANUFACTURER_MANUFACTURER = "//input[@id='layered_manufacturer_1']";
    public static final String CHECK_NEW_CONDITION = "//input[@id='layered_condition_new']";
    public static final String LIST_ENABLED_FILTERS = "//div[@id='enabled_filters']//child::ul";
    public static final String SLIDER_PRICE_RANGE = "//div[@id='layered_price_slider']";
    public static final String SLIDER_PRICE_RANGE_MIN = SLIDER_PRICE_RANGE + "/a[1]";
    public static final String SLIDER_PRICE_RANGE_MAX = SLIDER_PRICE_RANGE + "/a[2]";
    public static final String LABEL_PRICE_RANGE = "//span[@id='layered_price_range']";

    public static String getEnabledFilterCancelButton(int index) {
        return LIST_ENABLED_FILTERS + "/li[" + index + "]//a[@title='Cancel']";
    }

    public static boolean isThisFilterEnabled(WebDriver dvr, String filterName) {
        try {
            List<WebElement> products = AppHelper.findElements(dvr, LIST_ENABLED_FILTERS + "/li");
            for (WebElement p : products) {
                if (p.getText().contains(filterName) == true)
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String[] getPrice(WebDriver dvr) {
        return AppHelper.findElement(dvr, LABEL_PRICE_RANGE).getText().split("-");
    }

    public static double getMinPrice(WebDriver dvr) {
        return Double.parseDouble(getPrice(dvr)[0].replace("$", ""));
    }

    public static double getMaxPrice(WebDriver dvr) {
        return Double.parseDouble(getPrice(dvr)[1].replace("$", ""));
    }

    public static double getMinPricePercentage(WebDriver dvr) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(AppHelper.findElement(dvr, SLIDER_PRICE_RANGE_MIN).getAttribute("style"));
        m.find();
        return Double.parseDouble(m.group());
    }

    public static double getMaxPricePercentage(WebDriver dvr) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(AppHelper.findElement(dvr, SLIDER_PRICE_RANGE_MAX).getAttribute("style"));
        m.find();
        return Double.parseDouble(m.group());
    }

    public static void setMinPrice(WebDriver dvr, double minPrice) {
        int dir = 1;
        if (getMinPrice(dvr) > minPrice) {
            dir = -1;
        }
        Action move = new Actions(dvr).moveToElement(AppHelper.findElement(dvr, SLIDER_PRICE_RANGE_MIN)).clickAndHold()
                .moveByOffset(dir, 0).release().build();
        double minDiff = Double.MAX_VALUE;
        while (true) {
            if (Math.abs(getMinPrice(dvr) - (minPrice - 1)) > minDiff)
                break;
            minDiff = Math.abs(getMinPrice(dvr) - minPrice);
            move.perform();
        }
    }

    public static void setMaxPrice(WebDriver dvr, double maxPrice) {
        int dir = 1;
        if (getMaxPrice(dvr) > maxPrice) {
            dir = -1;
        }
        Action move = new Actions(dvr).moveToElement(AppHelper.findElement(dvr, SLIDER_PRICE_RANGE_MAX)).clickAndHold()
                .moveByOffset(dir, 0).release().build();
        double minDiff = Double.MAX_VALUE;
        while (true) {
            if (Math.abs(getMaxPrice(dvr) - (maxPrice + 1)) > minDiff) {
                break;
            }
            minDiff = Math.abs(getMaxPrice(dvr) - maxPrice);
            move.perform();
        }
    }

    public static String getFilterName(WebDriver dvr, String filterXPath) {
        return AppHelper.findElement(dvr, filterXPath).getText();
    }

}
