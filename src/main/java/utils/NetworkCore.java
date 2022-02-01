package utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class NetworkCore {

    protected Response response;
    protected RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
    protected static JSONObject jsonResponse;

    public static ArrayList<String> FunctionJsonPathRead(Response responseBody,String jsonPath){
        return (JsonPath.read(responseBody.asString(),jsonPath));
    }

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
