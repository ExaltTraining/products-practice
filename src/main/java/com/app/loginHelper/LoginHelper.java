package com.app.loginHelper;

import org.openqa.selenium.WebDriver;

import com.app.AppHelper;

public class LoginHelper {

    public static final String EMAIL_FIELD = "//input[@id='email']";
    public static final String PASSWORD_FIELD = "//input[@id='passwd']";
    public static final String SIGN_IN = "//button[@id='SubmitLogin']";
    public static final String CENTER_COLUMN = "//div[@id='center_column']";
    public static final String ERROR_ALERT = CENTER_COLUMN + "//child::div[contains(@class, alert-danger)]";
    public static final String ERROR_ALERT_TIPS = ERROR_ALERT + "//child::ol";
    public static final String ACCOUNT_NOT_REGISTERED_MSG = "Authentication failed.";
    public static final String HEADING_MAIN_SECTION = "//div[@id='center_column']/h1";
    public static final String TITLE_MAIN_SECTION_MY_ACCOUNT = "MY ACCOUNT";

    public static void enterEmail(WebDriver dvr, String text) {
        AppHelper.findElement(dvr, EMAIL_FIELD).sendKeys(text);
    }

    public static void enterPassword(WebDriver dvr, String text) {
        AppHelper.findElement(dvr, PASSWORD_FIELD).sendKeys(text);
    }

    public static void login(WebDriver dvr) {
        AppHelper.findElement(dvr, SIGN_IN).click();
    }

    public static boolean checkAccountInfoErrorMessage(WebDriver dvr, int index, String msg) {
        return AppHelper.waitUntilTextToBe(3, dvr, ERROR_ALERT_TIPS + "[" + index + "]", msg);
    }

}
