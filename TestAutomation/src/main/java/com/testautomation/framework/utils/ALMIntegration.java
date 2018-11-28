package com.testautomation.framework.utils;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.exceptions.ALMServiceException;
import org.apache.commons.lang3.StringUtils;
import org.testng.asserts.SoftAssert;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ALMIntegration  {

    public String scriptName;
    public int stepNo;
    public SoftAssert softAssert = null;
    private ALMServiceWrapper almWrapper = null;
    private String almURL;
    private String almUserName;
    private String almPassword;
    private String almDomain;
    private String almProject;
    private String almTestSetPath;
    private String almTestSetName;
    private String almTestSetID;
    private String almTestCaseName;

    private ITestCaseRun objRun = null;
    private ITestCase objTestCase = null;
    private String testStepExecutionResults[][] = new String[1][5];



    public ALMIntegration(String args[]) {
        almURL = args[0];
        almUserName = args[1];
        almPassword = args[2];
        almDomain = args[3];
        almProject = args[4];
        almTestSetPath = args[5];
        almTestSetName = args[6];
        almTestSetID = args[7];
        almWrapper = new ALMServiceWrapper(almURL);
    }

    public static Object resizeArray(Object oldArray, int newSize) {
        int oldSize = java.lang.reflect.Array.getLength(oldArray);
        Class elementType = oldArray.getClass().getComponentType();
        Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength > 0) {
            System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        }
        return newArray;
    }

    /**
     * To connect ALM using jacob and ALM TDConnect
     *
     * @throws ALMServiceException
     */
    public void initializeConnection() throws ALMServiceException {
//        if(StringUtils.equalsIgnoreCase(almIntegration,"ON")){
            boolean isConnected = almWrapper.connect(almUserName, almPassword, almDomain, almProject);
            if (isConnected) {
                System.out.println("Connected to ALM!");
            } else {
                System.out.println("Unable to connect to ALM.");
            }
//        }
    }


    /**
     * To close the ALM Connection
     *
     * @throws ALMServiceException
     */
    public void terminateTestExecution() throws ALMServiceException {
        // updateTestCaseResult();
        terminateConnection();
        //        Assert.fail("Test execution terminated!");
        //        throw new SkipException("Test execution terminated!");
    }

    public void terminateConnection() {
        almWrapper.close();
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}
