package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Generic;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class OperaBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, OperaBrowser> {

    String operaBinary=null;
    private ConfigTestData configTestData=null;
    public OperaBrowser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }
  @Override
  protected OperaBrowser setDriverPath() {

    return null;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {
      operaBinary = "C:\\Program Files\\Opera\\launcher.exe";
      OperaOptions options = new OperaOptions();
      options.setBinary(operaBinary);

      DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
      capabilities.setBrowserName("operablink");
//
//      capabilities.setCapability("opera.binary", "C:\\Program Files\\Opera\\launcher.exe");
    //  capabilities.setCapability(Capabili, "/path/to/your/opera");

//      capabilities.setCapability("opera.log.level", "CONFIG");
     // capabilities.setCapability("platform", "WINDOWS");
      //capabilities.setVersion("11");
//        capabilities.setPlatform(Platform.WINDOWS);

        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      configTestData.operaDriver = setWebDriverManage(new RemoteWebDriver(new URL(Generic.readConfigProp("localGridHub")), getOptions(options)));
      return configTestData.operaDriver;
  }

}
