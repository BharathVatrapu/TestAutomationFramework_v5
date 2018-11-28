package com.testProject.testsuites.web.demo.signin;


import com.testProject.base.TestBase;
import org.testng.annotations.Test;

public class VerifySignIn extends TestBase {

    @Test(groups={"SignIn"})
    public void VerifySignIn() throws Exception {
       //Variable Declarion

        //Step 1:
        verify(signInPage.gotoSignInPage(),"SignIn page is displayed");
        //Step 2:
        verify (signInPage.userLogin(),"User Successful signed in");

    }

}
