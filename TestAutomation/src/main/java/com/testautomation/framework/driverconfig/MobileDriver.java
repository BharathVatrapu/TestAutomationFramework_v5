package com.testautomation.framework.driverconfig;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface MobileDriver<S extends AppiumDriver,T extends Capabilities> {

  S buildMobileDriver(T options) throws MalformedURLException;

}
