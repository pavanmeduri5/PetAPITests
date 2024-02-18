package com.bdd.pet.stepDefinitions;

import com.bdd.pet.api.PetAPI;
import com.bdd.pet.api.PetEnums;
import com.bdd.pet.context.TestContext;
import com.bdd.pet.data.factory.PetFactory;
import com.bdd.pet.data.pojo.Pet;
import com.bdd.pet.support.AssertHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PetStepDef {
    TestContext context = new TestContext();
    PetFactory petFactory = new PetFactory();
    PetAPI petAPI = new PetAPI();
     Pet pet;

    @When("user initiates rest service to add new pet")
    public void user_initiates_rest_service_to_add_new_pet() throws Exception{
        pet = petFactory.newPet();
        context.response=petAPI.addNewPetAsJson(pet);
        context.currentPet=pet;
    }

    @Then("verify response is successful and returns valid pet data")
    public void verifyResponseIsSuccessfulAndReturnsValidPetData() throws Exception {
        AssertHelper assertHelper = new AssertHelper();
        assertHelper.assertRespCode(context.response, PetEnums.APICodes.CODE_200);
        assertHelper.assertResponseContent(context.currentPet,context.response);
    }

    @When("user adds same pet again")
    public void user_add_same_pet() throws Exception {
        context.response=petAPI.addNewPetAsJson(pet);
   }

    @When("user adds pet with invalid id")
    public void user_adds_invalid_pet() throws Exception{
        String pet = petFactory.petWithInvalidId();
        context.response= petAPI.addNewPet(pet);
    }

    @When("user adds new pet without mandatory fields")
    public void addNewPetWithoutMandatoryFields()  throws Exception{
        Pet dog= petFactory.petWithoutMandatoryFields();
        context.response= petAPI.addNewPetAsJson(dog);
    }

    @When("user adds pet with multiple tags")
    public void userAddsPetWithMultipleTags() throws Exception {
        Pet dog= petFactory.petWithMultipleTags();
        context.response= petAPI.addNewPetAsJson(dog);
        context.currentPet=dog;
    }

    @When("user performs search of a valid pet")
    public void userPerformsSearchOfAValidPet()  throws Exception{
           context.response= petAPI.getPetById(this. context.currentPet.getId());
       }

    @When("user performs search of non existing pet")
    public void userPerformsSearchOfNonExistingPet()  throws Exception{
       Pet pet = petFactory.petWithNonExistingId();
        context.response= petAPI.getPetById(pet.getId());
    }

    @When("user performs search of pet with invalid id")
    public void userPerformsSearchOfPetWithoutId()  throws Exception{
        context.response= petAPI.getPetWithInvalidId();
    }

    @When("user performs update of an existing pet")
    public void userPerformsUpdateOfAnExistingPet()  throws Exception{
        Pet newPet = petFactory.newPet();
        newPet.setId( context.currentPet.getId());
        context.response=petAPI.addNewPetAsJson(newPet);
       context.currentPet=newPet;
    }

    @When("user performs update of non existing pet")
    public void userPerformsUpdateOfNonExistingPet() throws Exception{

        Pet pet = petFactory.petWithNonExistingId();
        context.response= petAPI.updateExistingPet(pet);
    }

    @When("user performs update with invalid id")
    public void userPerformsUpdateWithInvalidId() throws Exception {
        context.response= petAPI.updatePetWithInvalidId();
    }

    @When("user performs update with invalid request method")
    public void userPerformsUpdateWithInvalidRequestMethod() throws Exception {
        context.response= petAPI.updatePetWithInvalidRequestMethod();
    }


    @When("user performs delete of an existing pet")
    public void userPerformsDeleteOfAnExistingPet() throws Exception {
        context.response= petAPI.deleteExistingPet(context.currentPet);
    }

    @When("user performs delete of non existing pet")
    public void userPerformsDeleteOfNonExistingPet() throws Exception {
         context.response= petAPI.deleteExistingPet( context.currentPet);
    }

    @When("user performs delete with invalid id")
    public void userPerformsDeleteWithInvalidId()  throws Exception{
        this.context.response= petAPI.deletePetWithInvalidId();
    }

    @When("user performs update of form for an existing pet")
    public void userPerformsUpdateOfFormForAnExistingPet() throws Exception{
        Pet existingPet = context.currentPet;
        Pet updatePet = petFactory.newPet();
        context.response=petAPI.addNewPetAsJson(pet);
        context.currentPet=updatePet;
        this.context.response= petAPI.updatePetFormInStore(existingPet,updatePet);
    }

    @When("user performs update of form for non existing pet")
    public void userPerformsUpdateOfFormForNonExistingPet() throws Exception{
        Pet pet = petFactory.petWithNonExistingId();
        this.context.response= petAPI.updatePetFormInStore(pet);
    }

    @When("user performs update of form invalid request method")
    public void userPerformsUpdateOfFormInvalidRequestMethod() throws Exception{
        Pet pet = petFactory.petWithNonExistingId();
        this.context.response= petAPI.updatePetFormWithInvalidRequest(pet);
    }


    @When("user performs search of pets with different {string}")
    public void userPerformsSearchOfPetsWithDifferent(String status) throws Exception{
        this.context.response= petAPI.findPetByStatus(status);
    }
}
