package com.testProject.constants;

public enum dataKey {

    // URLS
    HomePage("HomePage"),
    SignInPage("SignInPage"),
    RegistrationPage("RegistrationPage"),

    //SIGNIN DATA
    EmailAddress("EmailAddress"),
    Password("Password"),

    //REGISTRATION DATA
    FirstName("FirstName"),
    LastName("LastName"),
    CCNumber("CCNumber"),
    PhoneNumber("PhoneNumber"),
    ZIPCode("ZIPCode"),
    City("City"),
    State("State"),
    StoreAddress("StoreAddress"),

       ;
    private String page;

    private dataKey(String page) {
        this.setPage(page);
    }

    public String getPage() {
        return this.page;
    }

    private void setPage(String name) {
        this.page = name;
    }
}
