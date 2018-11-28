package com.testProject.base;

import com.aventstack.extentreports.Status;
import com.testProject.pagemodel.mobileApp.HomeScreen;
import com.testProject.pagemodel.web.RegistrationPage;
import com.testProject.pagemodel.web.SignInPage;
import com.testautomation.framework.base.ConfigBase;
import com.testautomation.framework.base.ConfigTestData;
import com.testautomation.framework.utils.DriverGeneric;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ResourceBundle;

public class TestBase extends ConfigBase {
    ResourceBundle rb =null;

    // Web page object model
    public SignInPage signInPage  = null;
    public RegistrationPage registrationPage  = null;
    // Mobile screen object model
    public HomeScreen homeScreen = null;


    @BeforeMethod(alwaysRun = true)
    public void initTestMethods(){
        initilizeClasses();
    }

    public void verify(boolean condition, String message){
        configTestData.stepNo = configTestData.stepNo+1;
        if(condition){
            extentManager.addExecutionStep(Status.PASS,message);
        } else{
            message=message.replaceAll(" is "," is not ");
            extentManager.addExecutionStep(Status.FAIL,message);
            configTestData.finalTestCaseStatus = Status.FAIL;
        }
    }
    public void verify(boolean condition, String trueMsg,String falseMsg){
        configTestData.stepNo = configTestData.stepNo+1;
        if(condition){
            extentManager.addExecutionStep(Status.PASS,trueMsg);
        } else{
            extentManager.addExecutionStep(Status.FAIL,falseMsg);
            configTestData.finalTestCaseStatus = Status.FAIL;
        }
    }

   public void Assert(boolean condition,String trueMsg,String falseMsg){
       configTestData.stepNo = configTestData.stepNo+1;

       if(condition){
           extentManager.addExecutionStep(Status.PASS,trueMsg);
       } else{
           extentManager.addExecutionStep(Status.FAIL,falseMsg);
           configTestData.finalTestCaseStatus = Status.FAIL;
       }
       Assert.assertTrue(condition,falseMsg);
   }

    public void initilizeClasses(){
        signInPage = new SignInPage(configTestData);
        registrationPage = new RegistrationPage(configTestData);

        homeScreen = new HomeScreen(configTestData);
    }


}
