package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.driverconfig.BaseWebDriver;
import com.testautomation.framework.utils.Generic;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileBrowser extends BaseWebDriver<RemoteWebDriver, DesiredCapabilities, MobileBrowser> {
    private ConfigTestData configTestData=null;
    public MobileBrowser(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }
  @Override
  protected MobileBrowser setDriverPath() {
    return null;
  }

  @Override
  public DesiredCapabilities getDefaultOptions() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (configTestData.testNetowk.equalsIgnoreCase("cloud")) {
            if(Generic.readConfigProp("mobile.cloud.env").equalsIgnoreCase("public")){
                capabilities.setCapability("user", Generic.readConfigProp("mobile.cloud.public.userid"));
                capabilities.setCapability("password", Generic.readConfigProp("mobile.cloud.public.password"));
            } else{
                capabilities.setCapability("user", Generic.readConfigProp("mobile.cloud.private.userid"));
                capabilities.setCapability("password", Generic.readConfigProp("mobile.cloud.private.password"));
            }

        }

        if (configTestData.mb_platformName.equalsIgnoreCase("Android")) {
            //capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.UDID, configTestData.mb_udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configTestData.mb_deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configTestData.mb_platformVersion);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configTestData.mb_platformName);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 9999);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        } else if (configTestData.mb_platformName.equalsIgnoreCase("ios")) {
            capabilities.setCapability(MobileCapabilityType.UDID, configTestData.mb_udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configTestData.mb_deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configTestData.mb_platformVersion);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configTestData.mb_platformName);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 9999);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        }
        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options)
      throws MalformedURLException {
      if (StringUtils.equalsIgnoreCase(configTestData.testNetowk, "cloud")) {
          if (StringUtils.equalsIgnoreCase(Generic.readConfigProp("mobile.cloud.env"), "public")) {
              configTestData.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Generic.readConfigProp("mobile.cloud.public.url")), getOptions(options)));
          } else {
              configTestData.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Generic.readConfigProp("mobile.cloud.private.url")), getOptions(options)));
          }
      } else {
          configTestData.mobileWebDriver = setWebDriverManage(new RemoteWebDriver(new URL(Generic.readConfigProp("localGridHub")), getOptions(options)));

      }
      return configTestData.mobileWebDriver;
  }

}
