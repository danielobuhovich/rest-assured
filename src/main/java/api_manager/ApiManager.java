package api_manager;

import lombok.Getter;
import lombok.Setter;
import modules.google_places.GooglePlacesModel;

public class ApiManager {
    @Getter
    @Setter
    GooglePlacesModel googlePlacesModel=new GooglePlacesModel();

}
