package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static constants.Constants.API_TOKEN_DADATA;
import static utils.NetworkCore.FunctionJsonPathRead;
import static utils.NetworkCoreDadataSuggestionsEmail.*;

public class DadataSuggestionsEmail {

    String defaultBody="{ \"query\": \"serega@\" }";
    String requestBody;
    Response responseBody;
    ArrayList<String> values;

    @Test(description = "Request with latin symbols in body")
    public void DadataSuggestionsEmail_Latin(){

        responseBody=FunctionDadataSuggestionsEmail(defaultBody,200);

        values= FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("serega"));
    }

    @Test(description = "Request with cyrillic symbols in body")
    public void DadataSuggestionsEmail_Cyrillic(){

        requestBody="{ \"query\": \"сергей@\" }";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with numbers in body")
    public void DadataSuggestionsEmail_Numbers(){

        requestBody="{\"query\":\"1234@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("1234"));
    }

    @Test(description = "Request with special symbols in body")
    public void DadataSuggestionsEmail_SpecSymb(){

        requestBody="{\"query\":\"!#$%@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with upper case in body")
    public void DadataSuggestionsEmail_UpCase(){

        requestBody="{\"query\":\"SEREGA@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("serega"));
    }

    @Test(description = "Request with mixed case in body")
    public void DadataSuggestionsEmail_MixCase(){

        requestBody="{\"query\":\"SeReGa@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("serega"));
    }

    @Test(description = "Request with empty body")
    public void DadataSuggestionsEmail_EmptyBody(){
        requestBody="";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with empty value in body")
    public void DadataSuggestionsEmail_EmptyValue(){
        requestBody="{\"query\":\" \"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with incorrect key in body")
    public void DadataSuggestionsEmail_IncKey(){
        requestBody="{\"qury\":\"serega@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with out of max range symbols in body")
    public void DadataSuggestionsEmail_OutOfMaxRange(){
        requestBody="{\"query\":\"seregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaserega@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,413);

    }

    @Test(description = "Request without well formed json in body")
    public void DadataSuggestionsEmail_WithoutWFJson(){
        requestBody="{\"query:serega@\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);
    }

    @Test(description = "Request with transfered body")
    public void DadataSuggestionsEmail_TransferBody(){
        requestBody="{\"query\":\"ыукупф@нфтвучюкг\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with default count in body")
    public void DadataSuggestionsEmail_DefaultCountNotSet(){

        responseBody=FunctionDadataSuggestionsEmail(defaultBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(10,values.size());
    }

    @Test(description = "Request with set default count in body")
    public void DadataSuggestionsEmail_DefaultCountSet(){
        requestBody="{\"query\":\"serega@\",\"count\":10}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(10,values.size());
    }

    @Test(description = "Request with min count in body")
    public void DadataSuggestionsEmail_MinCount(){
        requestBody="{\"query\":\"serega@\",\"count\":1}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(1,values.size());
    }

    @Test(description = "Request with min count +1")
    public void DadataSuggestionsEmail_MinCountPlus(){
        requestBody="{\"query\":\"serega@\",\"count\":2}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(2,values.size());
    }

    @Test(description = "Request with zero in count")
    public void DadataSuggestionsEmail_CountZero(){
        requestBody="{\"query\":\"serega@\",\"count\":0}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(10,values.size());
    }

    @Test(description = "Request with negative count in body")
    public void DadataSuggestionsEmail_NegativeCount(){
        requestBody="{\"query\":\"serega@\",\"count\":-2}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(10,values.size());
    }

    @Test(description = "Request with max count in body")
    public void DadataSuggestionsEmail_MaxCount(){
        requestBody="{\"query\":\"serega@\",\"count\":20}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(20,values.size());
    }

    @Test(description = "Request with max count -1")
    public void DadataSuggestionsEmail_MaxCountMinus(){
        requestBody="{\"query\":\"serega@\",\"count\":19}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(19,values.size());
    }

    @Test(description = "Request with max count +1")
    public void DadataSuggestionsEmail_MaxCountPlus(){
        requestBody="{\"query\":\"serega@\",\"count\":21}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(20,values.size());
    }

    @Test(description = "Request with out of max value count")
    public void DadataSuggestionsEmail_MaxCountValue(){
        requestBody="{\"query\":\"serega@\",\"count\":102}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..value");

        Assert.assertEquals(20,values.size());
    }

    @Test(description = "Request with special symbols in count")
    public void DadataSuggestionsEmail_SpecSymbCount(){
        requestBody="{\"query\":\"serega@\",\"count\":\"!@#\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);
    }

    @Test(description = "Request with latin symbols in count")
    public void DadataSuggestionsEmail_LatinSymbCount(){
        requestBody="{\"query\":\"serega@\",\"count\":\"qwe\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);
    }

    @Test(description = "Request with cyrillic symbols in count")
    public void DadataSuggestionsEmail_CyrillicSymbCount(){
        requestBody="{\"query\":\"serega@\",\"count\":\"фыв\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);
    }

    @Test(description = "Request with out of max symbols in count")
    public void DadataSuggestionsEmail_CountOutOfMaxSymb(){
        requestBody="{\"query\":\"serega@\",\"count\":1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,400);
    }

    @Test(description = "Testing domain suggestions")
    public void DadataSuggestionsEmail_DomainSugg(){
        requestBody="{\"query\":\"serega@gm\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("serega"));
    }

    @Test(description = "Testing local suggestions")
    public void DadataSuggestionsEmail_LocalSugg(){
        requestBody="{\"query\":\"serega\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..local");

        Assert.assertTrue(values.contains("serega"));
    }

    @Test(description = "Request with empty local")
    public void DadataSuggestionsEmail_EmptyLocal(){
        requestBody="{\"query\":\"@gmail.com\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);
    }

    @Test(description = "Testing correcting typos")
    public void DadataSuggestionsEmail_CorrectsTypos(){
        requestBody="{\"query\":\"serega@gmal/com\"}";

        responseBody=FunctionDadataSuggestionsEmail(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..domain");

        Assert.assertTrue(values.contains("gmail.com"));
    }

    @Test(description = "Request with get method")
    public void DadataSuggestionsEmail_Get(){
        responseBody=FunctionDadataSuggestionsEmail_Get(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),405);
    }

    @Test(description = "Request without authorization header")
    public void DadataSuggestionsEmail_NoAuth(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.JSON);

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,401);
    }

    @Test(description = "Request with empty value in authorization header")
    public void DadataSuggestionsEmail_EmptyAuth(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization","");

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,401);
    }

    @Test(description = "Request with incorrect value in authorization header")
    public void DadataSuggestionsEmail_IncAuth(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2cd");

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,403);
    }

    @Test(description = "Request without content type header")
    public void DadataSuggestionsEmail_NoContType(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,415);
    }

    @Test(description = "Request with xml content type header")
    public void DadataSuggestionsEmail_IncContType(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.XML);

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,400);
    }

    @Test(description = "Request without accept header")
    public void DadataSuggestionsEmail_NoAccept(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,200);
    }

    @Test(description = "Request with xml accept header")
    public void DadataSuggestionsEmail_IncAccept(){

        reqSpecBuildDadataSuggestionsEmail_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggestionsEmail_NoHead.setAccept(ContentType.XML);

        responseBody=FunctionDadataSuggestionsEmail_NoHead(defaultBody,200);
    }
}
