package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.*;
import static constants.Constants.Path.DADATA_CLEANER_PATH;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_CLEANER_SERVER;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCore {

    protected Response response;
    protected RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
    protected static JSONObject jsonResponse;



//DadataName start
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
    public static Response sendRequestAndGetResponseDadata(String reqBody) {
        responseDadata = given().
                spec(reqDadataCleanerFioSpec).
                body(reqBody).
        when().post();
        responseDadata.then().log().body().
                assertThat().body("[0]",Matchers.notNullValue());
        return responseDadata;
    }

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
//DadataName end



//DadataFio start
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
                assertThat().body("suggestions[0]",Matchers.notNullValue());
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
//DadataFio end



//DadataCountries start
public static RequestSpecBuilder reqDadataSuggestionsCountriesSpecBuilder = new RequestSpecBuilder()
        .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_COUNTRY)
        .setContentType(ContentType.JSON)
        .setAccept(ContentType.JSON)
        .addHeader("Authorization",API_TOKEN_DADATA);

    static RequestSpecification reqDadataSuggestionsCountriesSpec=reqDadataSuggestionsCountriesSpecBuilder.build();


    public static Response sendReqAndGetRespDadataSuggestCountries(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsCountriesSpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }



    public static RequestSpecBuilder reqDadataSuggestionsCountriesSpecBuilder_NoHeaders = new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_COUNTRY);

    static RequestSpecification reqDadataSuggestionsCountriesSpec_NoHeaders=reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.build();


    public static Response sendReqAndGetRespDadataSuggestCountries_NoHeaders(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsCountriesSpec_NoHeaders).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }


    public static Response sendReqAndGetRespDadataSuggestCountries_Get(String reqBody) {
        responseDadata = given().
                spec(reqDadataSuggestionsCountriesSpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }
//DadataCountries end


//Google
    public void sendRequestAndGetResponse(Method methd, int code){
        response=given().spec(requestSpecBuilder.build()).log().uri().log().parameters()
        .when().request(methd);
        response.then().log().body().assertThat().statusCode(code);
        try {
            jsonResponse = new JSONObject(response.getBody().asString());
        } catch (Exception e){
            Assert.fail("Can`t get response body");
        }
    }
}
