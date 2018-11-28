package com.testautomation.framework.driverconfig;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseWebDriver<S extends RemoteWebDriver, T extends Capabilities, V extends BaseWebDriver> implements
        WebDriver<S, T> {

  private final int PAGE_TIME_OUT = 30;

  protected S setWebDriverManage(S driver) {
    driver.manage().timeouts().implicitlyWait(PAGE_TIME_OUT, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(PAGE_TIME_OUT, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    return driver;
  }



  protected abstract V setDriverPath();

  protected abstract T getDefaultOptions();

  protected abstract T getOptions(T options);

}
