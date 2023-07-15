package com.mentoring_1;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.API_Calls.GET;

public class InterviewTask {
    private String expectedCapital = "Kyiv";
    private String actualCapital = "";


    @Test
    public void validateCapital() {
        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200).extract().response();
        List<Map<String, Object>> parsed = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        List<String> capital = new ArrayList<>();
        for (int i = 0; i < parsed.size(); i++) {
            Map<String, Object> outerMap = parsed.get(i);
            Map<String, Object> innerMap = (Map<String, Object>) outerMap.get("name");
            if (innerMap.get("common").equals("Ukraine")) {
                capital = (List<String>) outerMap.get("capital");
                actualCapital = capital.get(0);
                break;

            }


        }

        System.out.println(actualCapital);


    }
    @Test
    public void validateBreakingBadQuotes(){
        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes";
        Response re = GET("/10");

        List<Map<String,Object>>parsed = re.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for(int i=0;i< parsed.size();i++){
            Map<String,Object>map = parsed.get(i);
            if(map.get("author").equals("Jesse Pinkman")) {
                System.out.println(map.get("quote"));
               // System.out.println(map.get("author"));
            }
        }



    }


}
