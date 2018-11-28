package com.testautomation.framework.utils;

import com.testautomation.framework.base.ConfigBase;
import com.testautomation.framework.base.ConfigTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

public class DriverGeneric {

    public RemoteWebDriver driver;
    public ConfigTestData configTestData=null;
    public JavascriptExecutor jsexecutor;

    public DriverGeneric(ConfigTestData configTestData) {
        this.driver = configTestData.driver;
        this.configTestData = configTestData;
        jsexecutor = (JavascriptExecutor) this.driver;

    }



    public void navigateToURL(String URL) {
        try {
            driver.get(URL);
            if (!driver.getCurrentUrl().equals(URL)) {
                driver.get(URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public boolean verifyPageUrl(String Url){
        boolean returnStatus = true;
        String getpageUrl=driver.getCurrentUrl();
        if(Url.contains("https")){
            Url = Url.replaceAll("https://","");
        } else {
            Url = Url.replaceAll("http://","");
        }
        getpageUrl=getpageUrl.toLowerCase().trim();
        Url=Url.toLowerCase().trim();
        if(!getpageUrl.contains(Url)){
            returnStatus = false;
            System.out.println(getpageUrl+"::"+Url);
        }
        return returnStatus;
    }

    public void sysWait(final long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Clicks an Element (Buttons, Links)
    public <T> void click (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }
    //Enters a value into Edit box/Text box
    public <T> void writeValue (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //It clears the value
    public <T> void clearValue (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readValue (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }


}
