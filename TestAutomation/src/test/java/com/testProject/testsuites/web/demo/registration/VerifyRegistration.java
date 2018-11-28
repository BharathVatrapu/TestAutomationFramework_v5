package com.testProject.testsuites.web.demo.registration;


import com.testProject.base.TestBase;
import com.testProject.constants.dataKey;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VerifyRegistration extends TestBase {

    public String[] reg = new String[7];
    @Test(groups={"Registration"})
    public void VerifySignIn() throws Exception {
       //Variable Declarion
        //Registration data
        reg[0]=configTestData.read(dataKey.FirstName.toString());
        reg[1]=configTestData.read(dataKey.LastName.toString());
        reg[2]=configTestData.read(dataKey.CCNumber.toString());
        reg[3]="testauto_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "@example.com";
        reg[4]=configTestData.read(dataKey.Password.toString());
        reg[5]=configTestData.read(dataKey.ZIPCode.toString());
        reg[6]=configTestData.read(dataKey.StoreAddress.toString());



        //Step 2:
        verify(registrationPage.gotoRegistraionPage(),"Registraiton page is displayed");
        //Step 2:
        verify (registrationPage.userRegister(reg),"User is register Successful");

    }

}
