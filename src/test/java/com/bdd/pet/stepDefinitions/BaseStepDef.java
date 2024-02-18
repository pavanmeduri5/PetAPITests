package com.bdd.pet.stepDefinitions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bdd.pet.api.PetEnums;
import com.bdd.pet.context.TestContext;
import com.bdd.pet.support.AssertHelper;
import io.cucumber.java.en.Then;


public class BaseStepDef {


    AssertHelper assertHelper = new AssertHelper();


    @Then("verify response throws error for adding pet with invalid id")
    public void verifyResponseThrowsErrorForAddingPetWithInvalidId() throws Exception {
        assertHelper.assertRespCode(TestContext.response, PetEnums.APICodes.CODE_405);
    }

    @Then("verify response throws error for not providing mandatory fields")
    public void verifyResponseThrowsErrorForNotProvidingMandatoryFields() throws Exception {
        assertHelper.assertRespCodeOrMessage(TestContext.response, PetEnums.APICodes.CODE_400);
    }

    @Then("verify response throws error for non existing pet")
    public void verifyResponseThrowsErrorForNonExistingPet() throws Exception{
        assertHelper.assertRespCodeOrMessage(TestContext.response, PetEnums.APICodes.CODE_404);

    }

    @Then("verify response throws error for pet with invalid id")
    public void verifyResponseThrowsErrorForPetWithoutId() throws Exception {
        assertHelper.assertRespCodeOrMessage(TestContext.response, PetEnums.APICodes.CODE_400_NFE);
    }

    @Then("verify response throws error with invalid request")
    public void verifyResponseThrowsErrorWithInvalidRequest() throws Exception{
        assertHelper.assertRespCodeOrMessage(TestContext.response, PetEnums.APICodes.CODE_405);
    }

    @Then("verify response is successful with same {string} pets")
    public void verifyResponseIsSuccessfulWithSamePets(String status) throws Exception {
        assertHelper.assertPetStatus(TestContext.response, status);
    }

    @Then("verify response is successful")
    public void verifyResponseIsSuccessful() {
        assertHelper.assertRespCode(TestContext.response, PetEnums.APICodes.CODE_200);
    }
}
