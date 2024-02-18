package com.bdd.pet.context;

import com.bdd.pet.data.pojo.Pet;
import io.restassured.response.Response;
import lombok.Data;

@Data
public class TestContext {

    public static Pet currentPet;
    public static Response response;

 }