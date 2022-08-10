package com.app;

import com.app.signUpHelper.SignupHelper;

public class Account {
    private String gender;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String birthdate_days;
    private String birthdate_month;
    private String birthdate_year;
    private boolean newSletter;
    private boolean recieveOffers;
    private String addressFirstName;
    private String addressLastName;
    private String companyAddress;
    private String addressOne;
    private String addressTwo;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String other;
    private String phone;
    private String mobile;
    private String alias;

    public Account(String gender, String firstname, String lastname, String email, String password,
            String birthdate_days,
            String birthdate_month, String birthdate_year, boolean newSletter, boolean recieveOffers,
            String addressFirstName, String addressLastName, String companyAddress, String addressOne,
            String addressTwo, String city, String state, String postalCode, String country, String other, String phone,
            String mobile, String alias) {
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.birthdate_days = birthdate_days;
        this.birthdate_month = birthdate_month;
        this.birthdate_year = birthdate_year;
        this.newSletter = newSletter;
        this.recieveOffers = recieveOffers;
        this.addressFirstName = addressFirstName;
        this.addressLastName = addressLastName;
        this.companyAddress = companyAddress;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.other = other;
        this.phone = phone;
        this.mobile = mobile;
        this.alias = alias;
    }

    public static Account generateNewAccount() {
        return new Account(SignupHelper.INPUT_FORM_MR_GENDER, "Abd", "Ibreighith",
                "newaccount" + (int) (Math.random() * 1000000) + "@gmail.com", "qwer1234Q!",
                "23", "3", "2001", true,
                true, "Abd", "Ibreighith", "EXALT", "Ramallah",
                "Ramallah", "Ramallah", "3", "12345", "21", "other",
                "12312312", "234234234", "ABD");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate_days() {
        return birthdate_days;
    }

    public void setBirthdate_days(String birthdate_days) {
        this.birthdate_days = birthdate_days;
    }

    public String getBirthdate_month() {
        return birthdate_month;
    }

    public void setBirthdate_month(String birthdate_month) {
        this.birthdate_month = birthdate_month;
    }

    public String getBirthdate_year() {
        return birthdate_year;
    }

    public void setBirthdate_year(String birthdate_year) {
        this.birthdate_year = birthdate_year;
    }

    public boolean isNewSletter() {
        return newSletter;
    }

    public void setNewSletter(boolean newSletter) {
        this.newSletter = newSletter;
    }

    public boolean isRecieveOffers() {
        return recieveOffers;
    }

    public void setRecieveOffers(boolean recieveOffers) {
        this.recieveOffers = recieveOffers;
    }

    public String getAddressFirstName() {
        return addressFirstName;
    }

    public void setAddressFirstName(String addressFirstName) {
        this.addressFirstName = addressFirstName;
    }

    public String getAddressLastName() {
        return addressLastName;
    }

    public void setAddressLastName(String addressLastName) {
        this.addressLastName = addressLastName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
