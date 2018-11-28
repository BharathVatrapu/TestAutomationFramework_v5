package com.testautomation.framework.base;

import com.aventstack.extentreports.Status;
import com.testautomation.framework.utils.Generic;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigTestData {

    public static String workDir = System.getProperty("user.dir");

    // Testng params
    public String testEnvironment = null;
    public String testservice = null;
    public String testPlatform = null;
    public String testBrowser = null;
    public String testDeviceName = null;
    public String testAppName = null;
    public String testNetowk = null;

    public String banner = null;
    public String groupName = null;
    // Mobile Capabilities Testng params
    public String mb_udid = null;
    public String mb_deviceName = null;
    public String mb_platformName = null;
    public String mb_platformVersion = null;
    public String mb_manufacturer = null;
    public String mb_appActivity = null;

    public String testMethodName = null;

    // Remote we driver
    public RemoteWebDriver mobileWebDriver = null;
    public RemoteWebDriver chromeDriver = null;
    public RemoteWebDriver firefoxDriver = null;
    public RemoteWebDriver internerExplorerDriver = null;
    public RemoteWebDriver safariDriver = null;
    public RemoteWebDriver edgeDriver = null;
    public RemoteWebDriver operaDriver = null;

    public AppiumDriver androidDriver = null;
    public AppiumDriver iOSDriver = null;
    public WiniumDriver winDriver = null;
    public RemoteWebDriver driver = null;


    public String TEST_DATA_FILE_PATH = null;

    public static String CONFIG_PROP_FILE_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources";

    public Status finalTestCaseStatus = Status.PASS;

    public String suiteXmlName=null;

    public int stepNo =  0;
    public String stepDescription =  null;



    public static String read(String key){
        return ConfigBase.rbTestdata.getString(key);
    }
}
