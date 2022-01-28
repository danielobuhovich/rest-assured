package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_SUGGESTION_CURRENCY;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataCurrency {

    static Response responseDadata;

    public static RequestSpecBuilder reqDadataSuggCurrencySpecBuilder = new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTION_CURRENCY)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("Authorization",API_TOKEN_DADATA);

    static RequestSpecification reqDadataSuggCurrencySpec=reqDadataSuggCurrencySpecBuilder.build();

    public static Response sendReqAndGetRespDadataSuggCurrency(String reqBody, int stCode) {
        responseDadata = given().
                spec(reqDadataSuggCurrencySpec).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }

    public static Response sendReqAndGetRespDadataSuggCurrency_Get(String reqBody){
        responseDadata=given().
                spec(reqDadataSuggCurrencySpec).
                body(reqBody).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }

    public static RequestSpecBuilder reqDadataSuggCurrencySpecBuilder_NoHead=new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTION_CURRENCY);

    static RequestSpecification reqDadataSuggCurrencySpec_NoHead=reqDadataSuggCurrencySpecBuilder_NoHead.build();

    public static Response sendReqAndGetRespDadataSuggCurrency_NoHead(String reqBody){
        responseDadata=given().
                spec(reqDadataSuggCurrencySpec_NoHead).
                body(reqBody).
                when().post();
        responseDadata.then().log().body();
        return responseDadata;
    }
}
