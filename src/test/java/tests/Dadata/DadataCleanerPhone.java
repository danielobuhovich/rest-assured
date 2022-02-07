package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static utils.NetworkCoreDadataCleanerPhone.*;

public class DadataCleanerPhone {

    public static String defaultReqBody="[ \"раб 846)231.60.14 *139\" ]";
    public static String requestBody;
    public static Response responseBody;

    @Test(description = "Positive test")
    public void DadataCleanerPhone_Default(){
        responseBody=dadataCleanerPhone_Full(defaultReqBody,200);

        Assertions.assertEquals(responseBody.jsonPath().getString("[0].extension"),"139");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
    }

    @Test(description = "Request with mobile phone in body")
    public void DadataCleanerPhone_Mobile(){
        requestBody="[\"+7 911 243-45-68\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Мобильный");
    }

    @Test(description = "Request with station phone in body")
    public void DadataCleanerPhone_Station(){
        requestBody="[\"+7 495 456-55-77\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Стационарный");
    }

    @Test(description = "Request with straight mobile in body")
    public void DadataCleanerPhone_StrMobile(){
        requestBody="[\"+7 495 243-45-68\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Прямой мобильный");
    }

    @Test(description = "Request with call center phone in body")
    public void DadataCleanerPhone_CallCenter(){
        requestBody="[\"8 800 222-12-22\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Колл-центр");
    }

    @Test(description = "Request with unknown phone in body")
    public void DadataCleanerPhone_Unknown(){
        requestBody="[\"+7 333 1111112\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Неизвестный");
    }

    @Test(description = "Request with Belarus phone in body")
    public void DadataCleanerPhone_Belarus(){
        requestBody="[\"+375291111111\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].type"),"Мобильный");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].provider"),"Velcom");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Беларусь");
    }

    @Test(description = "Request with empty body")
    public void DadataCleanerPhone_EmptyBody(){
        requestBody=" ";
        responseBody=dadataCleanerPhone_Full(requestBody,400);
    }

    @Test(description = "Request with empty value in body")
    public void DadataCleanerPhone_EmptyValue(){
        requestBody="[\" \"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("[0].source"));
    }

    @Test(description = "Request with out of max range symbols in body")
    public void DadataCleanerPhone_OutOfMaxRangeBody(){
        requestBody="[\"7 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 11111127 333 1111112\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Request with latin symbols in body")
    public void DadataCleanerPhone_LatinBody(){
        requestBody="[\"Russia\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"Russia");
        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Request with cyrillic symbols in body")
    public void DadataCleanerPhone_CyrillicBody(){
        requestBody="[\"Россия\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"Россия");
        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Request with special symbols in body")
    public void DadataCleanerPhone_SpecialSymbBody(){
        requestBody="[\"!#$\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"!#$");
        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Request without well formed json in body")
    public void DadataCleanerPhone_NotWFJson(){
        requestBody="[\"+7 333 1111112]";
        responseBody=dadataCleanerPhone_Full(requestBody,400);
    }

    @Test(description = "Request with mess in body")
    public void DadataCleanerPhone_MessedBody(){
        requestBody="[\"-7 333 1=111*112\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"-7 333 1=111*112");
        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Request with zero in body")
    public void DadataCleanerPhone_ZeroBody(){
        requestBody="[\"+0 000 0000000\"]";
        responseBody=dadataCleanerPhone_Full(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"+0 000 0000000");
        Assert.assertNull(responseBody.jsonPath().getString("[0].phone"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].number"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].extension"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].provider"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].country"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].region"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].timezone"));
    }

    @Test(description = "Get request")
    public void DadataCleanerPhone_Get(){
        responseBody=dadataCleanerPhone_Get(defaultReqBody,405);
    }

    @Test(description = "Request without headers")
    public void DadataCleanerPhone_NoHead(){
        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request without authorization header")
    public void DadataCleanerPhone_NoAuth(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request with empty authorization header")
    public void DadataCleanerPhone_EmptyAuth(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization","");
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request with incorrect value in authorization header")
    public void DadataCleanerPhone_IncAuth(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization","Token asdaw241assfsa");

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,403);
    }

    @Test(description = "Request with swapped values in secret key and authorization")
    public void DadataCleanerPhone_SwapSecretAndAuth(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",API_TOKEN_DADATA);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",DADATA_SECRET_KEY);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request without secret key header")
    public void DadataCleanerPhone_NoSecret(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request with empty value in secret key header")
    public void DadataCleanerPhone_EmptySecret(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret","");

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,401);
    }

    @Test(description = "Request with incorrect secret key header")
    public void DadataCleanerPhone_IncSecret(){
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.JSON);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret","asdqw4124wasd12");

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,403);
    }

    @Test(description = "Request without content type header")
    public void DadataCleanerPhone_NoContType(){
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,415);
    }

    @Test(description = "Request with xml in content type header")
    public void DadataCleanerPhone_XMLContType(){
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuilderDadataCleanerPhone_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        reqSpecBuilderDadataCleanerPhone_NoHead.setContentType(ContentType.XML);

        responseBody=dadataCleanerPhone_NoHead(defaultReqBody,415);
    }

    @Test(description = "Request with json in accept header")
    public void DadataCleanerPhone_JsonAccept(){
        reqSpecBuilderDadataCleanerPhone.setAccept(ContentType.JSON);

        responseBody=dadataCleanerPhone_Full(defaultReqBody,200);
    }

    @Test(description = "Request with xml in accept header")
    public void DadataCleanerPhone_XMLAccept(){
        reqSpecBuilderDadataCleanerPhone.setAccept(ContentType.XML);

        responseBody=dadataCleanerPhone_Full(defaultReqBody,406);
    }
}
