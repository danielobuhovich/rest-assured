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

    @Test
    public void DadataSuggCurrency_KirillRequest(){
        responseBody=sendReqAndGetRespDadataSuggCurrency(defaultBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test
    public void DadataSuggCurrency_EmptyValueRequest(){
        requestBody="{\"query\":\" \"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_EmptyRequest(){
        requestBody="";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_LatinRequest(){
        requestBody="{\"query\":\"doll\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_NotWFJsonRequest(){
        requestBody="{\"query : \"руб}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,400);
    }

    @Test
    public void DadataSuggCurrency_TransferRequest(){
        requestBody="{\"query\":\"he,\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_IncKeyRequest(){
        requestBody="{\"qury\":\"руб\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_MaxRequest(){
        requestBody="{\"query\":\"рубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубльрубль\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,413);
    }

    @Test
    public void DadataSuggCurrency_UpCaseRequest(){
        requestBody="{\"query\":\"РУБЛЬ\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test
    public void DadataSuggCurrency_MixCaseRequest(){
        requestBody="{\"query\":\"РубЛЬ\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("рубль"));
    }

    @Test
    public void DadataSuggCurrency_ZeroInRequest(){
        requestBody="{\"query\":\"000\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_NegativeInRequest(){
        requestBody="{\"query\":\"-13\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_SpecSymbInRequest(){
        requestBody="{\"query\":\"!#@\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_CodeSearching(){
        requestBody="{\"query\":933}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Беларусь"));
    }

    @Test
    public void DadataSuggCurrency_StrCodeSearching(){
        requestBody="{\"query\":\"USD\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("США"));
    }

    @Test
    public void DadataSuggCurrency_NameSearch(){
        requestBody="{\"query\":\"песо\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("ARS"));
    }

    @Test
    public void DadataSuggCurrency_CountrySearch(){
        requestBody="{\"query\":\"Украина\"}";
        responseBody=sendReqAndGetRespDadataSuggCurrency(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Гривна"));
    }

    @Test
    public void DadataSuggCurrency_Get(){
        responseBody=sendReqAndGetRespDadataSuggCurrency_Get(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test
    public void DadataSuggCurrency_NoAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test
    public void DadataSuggCurrency_EmptyAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization","");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),401);
    }

    @Test
    public void DadataSuggCurrency_IncAuth(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2c");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),403);
    }

    @Test
    public void DadataSuggCurrency_NoContType(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),415);
    }

    @Test
    public void DadataSuggCurrency_IncContType(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.XML);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),400);
    }

    @Test
    public void DadataSuggCurrency_NoAccept(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),200);
        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test
    public void DadataSuggCurrency_EmptyAccept(){
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization", API_TOKEN_DADATA);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Accept","");

        responseBody=sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(),406);
    }

    @Test
    public void DadataSuggCurrency_IncAccept() {
        reqDadataSuggCurrencySpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataSuggCurrencySpecBuilder_NoHead.setAccept(ContentType.XML);
        reqDadataSuggCurrencySpecBuilder_NoHead.addHeader("Authorization", API_TOKEN_DADATA);

        responseBody = sendReqAndGetRespDadataSuggCurrency_NoHead(defaultBody);

        Assert.assertEquals(responseBody.statusCode(), 200);
        Assert.assertNotNull(responseBody.jsonPath().toString());
    }
}
