package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static utils.NetworkCore.FunctionJsonPathRead;
import static utils.NetworkCoreDadataDaily.*;

public class DadataDaily {

    String defaultBody="test";
    Response responseBody;
    ArrayList<String> values;
    LocalDate locDate = LocalDate.now();

    @Test(description = "Get request")
    public void DadataDaily_Get(){
        responseBody=FunctionDadataDaily();

        Assert.assertNotNull(responseBody.jsonPath().getString(""));
        Assert.assertEquals(responseBody.statusCode(), 200);
    }

    @Test(description = "Get request with compare date")
    public void DadataDaily_GetDateCheck(){
        responseBody=FunctionDadataDaily();

        Assert.assertEquals(responseBody.statusCode(), 200);
        Assert.assertNotNull(responseBody.jsonPath().getString(""));

        values= FunctionJsonPathRead(responseBody,"$..date");

        String[] dateParts=values.get(0).split("-");

        String[] asd=locDate.toString().split("-");

        Assert.assertEquals(dateParts,asd);
    }

    @Test(description = "Request with method post")
    public void DadataDaily_Post(){
        responseBody=FunctionDadataDaily_Post(defaultBody);
    }

    @Test(description = "Get request with request body")
    public void DadataDaily_GetWithBody(){
        responseBody=FunctionDadataDaily(defaultBody);

        Assert.assertEquals(responseBody.statusCode(), 200);
    }

    @Test(description = "Request without authorization header")
    public void DadataDaily_GetNoAuth(){
        reqSpecBuildDadataDaily_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(), 401);
    }

    @Test(description = "Requst with empty value in authorization header")
    public void DadataDaily_GetEmptyAuth(){
        reqSpecBuildDadataDaily_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        reqSpecBuildDadataDaily_NoHead.addHeader("Authorization","");

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(), 401);
    }

    @Test(description = "Request with incorrect value in authorization header")
    public void DadataDaily_GetIncAuth(){
        reqSpecBuildDadataDaily_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        reqSpecBuildDadataDaily_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2cd");

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(), 401);
    }

    @Test(description = "Request without secret key header")
    public void DadataDaily_GetNoSecKey(){
        reqSpecBuildDadataDaily_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test(description = "Request with empty secret key header")
    public void DadataDaily_GetEmptySecKey(){
        reqSpecBuildDadataDaily_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataDaily_NoHead.addHeader("X-Secret","");

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test(description = "Request with incorrect secret key header")
    public void DadataDaily_GetIncSecKey(){
        reqSpecBuildDadataDaily_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataDaily_NoHead.addHeader("X-Secret","8918040d277019c25d2535694cfeb125ae5388");

        responseBody=FunctionDadataDaily_NoHead();

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test(description = "Request with json in content type header")
    public void DadataDaily_GetJsonContType(){
        reqSpecBuildDadataDaily.setContentType(ContentType.JSON);

        responseBody=FunctionDadataDaily();

        Assert.assertEquals(responseBody.statusCode(),200);
    }

    @Test(description = "Request with xml in content type header")
    public void DadataDaily_GetXmlContType(){
        reqSpecBuildDadataDaily.setContentType(ContentType.XML);

        responseBody=FunctionDadataDaily();

        Assert.assertEquals(responseBody.statusCode(),200);
    }

    @Test(description = "Request with json in accept header")
    public void DadataDaily_GetJsonAccept(){
        reqSpecBuildDadataDaily.setAccept(ContentType.JSON);

        responseBody=FunctionDadataDaily();

        Assert.assertEquals(responseBody.statusCode(),200);
    }

    @Test(description = "Request with xml in accept header")
    public void DadataDaily_GetXmlAccept(){
        reqSpecBuildDadataDaily.setAccept(ContentType.XML);

        responseBody=FunctionDadataDaily();

        Assert.assertEquals(responseBody.statusCode(),406);
    }
}
