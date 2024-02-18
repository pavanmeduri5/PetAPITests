package com.bdd.pet.api;

import com.bdd.pet.data.pojo.Pet;
import com.bdd.pet.support.APIMethod;
import com.bdd.pet.support.PetEndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

import static com.bdd.pet.support.PetEndPoints.*;
import static io.restassured.RestAssured.given;

public class PetAPI extends BaseAPI{
    private static final String APPLICATION_JSON = "application/json";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String FORM_URLENCODED = "application/x-www-form-urlencoded";

    /**
     * Add a new Pet with Json data
     * @param pet
     * @return
     */
    public Response addNewPetAsJson(Object pet){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        return executeAPIAsJson(req, APIMethod.POST,PetEndPoints.ADD_PET,pet);
    }

    /**
     * Add a new pet
     * @param pet
     * @return
     */
    public Response addNewPet(String pet){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        return executeAPI(req, APIMethod.POST,PetEndPoints.ADD_PET,pet);
    }

    /**
     * Get pet by Id
     * @param petId
     * @return
     */
    public  Response getPetById(Long petId){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(petId != null){
            req.pathParam("petid",petId);
        }
       return executeAPI(req, APIMethod.GET,PET_BY_ID);
    }

    /**
     * Get pet with invalid id
     * @return
     */
    public  Response getPetWithInvalidId(){
        RequestSpecification req = given().accept(APPLICATION_JSON);

            req.pathParam("petid","a");

        return executeAPI(req, APIMethod.GET,PET_BY_ID);
    }

    /**
     * Get pet by status
     * @param petStatus
     * @return
     */
    public  Response GetPetByStatus(String petStatus){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(petStatus != null){
            req.queryParam("status",petStatus);
        }
        return executeAPI(req, APIMethod.GET,GET_PET_BY_STATUS);
    }

    /**
     * Update exising pet details
     * @param pet
     * @return
     */
     public  Response updateExistingPet(Pet pet){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        return executeAPIAsJson(req, APIMethod.PUT,UPDATE_PET,pet);
    }

    /**
     * Update pet with invalid Id
     * @return
     */
    public  Response updatePetWithInvalidId(){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        return executeAPIAsJson(req, APIMethod.PUT,UPDATE_PET,"s");
    }

    /**
     * Update Pet by invalid request
     * @return
     */
    public  Response updatePetWithInvalidRequestMethod(){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        return executeAPIAsJson(req, APIMethod.PATCH,UPDATE_PET,"s");
    }


    /**
     * Delete existing pet
     * @param pet
     * @return
     */
    public  Response deleteExistingPet(Pet pet){
        RequestSpecification req = given().contentType(APPLICATION_JSON).accept(APPLICATION_JSON);
        req.header("api_key",api_key);
        req.pathParam("petid",pet.getId());
        return executeAPIAsJson(req, APIMethod.DELETE,DELETE_PET,pet);
    }

    /**
     * Delete pet with invalid ID
     * @return
     */
    public  Response deletePetWithInvalidId(){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        req.header("api_key",api_key);
        req.pathParam("petid","a");
        return executeAPIAsJson(req, APIMethod.DELETE,DELETE_PET,"s");
    }

    /**
     * Upload pet form in store
     * @param existingPet
     * @param updatePet
     * @return
     */
    public  Response updatePetFormInStore(Pet existingPet, Pet updatePet) {
        RequestSpecification req = given().contentType(FORM_URLENCODED).accept(APPLICATION_JSON);
        if (existingPet.getId() > 0) {
            req.formParam("name", updatePet.getName());
            req.formParam("status", updatePet.getStatus());
            req.pathParam("petid", existingPet.getId());
        }
        return executeAPI(req, APIMethod.POST, PET_BY_ID);
    }

    /**
     * Upload pet form in store
     * @param existingPet
     * @return
     */
    public  Response updatePetFormInStore(Pet existingPet) {
        RequestSpecification req = given().contentType(FORM_URLENCODED).accept(APPLICATION_JSON);
        if (existingPet.getId() > 0) {
            req.formParam("name", existingPet.getName());
            req.formParam("status", existingPet.getStatus());
            req.pathParam("petid", existingPet.getId());
        }
        return executeAPI(req, APIMethod.POST, PET_BY_ID);
    }

    /**
     * Upload pet form with invalid request
     * @param pet
     * @return
     */
    public  Response updatePetFormWithInvalidRequest(Pet pet) {
        RequestSpecification req = given().contentType(FORM_URLENCODED).accept(APPLICATION_JSON);
        if (pet.getId() > 0) {
            req.formParam("name", pet.getName());
            req.formParam("status", pet.getStatus());
            req.pathParam("petid", pet.getId());
        }
        return executeAPI(req, APIMethod.PUT, PET_BY_ID);
    }


    /**
     * Find Pet by status
     * @param petStatus
     * @return
     */
    public  Response findPetByStatus(String petStatus){
        RequestSpecification req = given().accept(APPLICATION_JSON);
        if(petStatus != null){
            req.queryParam("status",petStatus);
        }
        return executeAPI(req, APIMethod.GET,GET_PET_BY_STATUS);
    }

    /**
     * Upload image
     * @param pet
     * @param additionalData
     * @return
     */
 public  Response uploadImage(Pet pet,String petImage, String additionalData){

        RequestSpecification req = given().contentType(MULTIPART_FORM_DATA).accept(APPLICATION_JSON);
        if (pet.getId() != null){
            req.pathParam("petId",pet.getId());
        }
        if (additionalData != null){
            req.formParam("additionalMetadata",additionalData);
        }
        if (petImage != null){
            req.multiPart("file", new File(petImage), "image/jpeg");
        }
       return executeAPI(req, APIMethod.POST,UPLOAD_PET_IMAGE);
    }

}
