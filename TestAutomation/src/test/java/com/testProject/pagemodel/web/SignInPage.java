package com.testProject.pagemodel.web;


import com.testProject.base.TestBase;
import com.testProject.constants.dataKey;
import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.utils.DriverGeneric;
import com.testautomation.framework.utils.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends DriverGeneric {

    public SignInPage(ConfigTestData configTestData) {
        super(configTestData);
        PageFactory.initElements(this.driver,this);
    }


    //********* Sign In Page Web Elements by using Page Factory*********

    @CacheLookup
    @FindBy(xpath = "//a[text()=\"Forgot your email address?\"]")
    public WebElement lnkForgotyouremailaddress;

    @CacheLookup
    @FindBy(xpath = "//a[text()=\"Forgot your password?\"]")
    public WebElement lnkForgotyourpassword;

    @CacheLookup
    @FindBy(xpath = "//a/ng-transclude/span[text()=\"Can't access your account?\"]")
    public WebElement lnkCantaccessyouraccount;


    @CacheLookup
    @FindBy(id="create-account-btn")
    public WebElement btnSignIn;


    @CacheLookup
    @FindBy(id="show-password-checkbox")
    public WebElement chkboxshowpasswordcheckbox;

    @CacheLookup
    @FindBy(id="keepMeSignedIn")
    public WebElement chkboxkeepMeSignedIn;


    @CacheLookup
    @FindBy(id="input-email")
    public WebElement inputemail;

    @CacheLookup
    @FindBy(id="password-password")
    public WebElement inputpasswordpassword;



    //*********Page Methods*********

    public boolean gotoSignInPage(){
        configTestData.stepDescription = "Navigate to SignIn page.";
        String signInUrl= configTestData.read(dataKey.SignInPage.toString());
        Log.startLog("SignInPage:::'gotoSignInPage'");
        navigateToURL(signInUrl);
        Log.info("Navigated URL is :"+signInUrl);
        Log.endLog("SignInPage:::'gotoSignInPage'");

        if (!verifyPageUrl(signInUrl))
            return false;

        return true;
    }
    public boolean userLogin (String emailAddress, String password){
        Log.startLog("SignInPage:::'userLogin'");
        configTestData.stepDescription = "Enter email and password, click SignIn.";
        //Enter Username(Email)
        enterEmailAddress(emailAddress);
        //Enter Password(password)
        enterPassword(password);
        //click SignIn
        clickSignIn();
        sysWait(300);
        if (!verifyPageUrl((configTestData.read(dataKey.HomePage.toString())))) {
            return false;
        }
        Log.endLog("SignInPage:::'userLogin'");
        return true;

    }
    public boolean userLogin (){
        Log.startLog("SignInPage:::'userLogin'");
        String email = configTestData.read(dataKey.EmailAddress.toString());
        String password = ConfigTestData.read(dataKey.Password.toString());
        configTestData.stepDescription = "Enter email and password, click SignIn.";
        //Enter Username(Email)
        enterEmailAddress(email);
        //Enter Password(password)
        enterPassword(password);
        Log.info("emailaddress:"+email+" -- "+"password:"+password);
        //click SignIn
        clickSignIn();
//        sysWait(300);
//        if (!verifyPageUrl((configTestData.read(dataKey.HomePage.toString())))) {
//            return false;
//        }
        Log.endLog("SignInPage:::'userLogin'");
        return true;
    }


    // Operatations
    public void enterEmailAddress(String emailAddress){
        inputemail.sendKeys(emailAddress);
    }
    public void enterPassword(String password){
        inputpasswordpassword.sendKeys(password);
    }
    public void clickSignIn(){
        jsexecutor.executeScript("arguments[0].click();", btnSignIn);
    }

}
