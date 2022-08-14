package com.app.signup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.app.Account;
import com.app.helper.AppHelper;
import com.app.signup.helper.SignupHelper;

public class SignupTest {

        private WebDriver dvr;
        private boolean loggedIn = false;

        @BeforeTest
        public void setup() {
                AppHelper.print("<<------ Setting up ------>>");
                System.setProperty(AppHelper.CHROME_DRIVER_KEY, AppHelper.CHROME_DRIVER_PATH_103);
                dvr = new ChromeDriver();
                dvr.get(AppHelper.WEBSITE_AUTH_URL);
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
                if (loggedIn) {
                        AppHelper.click(dvr, AppHelper.HEADER_SIGN_OUT_BUTTON);
                        loggedIn = false;
                }
                AppHelper.log("DONE :)");
                AppHelper.print("--------------------------");
        }

        @Test
        public void validateCreatingNewAccountTest() {
                AppHelper.run(new SignupTest() {
                }.getClass().getEnclosingMethod());
                AppHelper.findElement(dvr, SignupHelper.INPUT_EMAIL_FIELD).clear();

                Account ac = Account.generateNewAccount();

                SignupHelper.enterEmail(dvr, ac.getEmail());
                SignupHelper.submitEmail(dvr);

                Assert.assertTrue(
                                AppHelper.waitUntilTextToBe(5, dvr, SignupHelper.HEADER_SIGN_UP_FORM,
                                                SignupHelper.TITLE_ACCOUNT_CREATION_FORM_FILL_INFO),
                                "Page didn't navigate into account creation page.");

                SignupHelper.createNewAccount(dvr, ac);

                Assert.assertTrue(AppHelper.waitUntilTextToBe(5, dvr, SignupHelper.HEADING_MAIN_SECTION,
                                SignupHelper.TITLE_MAIN_SECTION_MY_ACCOUNT),
                                "Page didn't navigate into my account page.");
                loggedIn = true;
                AppHelper.finish(new SignupTest() {
                }.getClass().getEnclosingMethod());
        }

        @Test
        public void validateInvalidEmailTest() {
                AppHelper.run(new SignupTest() {
                }.getClass().getEnclosingMethod());

                SignupHelper.enterEmail(dvr, "newaccount" + (int) (Math.random() * 1000000) + "@gmail");
                SignupHelper.submitEmail(dvr);

                Assert.assertTrue(
                                AppHelper.waitUntilTextToBe(5, dvr, SignupHelper.HEADER_SIGN_UP_FORM,
                                                SignupHelper.TITLE_ACCOUNT_CREATION_FORM_FILL_INFO),
                                "Page didn't navigate into account creation page.");

                AppHelper.finish(new SignupTest() {
                }.getClass().getEnclosingMethod());
        }

        @Test
        public void validateInvalidAccountInfoTest() {
                AppHelper.run(new SignupTest() {
                }.getClass().getEnclosingMethod());

                SignupHelper.enterEmail(dvr, "newaccount" + (int) (Math.random() * 1000000) + "@gmail.com");
                SignupHelper.submitEmail(dvr);

                Assert.assertTrue(
                                AppHelper.waitUntilTextToBe(5, dvr, SignupHelper.HEADER_SIGN_UP_FORM,
                                                SignupHelper.TITLE_ACCOUNT_CREATION_FORM_FILL_INFO),
                                "Page didn't navigate into account creation page.");

                AppHelper.selectByTextFrom(dvr, "-",
                                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_ADDRESS_COUNTRY));
                AppHelper.findElement(dvr, SignupHelper.INPUT_FORM_EMAIL).clear();
                AppHelper.findElement(dvr, SignupHelper.INPUT_FORM_ADDRESS_ALIAS).clear();
                AppHelper.click(dvr, SignupHelper.BUTTON_FORM_REGISTER);

                AppHelper.waitUntilPresenceOf(1, dvr, SignupHelper.ERROR_ACCOUNT_INFO);
                for (int i = 0; i < SignupHelper.MESSAGE_ERROR_ACCOUNT_CREATION_INFO.length; i++) {
                        AppHelper.waitUntilPresenceOf(2, dvr, SignupHelper.ERROR_ALERT_TIPS + "[" +
                                        (i + 1) + "]");
                        Assert.assertTrue(
                                        SignupHelper.checkAccountInfoErrorMessage(dvr, i + 1,
                                                        SignupHelper.MESSAGE_ERROR_ACCOUNT_CREATION_INFO[i]),
                                        "(" + SignupHelper.MESSAGE_ERROR_ACCOUNT_CREATION_INFO[i]
                                                        + ") Message not found.");
                }

                AppHelper.finish(new SignupTest() {
                }.getClass().getEnclosingMethod());
        }

        @AfterTest
        public void teardown() throws InterruptedException {
                AppHelper.log("----- Teardown, quiting after 15secs!");
                Thread.sleep(1000);
                // dvr.quit();
        }
}
