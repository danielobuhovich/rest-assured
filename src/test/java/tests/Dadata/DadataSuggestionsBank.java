package tests.Dadata;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static constants.Constants.API_TOKEN_DADATA;
import static utils.NetworkCore.FunctionJsonPathRead;
import static utils.NetworkCoreDadataSuggestionsBank.*;

public class DadataSuggestionsBank {

    String defaultBody="{ \"query\": \"сбербанк\" }";
    String requestBody;
    Response responseBody;
    ArrayList<String> values;

    @Test(description = "Request with cyrillic symbols in body")
    public void DadataSuggestionsBank_Cyrillic(){
        responseBody=FunctionDadataSuggBank(defaultBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Сбербанк"));
    }

    @Test(description = "Request with latin symbols in body")
    public void DadataSuggestionsBank_Latin(){
        requestBody="{ \"query\": \"Tinkoff\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Тинькофф"));
    }

    @Test(description = "Request with numbers in body")
    public void DadataSuggestionsBank_Numb(){
        requestBody="{ \"query\": \"1234\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with special symbols in body")
    public void DadataSuggestionsBank_SpecSymb(){
        requestBody="{ \"query\": \"!#$\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request body in upper case")
    public void DadataSuggestionsBank_UpCase(){
        requestBody="{ \"query\": \"СБЕРБАНК\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Сбербанк"));
    }

    @Test(description = "Request body in mixed case")
    public void DadataSuggestionsBank_MixCase(){
        requestBody="{ \"query\": \"сбЕРБанК\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Сбербанк"));
    }

    @Test(description = "Request with empty value in body")
    public void DadataSuggestionsBank_EmptyVal(){
        requestBody="{ \"query\": \" \" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with empty body")
    public void DadataSuggestionsBank_EmptyBody(){
        requestBody="";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with incorrect key in body")
    public void DadataSuggestionsBank_IncKey(){
        requestBody="{ \"qery\": \"сбЕРБанК\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with out of max range body length")
    public void DadataSuggestionsBank_OutOfMaxRange(){
        requestBody="{ \"query\": \"сбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанксбербанк\" }";

        responseBody=FunctionDadataSuggBank(requestBody,413);
    }

    @Test(description = "Request without well formed json in body")
    public void DadataSuggestionsBank_NoWFJson(){
        requestBody="{ \"query: сбЕРБанК\" }";

        responseBody=FunctionDadataSuggBank(requestBody,400);
    }

    @Test(description = "Request with transfered value in body")
    public void DadataSuggestionsBank_TransferBody(){
        requestBody="{ \"query:\":\"c,th,fyr\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with one symbol in body")
    public void DadataSuggestionsBank_OneSymbInBody(){
        requestBody="{\"query\":\"с\"}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNotNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with get method")
    public void DadataSuggestionsBank_Get(){
        requestBody="{\"query\":\"сбербанк\"}";

        responseBody=FunctionDadataSuggBank_Get(requestBody);
    }

    @Test(description = "Request without authorization")
    public void DadataSuggestionsBank_NoAuth(){
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.JSON);

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,401);
    }

    @Test(description = "Request with empty value in authorization header")
    public void DadataSuggestionsBank_EmptyAuth(){
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization","");

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,401);
    }

    @Test(description = "Request with incorrect value in authorization header")
    public void DadataSuggestionsBank_IncAuth(){
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization","Token 80a01fdef1c07b1bf1b32ea8b99cb795cd2cd");

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,403);
    }

    @Test(description = "Request without content type header")
    public void DadataSuggestionsBank_NoContType(){
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,415);
    }

    @Test(description = "Request with incorrect value in content type header")
    public void DadataSuggestionsBank_IncContType(){
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.XML);

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,400);
    }

    @Test(description = "Request without accept header")
    public void DadataSuggestionsBank_NoAccept(){
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.JSON);

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,200);
    }

    @Test(description = "Request with incorrect value in accept header")
    public void DadataSuggestionsBank_IncAccept(){
        reqSpecBuildDadataSuggBank_NoHead.addHeader("Authorization",API_TOKEN_DADATA);
        reqSpecBuildDadataSuggBank_NoHead.setContentType(ContentType.JSON);
        reqSpecBuildDadataSuggBank_NoHead.setAccept(ContentType.JSON);

        responseBody=FunctionDadataSuggBank_NoHead(defaultBody,200);
    }


    @Test(description = "Searching by BIK")
    public void DadataSuggestionsBank_SearchBIK(){
        requestBody="{ \"query\": \"044525974\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Тинькофф"));
    }

    @Test(description = "Searching by not full BIK")
    public void DadataSuggestionsBank_SearchNotFullBIK(){
        requestBody="{ \"query\": \"04452\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by SWIFT")
    public void DadataSuggestionsBank_SearchSwift(){
        requestBody="{ \"query\": \"TICSRUMMXXX\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Тинькофф"));
    }

    @Test(description = "Searching by not full SWIFT")
    public void DadataSuggestionsBank_SearchNotFullSwift(){
        requestBody="{ \"query\": \"TICSRUM\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by Inn")
    public void DadataSuggestionsBank_SearchInn(){
        requestBody="{ \"query\": \"7710140679\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("suggestions[0]").contains("Тинькофф"));
    }

    @Test(description = "Searching by not full Inn")
    public void DadataSuggestionsBank_SearchNotFullInn(){
        requestBody="{ \"query\": \"77101406\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by Kpp")
    public void DadataSuggestionsBank_SearchKpp(){
        requestBody="{ \"query\": \"771301001\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by not full Kpp")
    public void DadataSuggestionsBank_SearchNotFullKpp(){
        requestBody="{ \"query\": \"7713010\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by address")
    public void DadataSuggestionsBank_SearchAddress(){
        requestBody="{ \"query\": \"ул Хуторская 2, д 38а\" }";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("Тинькофф"));
    }

    @Test(description = "Searching by bank status")
    public void DadataSuggestionsBank_BankStatus(){
        requestBody="{\"query\": \"банк\",\"status\": [\"LIQUIDATING\"]}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        values= FunctionJsonPathRead(responseBody,"$..status");

        Assert.assertTrue(values.contains("LIQUIDATING"));
        Assert.assertFalse(values.contains("ACTIVE"));
    }

    @Test(description = "Request with low case in body")
    public void DadataSuggestionsBank_BankStatusLowCase(){
        requestBody="{\"query\": \"банк\",\"status\": [\"liquidating\"]}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        values= FunctionJsonPathRead(responseBody,"$..status");

        Assert.assertTrue(values.contains("LIQUIDATING"));
        Assert.assertFalse(values.contains("ACTIVE"));
    }

    @Test(description = "Request with incorrect bank status")
    public void DadataSuggestionsBank_BankincStatus(){
        requestBody="{\"query\": \"банк\",\"status\": [\"liguidating\"]}";

        responseBody=FunctionDadataSuggBank(requestBody,400);
    }

    @Test(description = "Request with empty bank status")
    public void DadataSuggestionsBank_EmptyBankStatus(){
        requestBody="{\"query\": \"банк\",\"status\": [\" \"]}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with bank type in body")
    public void DadataSuggesstionsBank_BankType(){
        requestBody="{\n" +
                "    \"query\": \"банк\", \n" +
                "    \"type\": [\"BANK_BRANCH\"],\n" +
                "    \"count\":20\n"+
                "}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..type");

        Assert.assertFalse(values.contains("BANK"));
        Assert.assertTrue(values.contains("BANK_BRANCH"));
        Assert.assertFalse(values.contains("NKO"));
        Assert.assertFalse(values.contains("NKO_BRANCH"));
        Assert.assertFalse(values.contains("OTHER"));
    }

    @Test(description = "Request with lower case bank type in body")
    public void DadataSuggesstionsBank_BankTypeLowCase(){
        requestBody="{\n" +
                "    \"query\": \"банк\", \n" +
                "    \"type\": [\"bank_branch\"],\n" +
                "    \"count\":20\n"+
                "}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        values=FunctionJsonPathRead(responseBody,"$..type");

        Assert.assertFalse(values.contains("BANK"));
        Assert.assertTrue(values.contains("BANK_BRANCH"));
        Assert.assertFalse(values.contains("NKO"));
        Assert.assertFalse(values.contains("NKO_BRANCH"));
        Assert.assertFalse(values.contains("OTHER"));
    }

    @Test(description = "Request with incorrect bank type")
    public void DadataSuggesstionsBank_IncBankType(){
        requestBody="{\n" +
                "    \"query\": \"банк\", \n" +
                "    \"type\": [\"bankbranch\"],\n" +
                "    \"count\":20\n"+
                "}";

        responseBody=FunctionDadataSuggBank(requestBody,400);
    }

    @Test(description = "Request with empty bank type in body")
    public void DadataSuggesstionsBank_EmptyBankType(){
        requestBody="{\n" +
                "    \"query\": \"банк\", \n" +
                "    \"type\": [\" \"],\n" +
                "    \"count\":20\n"+
                "}";

        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertNull(responseBody.jsonPath().getString("suggestions[0]"));
    }

    @Test(description = "Request with location boost in body")
    public void DadataSuggestions_BankBoost(){
        requestBody="{\n" +
                "        \"query\": \"альфа\",\n" +
                "        \"locations_boost\": [\n" +
                "            {\n" +
                "                \"kladr_id\":\"2700000100005480047\"\n" +
                "            }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ХАБАРОВСКИЙ"));
    }

    @Test(description = "Request with not full location boost in body")
    public void DadataSuggestions_BankBoostNotFull(){
        requestBody="{\n" +
                "        \"query\": \"альфа\",\n" +
                "        \"locations_boost\": [\n" +
                "            {\n" +
                "                \"kladr_id\":\"2700000100005480\"\n" +
                "            }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ХАБАРОВСКИЙ"));
    }

    @Test(description = "Request with incorrect location boost in body")
    public void DadataSuggestions_BankBoostInc(){
        requestBody="{\n" +
                "        \"query\": \"альфа\",\n" +
                "        \"locations_boost\": [\n" +
                "            {\n" +
                "                \"kladr_id\":\"asd\"\n" +
                "            }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ХАБАРОВСКИЙ"));
    }

    @Test(description = "Request with empty location boost in body")
    public void DadataSuggestions_EmptyBankBoost(){
        requestBody="{\n" +
                "        \"query\": \"альфа\",\n" +
                "        \"locations_boost\": [\n" +
                "            {\n" +
                "                \"kladr_id\":\" \"\n" +
                "            }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ХАБАРОВСКИЙ"));
    }

    @Test(description = "Request with bank location in body")
    public void DadataSuggestions_BankLocation(){
        requestBody="{\n" +
                "    \"query\": \"про\",\n" +
                "    \"locations\":[\n" +
                "        {\n" +
                "            \"kladr_id\":\"7700000000071050001\"\n" +
                "        }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ПроКоммерцБанк"));
    }

    @Test(description = "Request with not full bank location in body")
    public void DadataSuggestions_BankLocationNotFull(){
        requestBody="{\n" +
                "    \"query\": \"про\",\n" +
                "    \"locations\":[\n" +
                "        {\n" +
                "            \"kladr_id\":\"7700000000071050\"\n" +
                "        }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ПроКоммерцБанк"));
    }

    @Test(description = "Request with incorrect bank location in body")
    public void DadataSuggestions_IncBankLocation(){
        requestBody="{\n" +
                "    \"query\": \"про\",\n" +
                "    \"locations\":[\n" +
                "        {\n" +
                "            \"kladr_id\":\"asd\"\n" +
                "        }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ПроКоммерцБанк"));
    }

    @Test(description = "Request with null bank location in body")
    public void DadataSuggestions_NullBankLocation(){
        requestBody="{\n" +
                "    \"query\": \"про\",\n" +
                "    \"locations\":[\n" +
                "        {\n" +
                "            \"kladr_id\":\" \"\n" +
                "        }]\n" +
                "}";
        responseBody=FunctionDadataSuggBank(requestBody,200);

        Assert.assertTrue(responseBody.jsonPath().getString("").contains("ПроКоммерцБанк"));
    }
}
