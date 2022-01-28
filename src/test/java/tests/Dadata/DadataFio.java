package tests.Dadata;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static utils.NetworkCoreDadataFio.*;

public class DadataFio {

    Response responseBody;
    String jsonBody;
    String requestBody;
    String defaultBody="{\"query\": \"Сергей\"}";

    @Test(description = "Positive test of FIO searching in dadata suggestions with default count")
    public  void SearchDadataFIO_Positive(){

        responseBody=sendReqAndGetRespDadataSuggestFio(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),10);
        Assert.assertTrue(values.contains("Сергей"));
    }

    @Test(description = "Expecting response body will contain not male gender")
    public  void SearchDadataFIO_FemaleReq(){

        requestBody="{\"query\": \"Иванова\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        Assert.assertFalse(genders.contains("MALE"));
    }

    @Test(description = "Expecting response body will contain not female gender")
    public  void SearchDadataFIO_MaleReq(){

        requestBody="{\"query\": \"Николаевич\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        Assert.assertFalse(genders.contains("FEMALE"));
    }

    @Test(description = "Request body with special characters")
    public  void SearchDadataFIO_SpecSymb(){

        requestBody="{\"query\": \"!@# %^& &^%\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> allBody=JsonPath.read(jsonBody,"*");

        Assert.assertEquals(responseBody.getStatusCode(),400);

        Assert.assertTrue(allBody.contains("!@# %^& &^%"));
    }

    @Test(description = "Request body with numbers")
    public  void SearchDadataFIO_Numbers(){

        requestBody="{\"query\": \"123 456 7890\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> allBody=JsonPath.read(jsonBody,"*");

        Assert.assertEquals(responseBody.getStatusCode(),400);

        Assert.assertTrue(allBody.contains("123 456 7890"));
    }

    @Test(description = "Request with upper case latin characters")
    public  void SearchDadataFIO_UpCase(){

        requestBody="{\"query\": \"СЕРГЕЙ\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertTrue(values.contains("Сергей"));
    }

    @Test(description = "Request with lower case latin characters")
    public  void SearchDadataFIO_LowCase(){

        requestBody="{\"query\": \"сергей\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertTrue(values.contains("Сергей"));
    }

    @Test(description = "Request with mixed case latin characters")
    public  void SearchDadataFIO_MixCase(){

        requestBody="{\"query\": \"СеРгЕй\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertTrue(values.contains("Сергей"));
    }

    @Test(description = "Request with english name")
    public  void SearchDadataFIO_English(){

        requestBody="{\"query\": \"John\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> un_values=JsonPath.read(jsonBody,"$..unrestricted_value");

        assertTrue(un_values.contains("John"));
    }

    @Test(description = "Request with unknown gender")
    public  void SearchDadataFIO_UnkGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\"UNKNOWN\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        assertTrue(genders.contains("MALE"));
        assertTrue(genders.contains("FEMALE"));
        assertTrue(genders.contains("UNKNOWN"));
    }

    @Test(description = "Request with male gender")
    public  void SearchDadataFIO_MaleGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\"MALE\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        assertTrue(genders.contains("MALE"));
        Assert.assertFalse(genders.contains("FEMALE"));
        assertTrue(genders.contains("UNKNOWN"));
    }

    @Test(description = "Request with female gender")
    public  void SearchDadataFIO_FemGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\"FEMALE\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        Assert.assertFalse(genders.contains("MALE"));
        assertTrue(genders.contains("FEMALE"));
        assertTrue(genders.contains("UNKNOWN"));
    }

    @Test(description = "Request with empty gender")
    public  void SearchDadataFIO_EmptyGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\" \"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        Assert.assertTrue(genders.contains("MALE"));
        assertTrue(genders.contains("FEMALE"));
        assertTrue(genders.contains("UNKNOWN"));
    }

    @Test(description = "Request with int value in gender")
    public  void SearchDadataFIO_NumbInGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":123}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request with gender in russian")
    public  void SearchDadataFIO_KirilInGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\"МУЖЧИНА\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Searching with gender in lower case")
    public  void SearchDadataFIO_LowCaseGender(){

        requestBody="{\"query\": \"Сергей\", \"gender\":\"female\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> genders=JsonPath.read(jsonBody,"$..gender");

        Assert.assertFalse(genders.contains("MALE"));
        assertTrue(genders.contains("FEMALE"));
        assertTrue(genders.contains("UNKNOWN"));
    }

    @Test(description = "Transfer text to russian")
    public void SearchDadataFIO_TransferRu(){

        requestBody="{\"query\": \"cthutq\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        assertTrue(values.contains("Сергей"));
    }

    @Test(description = "Transfer mixed text in request body")
    public void SearchDadataFIO_TransferMix(){

        requestBody="{\"query\": \"cthutq Михайлович Bdfyjd\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");
        assertTrue(values.contains("Сергей Михайлович Иванов"));

        ArrayList<String> surnames=JsonPath.read(jsonBody,"$..surname");
        assertTrue(surnames.contains("Иванов"));

        ArrayList<String> names=JsonPath.read(jsonBody,"$..name");
        assertTrue(names.contains("Сергей"));

        ArrayList<String> patronymic=JsonPath.read(jsonBody,"$..patronymic");
        assertTrue(patronymic.contains("Михайлович"));
    }

    @Test(description = "Transfer text to eng")
    public void SearchDadataFIO_TransferEng(){

        requestBody="{\"query\": \"Ощрт Дутщт\"}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();

        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        assertTrue(values.contains("John Lenon"));
    }

    @Test(description = "Request with empty value in body")
    public void SearchDadataFIO_EmptyValue(){

        requestBody="{\"query\": \"\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,200);
    }

    @Test(description = "Request with out of max range request body")
    public void SearchDadataFIO_OutOfMaxValue(){

        requestBody="{\"query\": \"СергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергейСергей\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,413);
    }

    @Test(description = "Request with empty body")
    public void SearchDadataFIO_EmptyBody(){

        requestBody="";

        sendReqAndGetRespDadataSuggestFio(requestBody,200);
    }

    @Test(description = "Request with negative count")
    public  void SearchDadataFIO_NegativeCount(){

        requestBody="{\"query\": \"Сергей\", \"count\":-2}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),10);
    }

    @Test(description = "Request with empty count")
    public  void SearchDadataFIO_EmptyCount(){

        requestBody="{\"query\": \"Сергей\", \"count\": }";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),10);
    }

    @Test(description = "Request with zero in count")
    public  void SearchDadataFIO_ZeroCount(){

        requestBody="{\"query\": \"Сергей\", \"count\": 0}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),10);
    }

    @Test(description = "Request with min value in count")
    public  void SearchDadataFIO_MinCount(){

        requestBody="{\"query\": \"Сергей\", \"count\":1}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),1);
    }

    @Test(description = "Request with max value in count")
    public  void SearchDadataFIO_MaxCount(){

        requestBody="{\"query\": \"Иван\", \"count\":20}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),20);
    }

    @Test(description = "Request with out of max range in count")
    public  void SearchDadataFIO_OutOfMaxCount(){

        requestBody="{\"query\": \"Иван\", \"count\":22}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..value");

        Assert.assertEquals(values.size(),20);
    }

    @Test(description = "Request with special characters in count")
    public  void SearchDadataFIO_SpecSymbInCount(){

        requestBody="{\"query\": \"Сергей\", \"count\":\"!@\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request with latin in count")
    public  void SearchDadataFIO_LatinInCount(){

        requestBody="{\"query\": \"Сергей\", \"count\":\"asd\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request with cyrillic in count")
    public  void SearchDadataFIO_CyrillicInCount(){

        requestBody="{\"query\": \"Сергей\", \"count\":\"лис\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request with incorrect key in request body")
    public  void SearchDadataFIO_IncKey(){

        requestBody="{\"qery\": \"Сергей\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request without well formed json")
    public  void SearchDadataFIO_WithoutWFJson(){

        requestBody="{query\": Сергей\"}";

        sendReqAndGetRespDadataSuggestFio(requestBody,400);
    }

    @Test(description = "Request without authorization")
    public  void SearchDadataFIO_NoAuth(){

        responseBody=sendReqAndGetRespDadataSuggestFio_NoAuth(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request with incorrect token in authorization")
    public  void SearchDadataFIO_IncAuth(){


        reqDadataSuggestionsFioSpecBuilder_NoAuth.addHeader("Authorization","Token f3374ee9d5c69869827fd83bf831f0188d9e8");

        responseBody=sendReqAndGetRespDadataSuggestFio_NoAuth(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),403);
    }

    @Test(description = "Request with empty value in authorization")
    public  void SearchDadataFIO_EmptyAuth(){


        reqDadataSuggestionsFioSpecBuilder_NoAuth.addHeader("Authorization","");

        responseBody=sendReqAndGetRespDadataSuggestFio_NoAuth(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),401);
    }

    @Test(description = "Request without content type")
    public  void SearchDadataFIO_NoContType(){

        reqDadataSuggestionsFioSpecBuilder_NoTypes.setAccept(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestFio_NoTypes(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),415);
    }

    @Test(description = "Request with incorrect content type")
    public  void SearchDadataFIO_IncContType(){

        reqDadataSuggestionsFioSpecBuilder_NoTypes.setAccept(ContentType.JSON);
        reqDadataSuggestionsFioSpecBuilder_NoTypes.setContentType(ContentType.XML);

        responseBody=sendReqAndGetRespDadataSuggestFio_NoTypes(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),400);
    }

    @Test(description = "Request without accept")
    public  void SearchDadataFIO_NoAccept(){

        reqDadataSuggestionsFioSpecBuilder_NoTypes.setContentType(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestFio_NoTypes(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Request with incorrect accept")
    public  void SearchDadataFIO_IncAccept(){

        reqDadataSuggestionsFioSpecBuilder_NoTypes.setAccept(ContentType.XML);
        reqDadataSuggestionsFioSpecBuilder_NoTypes.setContentType(ContentType.JSON);

        responseBody=sendReqAndGetRespDadataSuggestFio_NoTypes(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);
    }

    @Test(description = "Get request")
    public  void SearchDadataFIO_Get(){

        responseBody=sendReqAndGetRespDadataSuggestFio_Get(defaultBody);

        Assert.assertEquals(responseBody.getStatusCode(),405);
    }

    @Test(description = "Positive test of FIO searching in dadata suggestions with parts(name)")
    public  void SearchDadataFIO_PartsName(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": [\"NAME\"]\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> names=JsonPath.read(jsonBody,"$..name");

        String resultArraylist = String.join(",", names.toArray(new String[0]));

        Assert.assertTrue(resultArraylist.contains("Иван"));
    }

    @Test(description = "Positive test of FIO searching in dadata suggestions with parts(surname)")
    public  void SearchDadataFIO_PartsSurname(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": [\"SURNAME\"]\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> surnames=JsonPath.read(jsonBody,"$..surname");

        String resultArraylist = String.join(",", surnames.toArray(new String[0]));

        Assert.assertTrue(resultArraylist.contains("Иван"));
    }

    @Test(description = "Positive test of FIO searching in dadata suggestions with parts(surname)")
    public  void SearchDadataFIO_PartsPatronymic(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": [\"PATRONYMIC\"]\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> patronymics=JsonPath.read(jsonBody,"$..patronymic");

        String resultArraylist = String.join(",", patronymics.toArray(new String[0]));

        Assert.assertTrue(resultArraylist.contains("Иван"));
    }

    @Test(description = "Request with empty parts in request body")
    public  void SearchDadataFIO_EmptyParts(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": []\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> values=JsonPath.read(jsonBody,"$..unrestricted_value");

        Assert.assertTrue(values.contains("Иван"));
    }

    @Test(description = "Request with parts in lower case in request body")
    public  void SearchDadataFIO_LowCaseParts(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": [\"surname\"]\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> names=JsonPath.read(jsonBody,"$..name");
        ArrayList<String> surnames=JsonPath.read(jsonBody,"$..surname");
        ArrayList<String> patronymics=JsonPath.read(jsonBody,"$..patronymic");

        String namesString = String.join(",", names.toArray(new String[0]));
        String surnamesString = String.join(",", surnames.toArray(new String[0]));
        String patronymicsString = String.join(",", patronymics.toArray(new String[0]));


        Assert.assertFalse(namesString.contains("Иван"));
        Assert.assertTrue(surnamesString.contains("Иван"));
        Assert.assertFalse(patronymicsString.contains("Иван"));
    }

    @Test(description = "Request with incorrect parts in request body")
    public  void SearchDadataFIO_IncParts(){

        requestBody="{\n" +
                "  \"query\": \"Иван\",\n" +
                "  \"parts\": [\"secondname\"]\n" +
                "}";

        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);

        Assert.assertEquals(responseBody.getStatusCode(),200);

        jsonBody=responseBody.asString();
        ArrayList<String> names=JsonPath.read(jsonBody,"$..name");
        ArrayList<String> surnames=JsonPath.read(jsonBody,"$..surname");
        ArrayList<String> patronymics=JsonPath.read(jsonBody,"$..patronymic");

        String namesString = String.join(",", names.toArray(new String[0]));
        String surnamesString = String.join(",", surnames.toArray(new String[0]));
        String patronymicsString = String.join(",", patronymics.toArray(new String[0]));


        Assert.assertFalse(namesString.contains("Иван"));
        Assert.assertTrue(surnamesString.contains("Иван"));
        Assert.assertFalse(patronymicsString.contains("Иван"));
    }


//jayway examples

//    @Test(description = "Return")
//    public  void SearchDadataFIO_return(){
//
//        requestBody="{\"query\": \"Сергей\"}";
//
//        responseBody=sendReqAndGetRespDadataSuggestFio(requestBody);
//
//        String jsonBody=responseBody.asString(); //все тело ответа в string
//        System.out.println("jsonBody -> "+jsonBody);
//
//        String completeResponse= JsonPath.read(jsonBody,"$").toString(); //полное тело запроса в string
//        System.out.println("completeResponse -> "+completeResponse);
//
//
//        String firstArrayItem=JsonPath.read(jsonBody,"$.suggestions[0]").toString(); //первый элемент массива в string
//        System.out.println("firstArrayItem -> "+firstArrayItem);
//
//        String allGenders=JsonPath.read(jsonBody,"$..gender").toString(); //все поля gender в string
//        System.out.println("allIds -> "+allGenders);
//
//        String firstGender=JsonPath.read(jsonBody,"$.suggestions[0].data.gender").toString(); //значение поля gender первого элемента массива
//        System.out.println("firstId -> "+firstGender);
//    }
}
