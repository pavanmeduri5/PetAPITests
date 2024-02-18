package com.bdd.pet.stepDefinitions;

import com.bdd.pet.api.PetAPI;
import com.bdd.pet.api.PetEnums;
import com.bdd.pet.context.TestContext;
import com.bdd.pet.data.factory.PetFactory;
import com.bdd.pet.data.pojo.Pet;
import com.bdd.pet.support.AssertHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PetImageStepDef {

    private TestContext context;
    PetFactory petFactory = new PetFactory();
    PetAPI petAPI = new PetAPI();
    Pet pet;
    String description;

    public PetImageStepDef(TestContext context){
        this.context= context;
    }

    @Given("create a new pet")
    public void createANewPet() {
        pet = petFactory.newPet();
        petAPI.addNewPetAsJson(pet);

    }

    @And("user deletes the pet")
    public void userDeletesThePet() {
        petAPI.deleteExistingPet(pet);
    }

    @Then("verify response indicates upload image is success")
    public void verifyResponseIndicatesUploadImageIsSuccess() throws Exception {
        AssertHelper assertHelper = new AssertHelper();
        assertHelper.assertPetImage(context.response, PetEnums.APICodes.CODE_200,description);
    }

    @When("user uploads {string} with {string} description")
    public void userUploadsWithDescription(String petImage, String description) throws Exception{
        this.description=description;
        this.context.response= petAPI.uploadImage(pet,petImage,description);
    }
}
