package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static utils.NetworkCoreDadadataCleanerEmail.*;

public class DadataCleanerEmail {

    String defaultBody="[ \"serega@yandex/ru\" ]";
    String requestBody;
    Response responseBody;

    @Test(description = "Request with latin symbols in body")
    public void DadataCleanerEmail_Latin(){

        responseBody=functionDadataCleanerEmail(defaultBody,200);

        Assert.assertEquals("[serega@yandex.ru]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with cyrillic symbols in body")
    public void DadataCleanerEmail_Cyrillic(){

        requestBody="[\"сергей@почта.рус\"]";

        responseBody=functionDadataCleanerEmail(requestBody,200);

        Assert.assertEquals(responseBody.jsonPath().getString("email"),"[null]");
    }

    @Test(description = "Request with numbers in body")
    public void DadataCleanerEmail_Numbers(){

        requestBody="[\"12345@mail.ru\"]";

        responseBody=functionDadataCleanerEmail(requestBody,200);

        Assert.assertEquals("[12345@mail.ru]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with special symbols in body")
    public void DadataCleanerEmail_SpecSymbols(){

        requestBody="[\"!$%@gmail.com\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with empty local email part in body")
    public void DadataCleanerEmail_EmptyLocal(){

        requestBody="[\" @gmail.com\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("domain"));
    }

    @Test(description = "Request with empty domain email part in body")
    public void DadataCleanerEmail_EmptyDomain(){

        requestBody="[\"exapmle@ \"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("local"));
    }

    @Test(description = "Request with empty body")
    public void DadataCleanerEmail_EmptyBody(){

        requestBody="[\" \"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("source"));
    }

    @Test(description = "Request with transfered body")
    public void DadataCleanerEmail_TransferBody(){

        requestBody="[\"ыукфпф@нфтвучюкг\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with incorrect personal domain in body")
    public void DadataCleanerEmail_IncDomain(){

        requestBody="[\"serega@yandx.ry\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[serega@yandex.ru]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with temp mail in body")
    public void DadataCleanerEmail_TempMail(){

        requestBody="[\"rodrelatri@vusra.com\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[DISPOSABLE]",responseBody.jsonPath().getString("type"));
    }

    @Test(description = "Check personal email")
    public void DadataCleanerEmail_PersonalMail(){

        responseBody=functionDadataCleanerEmail(defaultBody);

        Assert.assertEquals("[PERSONAL]",responseBody.jsonPath().getString("type"));
    }

    @Test(description = "Check corporate email")
    public void DadataCleanerEmail_CorporateMail(){

        requestBody="[\"daniel.obuhovich@intexsoft.by\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[CORPORATE]",responseBody.jsonPath().getString("type"));
    }

    @Test(description = "Check role email")
    public void DadataCleanerEmail_Role(){

        requestBody="[\"support@gmail.com\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[ROLE]",responseBody.jsonPath().getString("type"));
    }

    @Test(description = "Request without well formed json in body")
    public void DadataCleanerEmail_WithoutWFJson(){

        requestBody="[\"serega@yandex.ru]";

        responseBody=functionDadataCleanerEmail(requestBody,400);
    }

    @Test(description = "Request with out of max range body")
    public void DadataCleanerEmail_OutOfMaxRange(){

        requestBody="[\"seregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaseregaserega@yandex.ru\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[null]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with upper case symbols in body")
    public void DadataCleanerEmail_UpCase(){

        requestBody="[\"SEREGA@YANDEX.RU\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[serega@yandex.ru]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with mixed case symbols in body")
    public void DadataCleanerEmail_MixCase(){

        requestBody="[\"SeReGa@YaNdEx.Ru\"]";

        responseBody=functionDadataCleanerEmail(requestBody);

        Assert.assertEquals("[serega@yandex.ru]",responseBody.jsonPath().getString("email"));
    }

    @Test(description = "Request with method get")
    public void DadataCleanerEmail_Get(){

        responseBody=functionDadataCleanerEmail_Get(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),405);
    }

    @Test(description = "Request without authorization header")
    public void DadataCleanerEmail_NoAuth(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with empty authorization header")
    public void DadataCleanerEmail_EmptyAuth(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization","");

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with incorrect authorization header")
    public void DadataCleanerEmail_IncAuth(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2cd");

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test(description = "Request without security key header")
    public void DadataCleanerEmail_NoSecKey(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with empty security key header")
    public void DadataCleanerEmail_EmptySecKey(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret","");

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with incorrect value in security key header")
    public void DadataCleanerEmail_IncSecKey(){

        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.JSON);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret","8918040d277019c25d2535694cfeb125ae5388");

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test(description = "Request without content type header")
    public void DadataCleanerEmail_NoContType(){

        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test(description = "Request with incorrect value in content type header")
    public void DadataCleanerEmail_IncContType(){

        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        dadataCleanerEmailReqSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);
        dadataCleanerEmailReqSpecBuilder_NoHead.setContentType(ContentType.XML);

        responseBody=functionDadataCleanerEmail_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test(description = "Request with json type in accept header")
    public void DadataCleanerEmail_JsonAccept(){

        dadataCleanerEmailReqSpecBuilder.setAccept(ContentType.JSON);

        responseBody=functionDadataCleanerEmail(defaultBody,200);
    }

    @Test(description = "Check parts of sent email")
    public void DadataCleanerEmail_Parts(){

        responseBody=functionDadataCleanerEmail(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("source"),"[serega@yandex/ru]");
        Assert.assertEquals(responseBody.jsonPath().getString("email"),"[serega@yandex.ru]");
        Assert.assertEquals(responseBody.jsonPath().getString("local"),"[serega]");
        Assert.assertEquals(responseBody.jsonPath().getString("domain"),"[yandex.ru]");
    }
}
