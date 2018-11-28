package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Generic;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class SafariBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, SafariBrowser> {

    private ConfigTestData configTestData=null;
    public SafariBrowser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }
  @Override
  protected SafariBrowser setDriverPath() {

    return null;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        SafariOptions safarioptions = new SafariOptions();
        //    options.setUseCleanSession(true);
        capabilities = DesiredCapabilities.safari();
        capabilities.setCapability(SafariOptions.CAPABILITY, safarioptions);
        capabilities.setBrowserName(DesiredCapabilities.safari().getBrowserName());
        capabilities.setPlatform(Platform.WINDOWS);

        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      configTestData.safariDriver = setWebDriverManage(new RemoteWebDriver(new URL(Generic.readConfigProp("localGridHub")), getOptions(options)));
      return configTestData.safariDriver;
  }

}
