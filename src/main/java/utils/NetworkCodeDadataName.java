package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_CLEANER_NAME;
import static constants.Constants.Path.DADATA_CLEANER_PATH;
import static constants.Constants.ServerName.DADATA_CLEANER_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCodeDadataName {

    protected Response response;

    static Response responseDadata;

    //positive spec
    public static RequestSpecBuilder reqDadataCleanerFioSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_NAME)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    static RequestSpecification reqDadataCleanerFioSpec=reqDadataCleanerFioSpecBuilder.build();


    //negative spec
    public static RequestSpecBuilder reqDadataCleanerFioSpecBuilderNoKey = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_NAME)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA);

    static RequestSpecification reqDadataCleanerFioSpecNoKey=reqDadataCleanerFioSpecBuilderNoKey.build();


    public static RequestSpecBuilder reqDadataCleanerFioSpecBuilderNoAuth = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_NAME)
            .setContentType(ContentType.JSON)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    static RequestSpecification reqDadataCleanerFioSpecNoAuth=reqDadataCleanerFioSpecBuilderNoAuth.build();


    public static RequestSpecBuilder reqDadataCleanerFioSpecBuilderNoConttype = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_CLEANER_NAME)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    static RequestSpecification reqDadataCleanerFioSpecNoConttype=reqDadataCleanerFioSpecBuilderNoConttype.build();


    //Positive functions

    public static void sendRequestAndGetResponseDadata(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }

    public static void sendRequestAndGetResponseDadata(String reqBody,String gender) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().body("[0]",Matchers.notNullValue()).
                assertThat().body("[0].gender", Matchers.equalTo(gender));
    }

    public static void sendRequestAndGetResponseDadata(String reqBody, int stCode, String gender) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpec).
                body(reqBody)
                .when().post();
        responseDadata.then().log().body().
                assertThat().body("[0]",Matchers.notNullValue()).
                assertThat().statusCode(stCode).
                assertThat().body("[0].gender", Matchers.equalTo(gender));
    }


    //Negative functions
    public static void sendRequestAndGetResponseDadataNoKey(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpecNoKey).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }

    public static void sendRequestAndGetResponseDadataGet(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }

    public static void sendRequestAndGetResponseDadataNoAuth(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpecNoAuth).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }

    public static void sendRequestAndGetResponseDadataNoConttype(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpecNoConttype).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }
}
