package com.testautomation.framework.driverconfig;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface WebDriver<S extends RemoteWebDriver,T extends Capabilities> {

  S buildWebDriver(T options) throws MalformedURLException;

}
