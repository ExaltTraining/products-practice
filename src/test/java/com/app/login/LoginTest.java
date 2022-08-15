package com.app.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.helper.AppHelper;
import com.app.login.helper.LoginHelper;

public class LoginTest {

    private WebDriver dvr;
    private boolean loggedIn = false;

    @BeforeTest
    public void setup() {
        AppHelper.log("<<------ Setting up ------>>");
        System.setProperty(AppHelper.CHROME_DRIVER_KEY, AppHelper.CHROME_DRIVER_PATH_103);
        dvr = new ChromeDriver();
        dvr.get(AppHelper.WEBSITE_AUTH_URL);
        AppHelper.log("<<------ Completed ------>>");
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
        if (loggedIn) {
            AppHelper.click(dvr, AppHelper.HEADER_SIGN_OUT_BUTTON);
            loggedIn = false;
        }
        AppHelper.findElement(dvr, LoginHelper.EMAIL_FIELD).clear();
        AppHelper.findElement(dvr, LoginHelper.PASSWORD_FIELD).clear();
        AppHelper.log("DONE :)");
        AppHelper.print("--------------------------");
    }

    @Test
    public void validateSuccessfulLoginTest() {
        AppHelper.run(new LoginTest() {
        }.getClass().getEnclosingMethod());

        LoginHelper.enterEmail(dvr, AppHelper.FIXED_EMAIL);
        LoginHelper.enterPassword(dvr, AppHelper.FIXED_PASSWORD);
        LoginHelper.login(dvr);

        Assert.assertTrue(AppHelper.waitUntilTextToBe(5, dvr, LoginHelper.HEADING_MAIN_SECTION,
                LoginHelper.TITLE_MAIN_SECTION_MY_ACCOUNT), "Page didn't navigate into my account page.");
        loggedIn = true;
        AppHelper.finish(new LoginTest() {
        }.getClass().getEnclosingMethod());
    }

    @Test
    public void validateNotRegisteredLoginTest() {
        AppHelper.run(new LoginTest() {
        }.getClass().getEnclosingMethod());

        LoginHelper.enterEmail(dvr, "newaccount@gmail.com");
        LoginHelper.enterPassword(dvr, "qwer1234Q!12312");
        LoginHelper.login(dvr);

        Assert.assertTrue(LoginHelper.checkAccountInfoErrorMessage(dvr, 1, LoginHelper.ACCOUNT_NOT_REGISTERED_MSG));

        AppHelper.finish(new LoginTest() {
        }.getClass().getEnclosingMethod());
    }

    @AfterTest
    public void teardown() throws InterruptedException {
        AppHelper.log("----- Teardown, quiting after 5secs!");
        Thread.sleep(5000);
        dvr.quit();
    }

}
