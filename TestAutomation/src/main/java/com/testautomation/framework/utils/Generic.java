package com.testautomation.framework.utils;

import com.testautomation.framework.base.ConfigTestData;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.Platform;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Generic {

    static ResourceBundle rbConfig =null;

    private static Platform platform = null;
    ConfigTestData configTestData=null;
    JsonUtils jsonUtils=null;

    public Generic(ConfigTestData configTestData){
        this.configTestData=configTestData;
        jsonUtils = new JsonUtils();
    }

    /*	To get the host OS name */
    public static Platform getCurretnPlatform(){
        if(platform == null){
            String osname = System.getProperty("os.name").toLowerCase();
            if(osname.contains("win")){
                platform = Platform.WINDOWS;
            } else if(osname.contains("nix") || osname.contains("nux") || osname.contains("aix")){
                platform=Platform.LINUX;
            } else if(osname.contains("mac")){
                platform=Platform.MAC;
            }
        }

        return platform;
    }

    /*	To get the ComputerName */
    public static String getComputerName() throws Exception{
        String hostname = "Unknown";
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            ex.fillInStackTrace();
            System.out.println("Hostname can not be resolved");
        }
        return (hostname);
    }

    public boolean testExecute(){
        String strExcute;
        strExcute = jsonUtils.getValue(jsonUtils.parseStringToJsonObject(configTestData.TEST_DATA_FILE_PATH),configTestData.testMethodName,"execute");
        return getBoolean(strExcute);
    }

    public boolean getBoolean(String str){

        if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("on")) {
             return true;
         } else {
             return false;
         }

    }

    public String readFile(String filepath) throws IOException{
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line!=null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } finally {
            bufferedReader.close();
        }
    }

    public String getSuiteXmlGroupName(String[] groupnames){
        String groupName=null;
        for(int i=0;i<=groupnames.length-1;i++){
            groupName = groupnames[0];
        }
        return groupName;

    }

    public void readMobileCapabilities(String structHead){
        JsonUtils jsonUtils =new JsonUtils();
        JSONObject jsonObject = null;
        String mobileCloudfilepath = null;
        System.out.println("network::"+configTestData.testNetowk);
        try {
            if(StringUtils.equalsIgnoreCase(configTestData.testNetowk,"cloud")){
                mobileCloudfilepath = configTestData.workDir+readConfigProp("mobile.cloud.devices.capabilities.file");

            } else{
                mobileCloudfilepath = configTestData.workDir+readConfigProp("mobile.local.devices.capabilities.file");
            }
            System.out.println("mobile path:"+mobileCloudfilepath);
            jsonObject = jsonUtils.parseStringToJsonObject(mobileCloudfilepath);
            configTestData.mb_udid = jsonUtils.getValue(jsonObject,structHead,"udid");
            configTestData.mb_deviceName= jsonUtils.getValue(jsonObject,structHead,"deviceName");
            configTestData.mb_platformName = jsonUtils.getValue(jsonObject,structHead,"platformName");
            configTestData.mb_platformVersion = jsonUtils.getValue(jsonObject,structHead,"platformVersion");
            configTestData.mb_manufacturer = jsonUtils.getValue(jsonObject,structHead,"manufacturer");
            configTestData.mb_appActivity = jsonUtils.getValue(jsonObject,structHead,"appActivity");

            System.out.println(configTestData.mb_deviceName+"::::device mobile");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void almIntialization(){
        try{
            ALMIntegration almIntegration = new ALMIntegration(readALMargs());
            almIntegration.initializeConnection();
        } catch (Exception e){
            e.printStackTrace();
        }

    }



    public String[] readALMargs(){
        JsonUtils jsonUtils=new JsonUtils();
        String[] args=null;
        try{
            JSONObject jsonObject = jsonUtils.parseStringToJsonObject(ConfigTestData.workDir+readConfigProp("alm.config.file"));
            args[0]=jsonUtils.getValue(jsonObject,"ALM","almURL");
            args[1]=jsonUtils.getValue(jsonObject,"ALM","almUserName");
            args[2]=jsonUtils.getValue(jsonObject,"ALM","almPassword");
            args[3]=jsonUtils.getValue(jsonObject,"ALM","almDomain");
            args[4]=jsonUtils.getValue(jsonObject,"ALM","almProject");
            args[5]=jsonUtils.getValue(jsonObject,"ALM","almTestSetPath");
            args[6]=jsonUtils.getValue(jsonObject,"ALM","almTestSetName");
            args[7]=jsonUtils.getValue(jsonObject,"ALM","almTestSetID");
        } catch (Exception e){
            e.printStackTrace();
        }
        return args;
    }

    /**  Method is used for get property value from framework properites **/
//    public static String getConfig(String propKey){
//        String propValue=null;
//        Properties prop = new Properties();
//        InputStream input = null;
//        try{
//            input = new FileInputStream(ConfigTestData.CONFIG_PROP_FILE_PATH);
//            // load a properties file
//            prop.load(input);
//            // get the property value
//            propValue = prop.getProperty(propKey);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return propValue;
//    }

    public static String getTestDataPath(String module){
       return MessageFormat.format(readConfigProp("testdata.path"),System.getProperty("user.dir"),module);
    }

    // Load the Config properties file from test source
    public static void loadConfigProp() throws Exception{
        File file = new File(ConfigTestData.CONFIG_PROP_FILE_PATH);
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        rbConfig = ResourceBundle.getBundle("config", Locale.getDefault(), loader);
    }
    // Read the Config properties file from test source
    public static String readConfigProp(String key){
        try {
            loadConfigProp();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rbConfig.getString(key);
    }



}
