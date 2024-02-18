package com.bdd.pet.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;
import org.testng.annotations.AfterMethod;
import static com.bdd.pet.support.AssertHelper.softAssert;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = { "com.bdd.pet.stepDefinitions" },
        plugin = { "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:src/main/java/support/cucumber.json"}

)

public class RunnerTest {
    @AfterMethod(alwaysRun = true)
    public void assertAll(){
        softAssert.assertAll();

    }

}
