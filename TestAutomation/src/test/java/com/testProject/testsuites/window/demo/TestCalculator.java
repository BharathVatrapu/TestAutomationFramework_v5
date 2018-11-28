package com.testProject.testsuites.window.demo;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.testProject.base.TestBase;
import com.testautomation.framework.base.ConfigTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public class TestCalculator  extends TestBase {
    static WiniumDriver driver = null;
    static WiniumDriverService service = null;
    static DesktopOptions options = null;
//    @BeforeClass(alwaysRun = true)
//    public void setTestData() {
//        configTestData.TEST_DATA_FILE_PATH = MessageFormat.format(Generic.readConfigProp("testdata.path"),System.getProperty("user.dir"),configTestData.testEnvironment.toLowerCase(), moduleConstants.SignIn, ProjectConstants.banner+".json");
//
//    }


//    @Test
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        options = new DesktopOptions(); //Instantiate Winium Desktop Options
        options.setApplicationPath("C:\\Windows\\System32\\calc.exe");
        File driverPath = new File("C:\\selenium-hub-node-configs\\DRIVERS\\Winium.Desktop.Driver.exe");
        System.setProperty("webdriver.winium.desktop.driver","C:\\selenium-hub-node-configs\\DRIVERS\\Winium.Desktop.Driver.exe");
        service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true)
                .withSilent(false).buildDesktopService();
        try {
            service.start();
        } catch (IOException e) {
            System.out.println("Exception while starting WINIUM service");
            e.printStackTrace();
        }

        driver = new WiniumDriver(service,options);
        Thread.sleep(1000);
        driver.findElement(By.name("Seven")).click();

        driver.findElement(By.name("Plus")).click();

        driver.findElement(By.name("Eight")).click();

        driver.findElement(By.name("Equals")).click();

        Thread.sleep(5000);

        String output = driver.findElement(By.id("CalculatorResults")).getAttribute("Name");

        System.out.println("Result after addition is: "+output);
        driver.close();
        service.stop();
    }
}
