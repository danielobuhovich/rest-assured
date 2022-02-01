package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_SUGGESTIONS_BANK;
import static constants.Constants.Path.DADATA_SUGGESTION_PATH;
import static constants.Constants.ServerName.DADATA_SUGGESTION_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataSuggestionsBank {

    public static Response responseDadata;

    public static RequestSpecBuilder reqSpecBuildDadataSuggBank=new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_BANK)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON);

    public static RequestSpecification reqSpecDadataSuggBank=reqSpecBuildDadataSuggBank.build();


    public static Response FunctionDadataSuggBank(String reqBody,int stCode){
        responseDadata=given().
                spec(reqSpecDadataSuggBank).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }


    public static Response FunctionDadataSuggBank_Get(String reqBody){
        responseDadata=given().
                spec(reqSpecDadataSuggBank).
                body(reqBody).
                when().get();
        responseDadata.then().log().body().
                assertThat().statusCode(405);
        return responseDadata;
    }


    public static RequestSpecBuilder reqSpecBuildDadataSuggBank_NoHead =new RequestSpecBuilder()
            .setBaseUri(DADATA_SUGGESTION_SERVER+DADATA_SUGGESTION_PATH+DADATA_ENDPOINT_SUGGESTIONS_BANK);

    public static RequestSpecification reqSpecDadataSuggBank_NoHead=reqSpecBuildDadataSuggBank_NoHead.build();


    public static Response FunctionDadataSuggBank_NoHead(String reqBody,int stCode){
        responseDadata=given().
                spec(reqSpecDadataSuggBank_NoHead).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(stCode);
        return responseDadata;
    }

}
