package com.testautomation.framework.driverconfig.drivers;


import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.driverconfig.BaseMobileDriver;
import com.testautomation.framework.utils.Generic;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Android extends BaseMobileDriver<AppiumDriver, DesiredCapabilities, Android> {
    private ConfigTestData configTestData=null;
    public Android(ConfigTestData configTestData) {
        this.configTestData = configTestData;
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
        capabilities.setCapability(MobileCapabilityType.UDID, configTestData.mb_udid);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configTestData.mb_deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configTestData.mb_platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configTestData.mb_platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configTestData.testDeviceName);
        capabilities.setCapability("appPackage", configTestData.testAppName);
        capabilities.setCapability("appActivity", configTestData.mb_appActivity);
        capabilities.setCapability("newCommandTimeout", 9999);
        return capabilities;
    }

  protected DesiredCapabilities getOptions(DesiredCapabilities capabilities) {
    return capabilities == null ? getDefaultOptions() : capabilities;
  }

  @Override
  public AppiumDriver buildMobileDriver(DesiredCapabilities options)
      throws MalformedURLException {
          if (StringUtils.equalsIgnoreCase(configTestData.testNetowk, "cloud")) {
              if (StringUtils.equalsIgnoreCase(Generic.readConfigProp("mobile.cloud.env"), "public")) {
                  configTestData.androidDriver = setMobileAppDriverManage(new AndroidDriver(new URL(Generic.readConfigProp("mobile.cloud.public.url")), getOptions(options)));
              } else {
                  configTestData.androidDriver = setMobileAppDriverManage(new AndroidDriver(new URL(Generic.readConfigProp("mobile.cloud.private.url")), getOptions(options)));
              }
          } else {
              configTestData.androidDriver = setMobileAppDriverManage(new AndroidDriver(new URL(Generic.readConfigProp("localGridHub")), getOptions(options)));

          }
      return configTestData.androidDriver;
  }

}
