package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_CLEANER_PHONE;
import static constants.Constants.Path.DADATA_CLEANER_PATH;
import static constants.Constants.ServerName.DADATA_CLEANER_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataCleanerPhone {

    public static Response responseBody;

    public static RequestSpecBuilder reqSpecBuilderDadataCleanerPhone = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_PHONE)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    public static RequestSpecification reqSpecDadataCleanerPhone= reqSpecBuilderDadataCleanerPhone.build();

    public static Response dadataCleanerPhone_Full(String reqBody,int stCode){
        responseBody=given().
                spec(reqSpecDadataCleanerPhone).
                body(reqBody).
                when().post();
        responseBody.then().log().body().
                assertThat().statusCode(stCode).
                assertThat().body(Matchers.notNullValue());
        return responseBody;
    }

    public static Response dadataCleanerPhone_Get(String reqBody,int stCode){
        responseBody=given().
                spec(reqSpecDadataCleanerPhone).
                body(reqBody).
                when().get();
        responseBody.then().log().body().
                assertThat().statusCode(stCode);
        return responseBody;
    }


    public static RequestSpecBuilder reqSpecBuilderDadataCleanerPhone_NoHead = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_PHONE);

    public static RequestSpecification reqSpecDadataCleanerPhone_NoHead=reqSpecBuilderDadataCleanerPhone_NoHead.build();

    public static Response dadataCleanerPhone_NoHead(String reqBody,int stCode){
        responseBody=given().
                spec(reqSpecDadataCleanerPhone_NoHead).
                body(reqBody).
                when().post();
        responseBody.then().log().body().
                assertThat().statusCode(stCode);
        return responseBody;
    }
}
