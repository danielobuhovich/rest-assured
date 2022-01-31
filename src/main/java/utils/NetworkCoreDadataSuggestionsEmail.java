package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_EMAIL;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataSuggestionsEmail {

    static Response responseDadata;

    public static RequestSpecBuilder reqSpecBuildDadataSuggestionsEmail=new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_EMAIL)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON);

    public static RequestSpecification reqSpecDadataSuggestionsEmail= reqSpecBuildDadataSuggestionsEmail.build();

    public static Response FunctionDadataSuggestionsEmail(String reqBody){
        responseDadata=given().
                spec(reqSpecDadataSuggestionsEmail).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }

    public static Response FunctionDadataSuggestionsEmail(String reqBody,int stCode){
        responseDadata=given().
                spec(reqSpecDadataSuggestionsEmail).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }

    public static Response FunctionDadataSuggestionsEmail_Get(String reqBody){
        responseDadata=given().
                spec(reqSpecDadataSuggestionsEmail).
                body(reqBody).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }


    public static RequestSpecBuilder reqSpecBuildDadataSuggestionsEmail_NoHead=new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_EMAIL);

    public static RequestSpecification reqSpecDadataSuggestionsEmail_NoHead=reqSpecBuildDadataSuggestionsEmail_NoHead.build();

    public static Response FunctionDadataSuggestionsEmail_NoHead(String reqBody,int stCode){
        responseDadata=given().
                spec(reqSpecDadataSuggestionsEmail_NoHead).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }
}
