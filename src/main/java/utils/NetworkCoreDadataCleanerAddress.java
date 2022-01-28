package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_CLEANER_ADDRESS;
import static constants.Constants.Path.DADATA_CLEANER_PATH;
import static constants.Constants.ServerName.DADATA_CLEANER_SERVER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class NetworkCoreDadataCleanerAddress {

    static Response responseDadata;

    public static RequestSpecBuilder reqDadataCleanerAddressSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_ADDRESS)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    static RequestSpecification reqDadataCleanerAddressSpec=reqDadataCleanerAddressSpecBuilder.build();


    public static Response sendReqAndGetRespDadataCleanerAddress(String reqBody) {
        responseDadata = given().
                spec(reqDadataCleanerAddressSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }

    public static Response sendReqAndGetRespDadataCleanerAddress(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerAddressSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }

    public static Response sendReqAndGetRespDadataCleanerAddressSchema(String reqBody) {
        responseDadata = given().
                spec(reqDadataCleanerAddressSpec).
                body(reqBody).
                when().post();
        responseDadata.then().body(matchesJsonSchemaInClasspath("dadataCleanerAddressSchema.json")).log().body();
        return responseDadata;
    }

    public static Response sendReqAndGetRespDadataCleanerAddress_Get(String reqBody) {
        responseDadata = given().
                spec(reqDadataCleanerAddressSpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body().assertThat().statusCode(405);
        return responseDadata;
    }



    public static RequestSpecBuilder reqDadataCleanerAddressSpecBuilder_NoHead = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_ADDRESS);

    static RequestSpecification reqDadataCleanerAddressSpec_NoHead=reqDadataCleanerAddressSpecBuilder_NoHead.build();


    public static Response sendReqAndGetRespDadataCleanerAddress_NoHead(String reqBody) {
        responseDadata = given().
                spec(reqDadataCleanerAddressSpec_NoHead).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }
}
