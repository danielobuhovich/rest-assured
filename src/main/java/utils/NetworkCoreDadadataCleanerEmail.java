package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_EMAIL;
import static constants.Constants.Path.DADATA_CLEANER_PATH;
import static constants.Constants.ServerName.DADATA_CLEANER_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadadataCleanerEmail {

    static Response responseDadata;

    public static RequestSpecBuilder dadataCleanerEmailReqSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_EMAIL)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    public static RequestSpecification dadataCleanerEmailReqSpec= dadataCleanerEmailReqSpecBuilder.build();

    public static Response functionDadataCleanerEmail(String reqBody){
        responseDadata=given().
                spec(dadataCleanerEmailReqSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().body("", Matchers.notNullValue());
        return responseDadata;
    }

    public static Response functionDadataCleanerEmail(String reqBody, int stCode){
        responseDadata=given().
                spec(dadataCleanerEmailReqSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode).
                assertThat().body("", Matchers.notNullValue());
        return responseDadata;
    }

    public static Response functionDadataCleanerEmail_Get(String reqBody){
        responseDadata=given().
                spec(dadataCleanerEmailReqSpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }


    public static RequestSpecBuilder dadataCleanerEmailReqSpecBuilder_NoHead= new RequestSpecBuilder()
            .setBaseUri(DADATA_CLEANER_SERVER+DADATA_CLEANER_PATH+DADATA_ENDPOINT_EMAIL);

    public static RequestSpecification dadataCleanerEmailReqSpec_NoHead=dadataCleanerEmailReqSpecBuilder_NoHead.build();

    public static Response functionDadataCleanerEmail_NoHead(String reqBody){
        responseDadata=given().
                spec(dadataCleanerEmailReqSpec_NoHead).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }
}
