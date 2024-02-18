package com.bdd.pet.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.asserts.SoftAssert;


import static com.bdd.pet.support.AssertHelper.softAssert;

public class Hooks {

    @Before
    public void beforeScenario(){
        softAssert =  new SoftAssert();
    }

    @After
    public void afterAll(){
        softAssert.assertAll();
    }
}
