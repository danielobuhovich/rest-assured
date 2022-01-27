package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.API_TOKEN_DADATA;
import static constants.Constants.DADATA_SECRET_KEY;
import static utils.NetworkCore.*;

public class DadadataCleanerAddress {

    String jsonBody;
    String requestBody;

    Response responseBody;

    String defaultBody="[ \"мск сухонска 11/-89\" ]";
    String notRussia="[ \"Гродно советская\" ]";


    @Test
    public void DadataCleanerAddress_Default(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(defaultBody);
    }

    @Test
    public void DadataCleanerAddress_DefaultSchema(){
        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region"),"Москва");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].street_with_type"),"ул Сухонская");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house_type_full"),"дом");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house"),"11");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_type_full"),"квартира");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat"),"89");
    }

    @Test
    public void DadataCleanerAddress_SchemaIndexCheck(){
        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].postal_code"),"127642");
    }

    @Test
    public void DadataCleanerAddress_SchemaCoordinates(){
        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].geo_lat"),"55.8782557");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].geo_lon"),"37.65372");
    }

    @Test
    public void DadataCleanerAddress_SchemaClosePlaces(){
        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].federal_district"),"Центральный");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].city_area"),"Северо-восточный");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].city_district_with_type"),"р-н Северное Медведково");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_area"),"34.6");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].square_meter_price"),"214887");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_price"),"7435091");
        Assert.assertNotNull(responseBody.jsonPath().getString("[0].metro"));
    }

    @Test
    public void DadataCleanerAddress_SchemaCodes(){
        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].kladr_id"),"7700000000028360004");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].fias_code"),"77000000000000028360004");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].okato"),"45280583000");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].oktmo"),"45362000");
    }

    @Test
    public void DadataCleanerAddress_SchemaNotRussia(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(notRussia);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Беларусь");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region_with_type"),"Гродненская обл");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].city_with_type"),"г Гродно");
    }

    @Test
    public void DadataCleanerAddress_SchemaNotRussia_Index(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(notRussia);

        Assert.assertNull(responseBody.jsonPath().getString("[0].postal_code"));
    }

    @Test
    public void DadataCleanerAddress_SchemaNotRussia_Coordinates(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(notRussia);

        Assert.assertNull(responseBody.jsonPath().getString("[0].geo_lat"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].geo_lon"));
    }

    @Test
    public void DadataCleanerAddress_SchemaNotRussia_ClosePlaces(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(notRussia);

        Assert.assertNull(responseBody.jsonPath().getString("[0].federal_district"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city_area"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].city_district_with_type"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].flat_area"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].square_meter_price"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].flat_price"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].metro"));
    }

    @Test
    public void DadataCleanerAddress_SchemaNotRussia_Codes(){
        responseBody=sendReqAndGetRespDadataCleanerAddress(notRussia);

        Assert.assertNull(responseBody.jsonPath().getString("[0].kladr_id"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].fias_code"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].okato"));
        Assert.assertNull(responseBody.jsonPath().getString("[0].oktmo"));
    }

    @Test
    public void DadataCleanerAddress_Schema_EmptyValReqBody(){
        requestBody="[]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody,400);
    }

    @Test
    public void DadataCleanerAddress_Schema_OutOfMaxReqBody(){
        //4500 symbols
        requestBody="[ \"ГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветскаяГродносоветская\" ]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody,200);
    }

    @Test
    public void DadataCleanerAddress_Schema_NotWfJsonReqBody(){
        requestBody="[Гродно советская]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody,400);
    }

    @Test
    public void DadataCleanerAddress_Schema_EngReqBody(){
        requestBody="[\"Moscow Sukhonska 11/-89 \"]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region"),"Москва");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].street_with_type"),"ул Сухонская");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house_type_full"),"дом");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house"),"11");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_type_full"),"квартира");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat"),"89");
    }

    @Test
    public void DadataCleanerAddress_Schema_TransferReqBody(){
        requestBody="[\"vcr ce[jycrf 11/-89\"]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region"),"Москва");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].street_with_type"),"ул Сухонская");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house_type_full"),"дом");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house"),"11");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_type_full"),"квартира");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat"),"89");
    }

    @Test
    public void DadataCleanerAddress_Schema_NumbersInReqBody(){
        requestBody="[\"123 564\"]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].unparsed_parts"),"123 564");
    }

    @Test
    public void DadataCleanerAddress_Schema_SpecSymbInReqBody(){
        requestBody="[\"!#$%@ *&^\"]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].unparsed_parts"),"!#$%@ *&^");
    }

    @Test
    public void DadataCleanerAddress_Schema_EmptyReqBody(){
        requestBody="";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody,400);;
    }

    @Test
    public void DadataCleanerAddress_Schema_UpCaseReqBody(){
        requestBody="[ \"МСК СУХОНСКА 11/-89\" ]";

        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region"),"Москва");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].street_with_type"),"ул Сухонская");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house_type_full"),"дом");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house"),"11");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_type_full"),"квартира");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat"),"89");
    }

    @Test
    public void DadataCleanerAddress_Schema_MixCaseReqBody(){
        requestBody="[ \"МсК сухОНска 11/-89\" ]";

        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(requestBody);

        Assert.assertEquals(responseBody.jsonPath().getString("[0].country"),"Россия");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].region"),"Москва");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].street_with_type"),"ул Сухонская");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house_type_full"),"дом");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].house"),"11");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat_type_full"),"квартира");
        Assert.assertEquals(responseBody.jsonPath().getString("[0].flat"),"89");
    }

    @Test
    public void DadataCleanerAddress_Schema_ZeroReqBody(){
        requestBody="[ \"000\" ]";

        responseBody=sendReqAndGetRespDadataCleanerAddress(requestBody,200);
    }

    @Test
    public void DadataCleanerAddress_Get(){
        responseBody=sendReqAndGetRespDadataCleanerAddress_Get(defaultBody);
    }

    @Test
    public void DadataCleanerAddress_NoAuth(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test
    public void DadataCleanerAddress_IncAuth(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2cd");
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test
    public void DadataCleanerAddress_EmptyAuth(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization","");
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test
    public void DadataCleanerAddress_NoSecKey(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test
    public void DadataCleanerAddress_IncSecKey(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret","8918040d277019c25d2535694cfeb125ae5388");

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test
    public void DadataCleanerAddress_EmptySecKey(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.JSON);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret","");

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test
    public void DadataCleanerAddress_NoContType(){
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test
    public void DadataCleanerAddress_IncContType(){
        reqDadataCleanerAddressSpecBuilder_NoHead.setContentType(ContentType.XML);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqDadataCleanerAddressSpecBuilder_NoHead.addHeader("X-Secret",DADATA_SECRET_KEY);

        responseBody=sendReqAndGetRespDadataCleanerAddress_NoHead(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test
    public void DadataCleanerAddress_JsonAccess(){
        reqDadataCleanerAddressSpecBuilder.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataCleanerAddressSchema(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test
    public void DadataCleanerAddress_XmlAccess(){
        reqDadataCleanerAddressSpecBuilder.setAccept(ContentType.XML);

        responseBody=sendReqAndGetRespDadataCleanerAddress(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),406);
    }

    @Test
    public void DadataCleanerAddress_EmptyAccess(){
        reqDadataCleanerAddressSpecBuilder.addHeader("Accept","");

        responseBody=sendReqAndGetRespDadataCleanerAddress(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }
}
