package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_SUGGESTIONS_FIO;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataFio {

    static Response responseDadata;

    public static RequestSpecBuilder reqDadataSuggestionsFioSpecBuilder = new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_FIO)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA);

    static RequestSpecification reqDadataSuggestionsFioSpec=reqDadataSuggestionsFioSpecBuilder.build();


    public static Response sendReqAndGetRespDadataSuggestFio(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsFioSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().body("suggestions[0]", Matchers.notNullValue());
        return responseDadata;
    }

    public static void sendReqAndGetRespDadataSuggestFio(String reqBody,int stCode) {
        responseDadata = given().
                spec(reqDadataSuggestionsFioSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
    }


    //No authorization
    public static RequestSpecBuilder reqDadataSuggestionsFioSpecBuilder_NoAuth = new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_FIO)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON);

    static RequestSpecification reqDadataSuggestionsFioSpec_NoAuth=reqDadataSuggestionsFioSpecBuilder_NoAuth.build();


    public static Response sendReqAndGetRespDadataSuggestFio_NoAuth(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsFioSpec_NoAuth).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }

    //Without types
    public static RequestSpecBuilder reqDadataSuggestionsFioSpecBuilder_NoTypes = new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_FIO)
            .addHeader("Authorization",API_TOKEN_DADATA);

    static RequestSpecification reqDadataSuggestionsFioSpec_NoTypes=reqDadataSuggestionsFioSpecBuilder_NoTypes.build();


    public static Response sendReqAndGetRespDadataSuggestFio_NoTypes(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsFioSpec_NoTypes).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }

    public static Response sendReqAndGetRespDadataSuggestFio_Get(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsFioSpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }
}
