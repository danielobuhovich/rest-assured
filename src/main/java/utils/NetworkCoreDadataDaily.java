package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static constants.Constants.Endpoint.DADATA_ENDPOINT_STAT_DAILY;
import static constants.Constants.Path.DADATA_STAT_PATH;
import static constants.Constants.ServerName.DADATA_SERVER;
import static io.restassured.RestAssured.given;

public class NetworkCoreDadataDaily {

    public static Response responseDadata;


    public static RequestSpecBuilder reqSpecBuildDadataDaily=new RequestSpecBuilder()
            .setBaseUri(DADATA_SERVER+DADATA_STAT_PATH+DADATA_ENDPOINT_STAT_DAILY)
            .addHeader("Authorization",API_TOKEN_DADATA)
            .addHeader("X-Secret",DADATA_SECRET_KEY);

    public static RequestSpecification reqSpecDadataDaily= reqSpecBuildDadataDaily.build();


    public static Response FunctionDadataDaily(){
        responseDadata=given().
                spec(reqSpecDadataDaily).
                when().get();
        responseDadata.then().log().body().
                assertThat().body("", Matchers.notNullValue());
        return responseDadata;
    }

    public static Response FunctionDadataDaily(String reqBody){
        responseDadata=given().
                spec(reqSpecDadataDaily).
                body(reqBody).
                when().get();
        responseDadata.then().log().body().
                assertThat().statusCode(200);
        return responseDadata;
    }

    public static Response FunctionDadataDaily_Post(String reqBody){
        responseDadata=given().
                spec(reqSpecDadataDaily).
                body(reqBody).
                when().post();
        responseDadata.then().log().body().
                assertThat().statusCode(405);
        return responseDadata;
    }


    public static RequestSpecBuilder reqSpecBuildDadataDaily_NoHead=new RequestSpecBuilder()
            .setBaseUri(DADATA_SERVER+DADATA_STAT_PATH+DADATA_ENDPOINT_STAT_DAILY);

    public static RequestSpecification reqSpecDadataDaily_NoHead=reqSpecBuildDadataDaily_NoHead.build();


    public static Response FunctionDadataDaily_NoHead(){
        responseDadata=given().
                spec(reqSpecDadataDaily_NoHead).
                when().get();
        responseDadata.then().log().body();
        return responseDadata;
    }
}
