package com.bdd.pet.support;

import com.bdd.pet.api.PetEnums;
import com.bdd.pet.data.pojo.APIResponse;
import com.bdd.pet.data.pojo.Pet;
import com.google.gson.Gson;
import io.restassured.response.Response;
import java.util.Arrays;
import org.testng.asserts.SoftAssert;


public class AssertHelper {
    public static SoftAssert softAssert = new SoftAssert();

    /**
     * Assert Response code
     * @param response
     * @param expectedCode
     */
    public void assertRespCode(Response response, PetEnums.APICodes expectedCode) {
        softAssert.assertTrue(
                (response.statusCode() == expectedCode.code), "Current Test is designed for Status code: " + expectedCode.code + " Actual: " + response.statusCode());
    }

    /**
     * Assert code or message in response
     * @param response
     * @param expectedCode
     * @throws Exception
     */
    public void assertRespCodeOrMessage(Response response, PetEnums.APICodes expectedCode) throws Exception {

        APIResponse apiResponse = null;
        if (response.statusCode() == PetEnums.APICodes.CODE_200.code || response.statusCode() == PetEnums.APICodes.CODE_201.code) {
            softAssert.assertFalse(response.statusCode() == PetEnums.APICodes.CODE_200.code || response.statusCode() == PetEnums.APICodes.CODE_201.code, "Current Test is designed for Status code:" + expectedCode.code +": Actual " + response.statusCode() );
        } else {
            softAssert.assertTrue(
                    (response.statusCode() == expectedCode.code), "Current Test is designed for Status code:" + expectedCode.code +": Actual " + response.statusCode() );
            if (response.asString().length() > 1) {
                apiResponse = response.as(APIResponse.class);
                if (apiResponse.getMessage() != null)
                    softAssert.assertTrue(apiResponse.getMessage().contains(expectedCode.msg.toString()), "Response code message verified");
            }
        }
    }


    /**
     * Assert Pet Status
     * @param response
     * @param value
     */
    public void assertPetStatus(Response response, String value) {
        Pet[] expectedResponse = response.as(Pet[].class);
        boolean allSameStatus = Arrays.asList(expectedResponse).stream().allMatch(x -> x.getStatus().equals(value));
        softAssert.assertTrue(allSameStatus, "All pets have same status for status: " + value);
    }


    /**
     * Assert pet image upload
     * @param response
     * @param expectedCode
     * @param description
     */
    public void assertPetImage(Response response, PetEnums.APICodes expectedCode, String description) {
        APIResponse apiResponse = response.as(APIResponse.class);
        softAssert.assertTrue((response.statusCode() == expectedCode.code), "Response code  verified");
        softAssert.assertTrue(apiResponse.getMessage().contains(description), "Response message verified");
        softAssert.assertTrue(apiResponse.getMessage().contains("uploaded"), "Response upload confirmation verified");

    }


    /**
     * Assert response Content
     * @param model
     * @param response
     */
    public void assertResponseContent(Object model, Response response) {
        Pet petResponse = response.as(Pet.class);
        boolean result = new Gson().toJson(model).equals(new Gson().toJson(response.as(Pet.class)));
        softAssert.assertTrue(result, "Response body verified for id: " + petResponse.getId());
    }
}
