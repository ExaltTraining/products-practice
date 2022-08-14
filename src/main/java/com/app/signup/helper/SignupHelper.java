package com.app.signup.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.app.Account;
import com.app.helper.AppHelper;

public class SignupHelper {

    public static final String INPUT_EMAIL_FIELD = "//input[@id='email_create']";
    public static final String BUTTON_SUBMIT_CREATE = "//button[@id='SubmitCreate']";
    public static final String FORM_SIGN_UP = "//form[@id='create-account_form']";
    public static final String FORM_ACCOUNT_CREATION = "//form[@id='account-creation_form']";
    public static final String DIV_MAIN_SECTION = "//div[@id='center_column']";
    public static final String ERROR_ALERT_TIPS = DIV_MAIN_SECTION + "//child::ol/child::li";
    public static final String HEADING_MAIN_SECTION = "//div[@id='center_column']/h1";
    public static final String TITLE_MAIN_SECTION_MY_ACCOUNT = "MY ACCOUNT";
    public static final String HEADER_SIGN_UP_FORM = FORM_ACCOUNT_CREATION + "/div[1]/h3";
    public static final String TITLE_SIGN_UP_FORM_CREATE_ACCOUNT = "CREATE AN ACCOUNT";
    public static final String TITLE_ACCOUNT_CREATION_FORM_FILL_INFO = "YOUR PERSONAL INFORMATION";
    public static final String ERROR_ALERT = "//div[@id='create_account_error']";
    public static final String ERROR_ACCOUNT_INFO = DIV_MAIN_SECTION + "//child::ol";
    public static final String ITEM_ERROR_ALERT = "//div[@id='create_account_error']//child::li";
    public static final String MESSAGE_ERROR_INVALID = "Invalid email address.";
    public static final String MESSAGE_ERROR_REGISTERED = "this email address has already been registered";
    public static final String INPUT_ERROR_ALERT = FORM_SIGN_UP + "//child::div[contains(@class, 'form-group')]";
    public static final String URL_ACCOUNT_CREATION = AppHelper.WEBSITE_AUTH_URL + "#account-creation";
    public static final String INPUT_FORM_MR_GENDER = "//input[@id='id_gender1']";
    public static final String INPUT_FORM_MRS_GENDER = "//input[@id='id_gender2']";
    public static final String INPUT_FORM_CUSTOMER_FIRSTNAME = "//input[@id='customer_firstname']";
    public static final String INPUT_FORM_CUSTOMER_LASTNAME = "//input[@id='customer_lastname']";
    public static final String INPUT_FORM_EMAIL = "//input[@id='email']";
    public static final String INPUT_FORM_PASSWORD = "//input[@id='passwd']";
    public static final String SELECT_FORM_DAYS = "//select[@id='days']";
    public static final String SELECT_FORM_BIRTHDATE_MONTH = "//select[@id='months']";
    public static final String SELECT_FORM_BIRTHDATE_YEAR = "//select[@id='years']";
    public static final String INPUT_FORM_NEW_SLETTER = "//input[@id='newsletter']";
    public static final String INPUT_FORM_RECEIVE_OFFERS = "//input[@id='optin']";
    public static final String INPUT_FORM_ADDRESS_FIRSTNAME = "//input[@id='firstname']";
    public static final String INPUT_FORM_ADDRESS_LASTNAME = "//input[@id='lastname']";
    public static final String INPUT_FORM_ADDRESS_COMAPNY = "//input[@id='company']";
    public static final String INPUT_FORM_ADDRESS_ADDRESS_ONE = "//input[@id='address1']";
    public static final String INPUT_FORM_ADDRESS_ADDRESS_TWO = "//input[@id='address2']";
    public static final String INPUT_FORM_ADDRESS_CITY = "//input[@id='city']";
    public static final String SELECT_FORM_ADDRESS_STATE = "//select[@id='id_state']";
    public static final String INPUT_FORM_ADDRESS_POSTAL_CODE = "//input[@id='postcode']";
    public static final String SELECT_FORM_ADDRESS_COUNTRY = "//select[@id='id_country']";
    public static final String TEXTAREA_FORM_ADDRESS_OTHER = "//textarea[@id='other']";
    public static final String INPUT_FORM_ADDRESS_PHONE = "//input[@id='phone']";
    public static final String INPUT_FORM_ADDRESS_MOBILE = "//input[@id='phone_mobile']";
    public static final String INPUT_FORM_ADDRESS_ALIAS = "//input[@id='alias']";
    public static final String BUTTON_FORM_REGISTER = "//button[@id='submitAccount']";
    public static final String[] MESSAGE_ERROR_ACCOUNT_CREATION_INFO = new String[] {
            "You must register at least one phone number.",
            "lastname is required.",
            "firstname is required.",
            "email is required.",
            "passwd is required.",
            "id_country is required.",
            "alias is required.",
            "address1 is required.",
            "city is required.",
            "Country cannot be loaded with address->id_country",
            "Country is invalid"
    };

    public static void enterEmail(WebDriver dvr, String text) {
        AppHelper.findElement(dvr, INPUT_EMAIL_FIELD).sendKeys(text);
    }

    public static void submitEmail(WebDriver dvr) {
        AppHelper.findElement(dvr, BUTTON_SUBMIT_CREATE).click();
    }

    public static void createNewAccount(WebDriver dvr, Account info) {
        AppHelper.click(dvr, info.getGender());
        AppHelper.sendTextTo(dvr, info.getFirstname(), SignupHelper.INPUT_FORM_CUSTOMER_FIRSTNAME);
        AppHelper.sendTextTo(dvr, info.getLastname(), SignupHelper.INPUT_FORM_CUSTOMER_LASTNAME);
        AppHelper.sendTextTo(dvr, info.getPassword(), SignupHelper.INPUT_FORM_PASSWORD);
        AppHelper.selectByValueFrom(dvr, info.getBirthdate_days(),
                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_DAYS));
        AppHelper.selectByValueFrom(dvr, info.getBirthdate_month(),
                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_BIRTHDATE_MONTH));
        AppHelper.selectByValueFrom(dvr, info.getBirthdate_year(),
                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_BIRTHDATE_YEAR));
        AppHelper.click(dvr, SignupHelper.INPUT_FORM_NEW_SLETTER);
        AppHelper.click(dvr, SignupHelper.INPUT_FORM_RECEIVE_OFFERS);
        AppHelper.sendTextTo(dvr, info.getAddressFirstName(), SignupHelper.INPUT_FORM_ADDRESS_FIRSTNAME);
        AppHelper.sendTextTo(dvr, info.getAddressLastName(), SignupHelper.INPUT_FORM_ADDRESS_LASTNAME);
        AppHelper.sendTextTo(dvr, info.getCompanyAddress(), SignupHelper.INPUT_FORM_ADDRESS_COMAPNY);
        AppHelper.sendTextTo(dvr, info.getAddressOne(), SignupHelper.INPUT_FORM_ADDRESS_ADDRESS_ONE);
        AppHelper.sendTextTo(dvr, info.getAddressTwo(), SignupHelper.INPUT_FORM_ADDRESS_ADDRESS_TWO);
        AppHelper.sendTextTo(dvr, info.getCity(), SignupHelper.INPUT_FORM_ADDRESS_CITY);
        AppHelper.selectByValueFrom(dvr, info.getState(),
                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_ADDRESS_STATE));
        AppHelper.sendTextTo(dvr, info.getPostalCode(), SignupHelper.INPUT_FORM_ADDRESS_POSTAL_CODE);
        AppHelper.selectByValueFrom(dvr, info.getCountry(),
                AppHelper.findElement(dvr, SignupHelper.SELECT_FORM_ADDRESS_COUNTRY));
        AppHelper.sendTextTo(dvr, info.getOther(), SignupHelper.TEXTAREA_FORM_ADDRESS_OTHER);
        AppHelper.sendTextTo(dvr, info.getPhone(), SignupHelper.INPUT_FORM_ADDRESS_PHONE);
        AppHelper.sendTextTo(dvr, info.getMobile(), SignupHelper.INPUT_FORM_ADDRESS_MOBILE);
        AppHelper.sendTextTo(dvr, info.getAlias(), SignupHelper.INPUT_FORM_ADDRESS_ALIAS);
        AppHelper.click(dvr, SignupHelper.BUTTON_FORM_REGISTER);
    }

    public static boolean isEmailInvalid(WebDriver dvr) {
        return checkErrorMessage(dvr, MESSAGE_ERROR_INVALID);
    }

    public static boolean isEmailRegistered(WebDriver dvr) {
        return checkErrorMessage(dvr, MESSAGE_ERROR_REGISTERED);
    }

    public static boolean checkErrorMessage(WebDriver dvr, String msg) {
        WebElement errorMessageAlert = AppHelper.findElement(dvr, ERROR_ALERT);
        String styleAttribute = errorMessageAlert.getAttribute("style");
        if (styleAttribute.equals("display: block;")) {
            try {
                errorMessageAlert
                        .findElement(By.xpath("./child::li[contains(text(), '" + msg + "')]"));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkAccountInfoErrorMessage(WebDriver dvr, int index, String msg) {
        return AppHelper.waitUntilTextToBe(3, dvr, ERROR_ALERT_TIPS + "[" + index + "]", msg);
    }

}
