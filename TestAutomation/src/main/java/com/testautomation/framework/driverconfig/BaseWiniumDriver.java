package com.testautomation.framework.driverconfig;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public abstract class BaseWiniumDriver<S extends WiniumDriver, T extends DesktopOptions,W extends WiniumDriverService, V extends BaseWiniumDriver> implements
        Winium<S, T, W> {

  private final int SCREEN_TIME_OUT = 30;

  protected S setWindowAppDriverManage(S driver) {
    //driver.manage().timeouts().implicitlyWait(SCREEN_TIME_OUT, TimeUnit.SECONDS);

       return driver;
  }
  protected abstract V setDriverPath();

  protected abstract T getDefaultOptions();

  protected abstract T getOptions(T options);

  protected abstract W getDefaultServices();

  protected abstract W getServices(W services);

}
