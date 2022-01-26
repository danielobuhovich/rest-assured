package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.NetworkCore.*;


public class DadataName {

    String defaultName ="[\"Срегей владимерович иванов\"]";
    String requestBody;

    Response responseBody;


    @Test(description = "1. Positive test of name searching in dadata cleaner")
    public  void SearchDadataName_Correct(){

        sendRequestAndGetResponseDadata(defaultName,200, "М");
    }


    @Test(description = "2. Sending oversized request body in name searching request in dadata cleaner")
    public  void SearchDadataName_Oversized(){

        requestBody="[\"СрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичвановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегейвладимеровичивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович ивановСрегей владимерович иванов\"]";

        sendRequestAndGetResponseDadata(requestBody,200);
    }


    @Test(description = "3. Sending empty request body in name searching request in dadata cleaner")
    public  void SearchDadataName_Null(){

        requestBody="[\" \"]";

        sendRequestAndGetResponseDadata(requestBody,200);
    }


    @Test(description = "4. Sending numbers in request body")
    public  void SearchDadataName_SendNum(){

        requestBody="[\"123 456 7890\"]";

        sendRequestAndGetResponseDadata(requestBody,400);
    }


    @Test(description = "5. Sending special characters in request body")
    public  void SearchDadataName_SendSpecChar(){

        requestBody="[\"!@# $%^ *&_=\"]";

        sendRequestAndGetResponseDadata(requestBody,400);
    }


    @Test(description = "6. Sending correct latin name in request body")
    public  void SearchDadataName_CorrectLatinName_Male(){

        requestBody="[\"John Lenon\"]";

        sendRequestAndGetResponseDadata(requestBody,200,"М");
    }


    @Test(description = "7. Sending correct latin name in request body")
    public  void SearchDadataName_CorrectLatinName_Female(){

        requestBody="[\"Emily Smith\"]";

        sendRequestAndGetResponseDadata(requestBody,200,"Ж");
    }


    @Test(description = "8. Sending mixed latin characters in request body")
    public  void SearchDadataName_MixedLatinName(){

        requestBody="[\"Jonh Lneno\"]";

        sendRequestAndGetResponseDadata(requestBody,200,"М");
    }


    @Test(description = "9. Sending request body in upper case")
    public  void SearchDadataName_SendUpCase(){

        requestBody="[\"СРЕГЕЙ ВЛАДИМЕРОВИЧ ИВАНОВ\"]";

        sendRequestAndGetResponseDadata(requestBody,200);
    }


    @Test(description = "10. Sending request body in mixed case")
    public  void SearchDadataName_SendMixedCase(){

        requestBody="[\"СРегеЙ ВлаДИМЕроВич ивАНоВ\"]";

        sendRequestAndGetResponseDadata(requestBody,200);
    }


    @Test(description = "11. Check male gender")
    public  void SearchDadataName_MaleGender(){

        sendRequestAndGetResponseDadata(defaultName,"М");
    }


    @Test(description = "12. Check female gender")
    public  void SearchDadataName_FemaleGender(){

        requestBody="[\"Анна владимеровна иванова\"]";

        sendRequestAndGetResponseDadata(requestBody,"Ж");
    }

    @Test(description = "13. Check uncertain gender")
    public  void SearchDadataName_UncertainGender(){

        requestBody="[\"биндей женя\"]";

        sendRequestAndGetResponseDadata(requestBody,"НД");
    }


    @Test(description = "14. Request with incorrect content-type header")
    public  void SearchDadataName_IncorrectCT(){

        reqDadataCleanerFioSpecBuilder.setContentType(ContentType.XML);

        sendRequestAndGetResponseDadata(defaultName,415);
    }


    @Test(description = "15. Request without content-type header")
    public  void SearchDadataName_WithoutCT(){

        sendRequestAndGetResponseDadataNoConttype(defaultName,415);
    }


    @Test(description = "16. Request without authorization header")
    public  void SearchDadataName_WithoutAuth(){

        sendRequestAndGetResponseDadataNoAuth(defaultName,401);
    }


    @Test(description = "17. Request with incorrect content in authorization header")
    public  void SearchDadataName_IncorrectAuth(){

        reqDadataCleanerFioSpecBuilderNoAuth.addHeader("Authorization","Token da020307c38dff939352a52572cea1ee43d3");

        sendRequestAndGetResponseDadataNoAuth(defaultName,403);
    }


    @Test(description = "18. Request with empty authorization header")
    public  void SearchDadataName_EmptyAuth(){

        reqDadataCleanerFioSpecBuilderNoAuth.addHeader("Authorization","");

        sendRequestAndGetResponseDadataNoAuth(defaultName,401);
    }


    @Test(description = "19. Request without X-Secret header")
    public  void SearchDadataName_WithoutSecret(){

        sendRequestAndGetResponseDadataNoKey(defaultName,401);
    }


    @Test(description = "20. Request with incorrect content in X-Secret header")
    public  void SearchDadataName_IncorrectSecret(){

        reqDadataCleanerFioSpecBuilderNoKey.addHeader("X-Secret","d9529a335ab0ddeaff337d291d86237a067b7");

        sendRequestAndGetResponseDadataNoKey(defaultName,403);
    }


    @Test(description = "21. Request with empty X-Secret header")
    public  void SearchDadataName_EmptySecret(){

        reqDadataCleanerFioSpecBuilderNoKey.addHeader("X-Secret","");

        sendRequestAndGetResponseDadataNoKey(defaultName,401);
    }


    @Test(description = "22. Request with incorrect method")
    public  void SearchDadataName_IncorrectMethod(){

        sendRequestAndGetResponseDadataGet(defaultName,405);
    }


    @Test(description = "23. Request with XML in Accept header")
    public  void SearchDadataName_XMLAccept(){

        reqDadataCleanerFioSpecBuilder.setAccept(ContentType.XML);

        sendRequestAndGetResponseDadata(defaultName,406);
    }

    @Test(description = "24. Request without well formed json")
    public  void SearchDadataName_WellJson(){

        reqDadataCleanerFioSpecBuilder.setAccept(ContentType.XML);

        String requestBody="[Срегей владимерович иванов]";

        sendRequestAndGetResponseDadata(requestBody,400);
    }

//    @Test(description = "25. Returning response body and check step by step")
//    public  void SearchDadataName_Return(){
//
//        responseBody=sendRequestAndGetResponseDadata(defaultName);
//
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].source"),"Срегей владимерович иванов");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].result"),"Иванов Сергей Владимирович");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].result_genitive"),"Иванова Сергея Владимировича");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].result_dative"),"Иванову Сергею Владимировичу");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].result_ablative"),"Ивановым Сергеем Владимировичем");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].surname"),"Иванов");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].name"),"Сергей");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].patronymic"),"Владимирович");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].gender"),"М");
//        Assert.assertEquals(responseBody.jsonPath().getString("[0].qc"),"1");
//    }
}
