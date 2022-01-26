package tests.Dadata;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static constants.Constants.API_TOKEN_DADATA;
import static utils.NetworkCore.*;

public class DadataCountries {

    String jsonBody;
    String requestBody;

    Response responseBody;

    String defaultBody="{\"query\": \"Мона\"}";

    @Test(description = "Positive test of country searching in dadata suggestions by short name")
    public  void SearchDadataCountry_NameShort(){

        responseBody=sendReqAndGetRespDadataSuggestCountries(defaultBody);
    }

    @Test(description = "Request with empty request body")
    public  void SearchDadataCountry_EmptyBody(){

        requestBody="";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with empty value in request body")
    public  void SearchDadataCountry_EmptyValBody(){

        requestBody="{\"query\": \" \"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with out of max range request body")
    public  void SearchDadataCountry_OutOfMaxBody(){

        requestBody="{\"query\": \"МолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдоваМолдова \"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),413);
    }

    @Test(description = "Request with numbers in request body")
    public  void SearchDadataCountry_NumInBody(){

        requestBody="{\"query\": \"1234\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with special characters in request body")
    public  void SearchDadataCountry_SpecCharInBody(){

        requestBody="{\"query\": \"!@#$\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with latin characters in request body")
    public  void SearchDadataCountry_LatinInBody(){

        requestBody="{\"query\": \"Mona\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with upper case in request body")
    public  void SearchDadataCountry_UpCaseBody(){

        requestBody="{\"query\": \"МОЛДО\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());
    }

    @Test(description = "Request with lower case in request body")
    public  void SearchDadataCountry_LowCaseBody(){

        requestBody="{\"query\": \"молдо\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());
    }

    @Test(description = "Request with mixed case in request body")
    public  void SearchDadataCountry_MixCaseBody(){

        requestBody="{\"query\": \"мОЛДо\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());
    }

    @Test(description = "Request with transfer text in request body")
    public  void SearchDadataCountry_TransferBody(){

        requestBody="{\"query\": \"vjklj\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request with incorrect key in request body")
    public  void SearchDadataCountry_IncKeyInBody(){

        requestBody="{\"qery\": \"Мона\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());
    }

    @Test(description = "Request without well formed json in request body")
    public  void SearchDadataCountry_WithoutWFJsonBody(){

        requestBody="{\"query: \"Мона}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),400);
    }

    @Test(description = "Positive test of country searching in dadata suggestions by name")
    public  void SearchDadataCountry_Name(){

        requestBody="{\"query\": \"Республика\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Positive test of country searching in dadata suggestions by code")
    public  void SearchDadataCountry_Code(){

        requestBody="{\"query\": \"500\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());

        jsonBody=responseBody.asString();
        ArrayList<String> values= JsonPath.read(jsonBody,"$..code");

        Assert.assertEquals(responseBody.getStatusCode(),200);
        Assert.assertTrue(values.contains("500"));
    }

    @Test(description = "Request with zero code")
    public  void SearchDadataCountry_ZeroCode(){

        requestBody="{\"query\": \"000\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Request with negative code")
    public  void SearchDadataCountry_NegativeCode(){

        requestBody="{\"query\": \"-15\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.nullValue());

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Positive test of country searching in dadata suggestions by Alfa2")
    public  void SearchDadataCountry_Alfa2(){

        requestBody="{\"query\": \"NL\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());

        jsonBody=responseBody.asString();
        ArrayList<String> values= JsonPath.read(jsonBody,"$..alfa2");

        Assert.assertEquals(responseBody.getStatusCode(),200);
        Assert.assertTrue(values.contains("NL"));
    }

    @Test(description = "Positive test of country searching in dadata suggestions by Alfa3")
    public  void SearchDadataCountry_Alfa3(){

        requestBody="{\"query\": \"UKR\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());

        jsonBody=responseBody.asString();
        ArrayList<String> values= JsonPath.read(jsonBody,"$..alfa3");

        Assert.assertEquals(responseBody.getStatusCode(),200);
        Assert.assertTrue(values.contains("UKR"));
    }


    @Test(description = "Request without content type header")
    public  void SearchDadataCountry_NoContType(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test(description = "Request with incorrect content type")
    public  void SearchDadataCountry_IncContType(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.JSON);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.XML);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),400);
    }

    @Test(description = "Request without accept header")
    public  void SearchDadataCountry_NoAccept(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("suggestions[0]",Matchers.notNullValue());
    }

    @Test(description = "Request with incorrect content type")
    public  void SearchDadataCountry_IncAccept(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.JSON);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.XML);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
        responseBody.then().assertThat().body("SuggestResponse",Matchers.notNullValue());
    }

    @Test(description = "Request without authorization")
    public  void SearchDadataCountry_NoAuth(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.JSON);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with incorrect authorization")
    public  void SearchDadataCountry_IncAuth(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization","Token f3374ee9d5c69869827fd83bf831f0188d9e8");
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.JSON);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test(description = "Request with empty authorization")
    public  void SearchDadataCountry_EmptyAuth(){

        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.addHeader("Authorization","");
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setContentType(ContentType.JSON);
        reqDadataSuggestionsCountriesSpecBuilder_NoHeaders.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with get")
    public  void SearchDadataCountry_Get(){

        responseBody=sendReqAndGetRespDadataSuggestCountries_NoHeaders(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test(description = "Request with max request body length")
    public  void SearchDadataCountry_MaxRequestBody(){

        //300 symbols in query
        requestBody="{\"query\": \"МонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонако\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Request with out of max request body length")
    public  void SearchDadataCountry_OutMaxRequestBody(){

        //301 symbols in query
        requestBody="{\"query\": \"qМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонакоМонако\"}";

        responseBody=sendReqAndGetRespDadataSuggestCountries(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),413);
    }
}