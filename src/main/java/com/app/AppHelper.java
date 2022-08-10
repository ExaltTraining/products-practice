package com.app;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppHelper {
    public static final String WEBSITE_BASE_URL = "http://automationpractice.com";
    public static final String WEBSITE_AUTH_URL = WEBSITE_BASE_URL + "/index.php?controller=authentication";
    public static final String WEBSITE_WOMEN_PRODUCTS_URL = WEBSITE_BASE_URL
            + "/index.php?id_category=3&controller=category";
    public static final String CHROME_DRIVER_PATH_103 = "drivers\\chromedriver.exe";
    public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    public static final String HEADER_SIGN_IN_BUTTON = "//header[@id='header']//child::a[@class='login']";
    public static final String HEADER_SIGN_OUT_BUTTON = "//header[@id='header']//child::a[@class='logout']";

    public static void log(String text) {
        print("> " + text);
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void run(Method method) {
        log("Running: " + method);
    }

    public static void finish(Method method) {
        log("Finished: " + method);
    }

    public static boolean waitUntilPresenceOf(int secTimeOut, WebDriver dvr, String xpath) {
        return waitUntil(secTimeOut, dvr, ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static boolean waitUntilNotPresenceOf(int secTimeOut, WebDriver dvr, String xpath) {
        return waitUntil(secTimeOut, dvr, ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public static boolean waitUntilTextToBe(int secTimeOut, WebDriver dvr, String xpath, String text) {
        return waitUntil(secTimeOut, dvr, ExpectedConditions.textToBe(By.xpath(xpath), text));
    }

    public static <T> boolean waitUntil(int secTimeOut, WebDriver dvr, ExpectedCondition<T> condition) {
        try {
            new WebDriverWait(dvr, Duration.ofSeconds(secTimeOut)).until(condition);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public static WebElement findElement(WebDriver dvr, String xpath) {
        return dvr.findElement(By.xpath(xpath));
    }

    public static List<WebElement> findElements(WebDriver dvr, String xpath) {
        return dvr.findElements(By.xpath(xpath));
    }

    public static boolean isUrlChangedTo(WebDriver dvr, String newExpectedUrl) {
        return dvr.getCurrentUrl().equals(newExpectedUrl);
    }

    public static void selectByValueFrom(WebDriver dvr, String value, WebElement element) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void selectByTextFrom(WebDriver dvr, String text, WebElement element) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static void click(WebDriver dvr, String xpath) {
        findElement(dvr, xpath).click();
    }

    public static void sendTextTo(WebDriver dvr, String text, String xpath) {
        findElement(dvr, xpath).clear();
        findElement(dvr, xpath).sendKeys(text);
    }

}
