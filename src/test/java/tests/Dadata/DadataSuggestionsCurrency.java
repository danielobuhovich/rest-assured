package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.API_TOKEN_DADATA;
import static utils.NetworkCoreDadataCurrency.*;

public class DadataSuggestionsCurrency {

    String requestBody;

    Response responseBody;

    String defaultBody="{ \"query\": \"руб\" }";

    @Test(description = "Request with Cyrillic symbols")
    public void DadataSuggCurrency_KirillRequest(){
        responseBody=sendReqAndGetRespDadataSuggCurrency(defaultBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test(description = "Request with empty value")
    public void DadataSuggCurrency_EmptyValueRequest(){
        requestBody="{\"query\":\" \"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with empty body")
    public void DadataSuggCurrency_EmptyRequest(){
        requestBody="";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with latin symbols")
    public void DadataSuggCurrency_LatinRequest(){
        requestBody="{\"query\":\"doll\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request without well formed json")
    public void DadataSuggCurrency_NotWFJsonRequest(){
        requestBody="{\"query : \"руб}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,400);
    }

    @Test(description = "Request with transfered text")
    public void DadataSuggCurrency_TransferRequest(){
        requestBody="{\"query\":\"he,\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with incorrect key in body")
    public void DadataSuggCurrency_IncKeyRequest(){
        requestBody="{\"qury\":\"руб\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with max symbols in body")
    public void DadataSuggCurrency_MaxRequest(){
        requestBody="{\"query\":\"рубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубль\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,413);
    }

    @Test(description = "Request with upper case symbols in body")
    public void DadataSuggCurrency_UpCaseRequest(){
        requestBody="{\"query\":\"РУБЛЬ\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test(description = "Request with mixed case symbols in body")
    public void DadataSuggCurrency_MixCaseRequest(){
        requestBody="{\"query\":\"РубЛЬ\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test(description = "Request with zero in body")
    public void DadataSuggCurrency_ZeroInRequest(){
        requestBody="{\"query\":\"000\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with negative numbers in body")
    public void DadataSuggCurrency_NegativeInRequest(){
        requestBody="{\"query\":\"-13\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with special symbols in body")
    public void DadataSuggCurrency_SpecSymbInRequest(){
        requestBody="{\"query\":\"!#@\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Searching by code")
    public void DadataSuggCurrency_CodeSearching(){
        requestBody="{\"query\":933}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Беларусь"));
    }

    @Test(description = "Searching by StrCode")
    public void DadataSuggCurrency_StrCodeSearching(){
        requestBody="{\"query\":\"USD\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("США"));
    }

    @Test(description = "Searching by name")
    public void DadataSuggCurrency_NameSearch(){
        requestBody="{\"query\":\"песо\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("ARS"));
    }

    @Test(description = "Searching by country")
    public void DadataSuggCurrency_CountrySearch(){
        requestBody="{\"query\":\"Украина\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Гривна"));
    }

    @Test(description = "Request with incorrect method")
    public void DadataSuggCurrency_Get(){
        responseBody=sendReqAndGetRespDadataSuggCurrency_Get(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Request without authorization header")
    public void DadataSuggCurrency_NoAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test(description = "Request with empty authorization header")
    public void DadataSuggCurrency_EmptyAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization","");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test(description = "Request with incorrect value in authorization header")
    public void DadataSuggCurrency_IncAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2c");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),403);
    }

    @Test(description = "Request without Content-Type header")
    public void DadataSuggCurrency_NoContType(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),415);
    }

    @Test(description = "Request with incorrect value in Content-Type header")
    public void DadataSuggCurrency_IncContType(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.XML);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),400);
    }

    @Test(description = "Request without Accept header")
    public void DadataSuggCurrency_NoAccept(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),200);
        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with empty value in Accept header")
    public void DadataSuggCurrency_EmptyAccept(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization", API_TOKEN_DADATA);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Accept","");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),406);
    }

    @Test(description = "Request with incorrect value in Accept header")
    public void DadataSuggCurrency_IncAccept() {
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.XML);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization", API_TOKEN_DADATA);

        responseBody = sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(), 200);
        Assert.assertNotNull(responseBody.jsonPath().toString());
    }
}
