package modules.google_places;

import io.restassured.http.Method;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import utils.NetworkCore;

import java.util.HashMap;

import static constants.Constants.Endpoint.GOOGLE_PLACE_ENDPOINT_SEARCH;
import static constants.Constants.Path.GOOGLE_PLACE_PATH;
import static constants.Constants.ServerName.GOOGLE_PLACE_SERVER;

public class GooglePlacesModel extends NetworkCore {
    @Setter
    @Getter
    @Builder
    public static class RequestModel{
        private String key;
        private String input;
        private String inputtype;
    }

    public GooglePlacesModel search(RequestModel model, Method methd, int statusCode){
        HashMap requestParams=new HashMap();
        requestParams.put("key",model.getKey());
        requestParams.put("input",model.getInput());
        requestParams.put("inputtype",model.getInputtype());
        requestSpecBuilder.setBaseUri(GOOGLE_PLACE_SERVER + GOOGLE_PLACE_PATH + GOOGLE_PLACE_ENDPOINT_SEARCH)
                .addQueryParams(requestParams);
        sendRequestAndGetResponse(methd, statusCode);
        return this;
    }

}
