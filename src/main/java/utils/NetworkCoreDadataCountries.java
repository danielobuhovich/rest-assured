package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_SUGGESTIONS_COUNTRY;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataCountries {

    static Response responseDadata;

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
}
