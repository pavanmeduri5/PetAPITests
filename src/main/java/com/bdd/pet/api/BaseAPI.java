package com.bdd.pet.api;

import com.bdd.pet.support.APIMethod;
import com.bdd.pet.support.ConfigReader;

import io.restassured.parsing.Parser;
import org.json.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseAPI {
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String base_url;
    protected String api_key;

    BaseAPI() {
        base_url=ConfigReader.getInstance().getString("apibaseuri");
        api_key=ConfigReader.getInstance().getString("api_key");
        RestAssured.defaultParser = Parser.JSON;
    }



    /**
     * Convert POJO to JSON
     */
    protected JSONObject createJSONPayload(Object pojo) {
        return new JSONObject(pojo);
    }


    public Response executeAPIAsJson(RequestSpecification request, APIMethod method, String resource,Object pojo) {
        if (pojo != null) {
            String payload = createJSONPayload(pojo).toString();
            request.body(payload);}

            return executeAPI(request,method,resource);
        }

    public Response executeAPI(RequestSpecification request, APIMethod method, String resource,Object body) {
        if (body != null) {
           request.body(body);}
            return executeAPI(request,method,resource);
    }

    public Response executeAPI(RequestSpecification request, APIMethod method, String resource) {
        Response response;
        String url= base_url.concat(resource);

        switch (method) {
            case POST:
                  response = request.post(url);
                break;
            case DELETE:

                    response = request.delete(url);
                    break;
            case PUT:

                    response = request.put(url);
                    break;
            case GET:
            default:
                    response = request.get(url);

                break;
        }
        return response;
    }

}

