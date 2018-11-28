package com.testautomation.framework.driverconfig;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;

import java.util.concurrent.TimeUnit;

public abstract class BaseMobileDriver<S extends AppiumDriver, T extends Capabilities, V extends BaseMobileDriver> implements
        MobileDriver<S, T> {

  private final int SCREEN_TIME_OUT = 30;

  protected S setMobileAppDriverManage(S driver) {
    driver.manage().timeouts().implicitlyWait(SCREEN_TIME_OUT, TimeUnit.SECONDS);
    driver.launchApp();
    return driver;
  }

  protected abstract T getDefaultOptions();

  protected abstract T getOptions(T options);

}
