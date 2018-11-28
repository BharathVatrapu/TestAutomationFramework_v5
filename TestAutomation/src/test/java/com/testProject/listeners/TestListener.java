package com.testProject.listeners;

import com.aventstack.extentreports.Status;
import com.testProject.base.TestBase;
import com.testautomation.framework.base.ConfigTestData;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


//**********************************************************************************************************
//Description: This is the main listener class.
//**********************************************************************************************************
public class TestListener extends TestBase implements ITestListener {


//    public TestListener(ConfigTestData configTestData) {
//        super(configTestData);
//    }

    @Override
    public synchronized void onStart(ITestContext context) {

    }

    @Override
    public synchronized void onFinish(ITestContext context) {

    }

    @Override
    public synchronized void onTestStart(ITestResult result) {


    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {

    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
       // extentManager.addException(Status.FAIL,result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
//        extentManager.addException(Status.SKIP,result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
