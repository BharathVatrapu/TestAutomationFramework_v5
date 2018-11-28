package com.testProject.pagemodel.mobileApp;

import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.utils.Log;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class HomeScreen {

    private ConfigTestData configTestData=null;
    public HomeScreen(ConfigTestData configTestData){
        this.configTestData=configTestData;
        PageFactory.initElements(new AppiumFieldDecorator(configTestData.driver), this);
    }
    // Variable Declaration

    //********* HomePage Page Web Elements by using Page Factory*********

    @CacheLookup
    @FindAll({
            @FindBy(xpath = "//XCUIElementTypeButton[@label='just for U']"),
            @FindBy(xpath = "//andr oid.widget.TextView[@text='just for U']")
    })
    public WebElement btnJustforU;

    @CacheLookup
    @FindAll({
            @FindBy(xpath = "//XCUIElementTypeButton[@label='My Store']"),
            @FindBy(xpath = "//android.widget.TextView[@text='My Store']")
    })
    public WebElement btnMyStore;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()=\"Gmail\"]")
    public WebElement lnkGmail;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()=\"Images\"]")
    public WebElement lnkImages;


    //#################################################################
    //#################################################################
    //##################        METHODS        #######################
    //#################################################################
    //#################################################################
    public boolean gotoJustForU() throws Exception{
        configTestData.stepDescription = "Just for u screen displayed";
        Log.startLog("'gotoJustForU'");
        btnJustforU.click();
        Log.endLog("gotoJustForU");
        return true;

    }
    public boolean gotoMyStore() throws Exception{
        configTestData.stepDescription = "My Store screen displayed";
        Log.startLog("'gotoMyStore'");
        btnMyStore.click();
        Log.endLog("gotoMyStore");
        return true;
    }

}
