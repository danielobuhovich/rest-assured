package constants;

import utils.UtilsMethods;

public class Constants {

    //domain name
    public static class ServerName{
        public static String GOOGLE_PLACE_SERVER="https://maps.googleapis.com/";
        public static String DADATA_CLEANER_SERVER="https://cleaner.dadata.ru";
        public static String DADATA_SUGGESTION_SERVER="https://suggestions.dadata.ru";
        public static String DADATA_SERVER="https://dadata.ru/";
    }

    //path
    public static class Path{
        public static String GOOGLE_PLACE_PATH="maps/api/place/";
        public static String DADATA_SUGGESTION_PATH="/suggestions/api/4_1/rs/suggest";
        public static String DADATA_CLEANER_PATH="/api/v1/clean";
        public static String DADATA_STAT_PATH="api/v2/stat/";
    }

    //endpoint
    public static class Endpoint{
        public static String GOOGLE_PLACE_ENDPOINT_SEARCH="findplacefromtext/json";
        public static String DADATA_ENDPOINT_CLEANER_NAME="/name";
        public static String DADATA_ENDPOINT_SUGGESTIONS_FIO="/fio";
        public static String DADATA_ENDPOINT_SUGGESTIONS_COUNTRY="/country";
        public static String DADATA_ENDPOINT_CLEANER_ADDRESS="/address";
        public static String DADATA_ENDPOINT_SUGGESTION_CURRENCY="/currency";
        public static String DADATA_ENDPOINT_EMAIL="/email";
        public static String DADATA_ENDPOINT_SUGGESTIONS_BANK="/bank";
        public static String DADATA_ENDPOINT_STAT_DAILY="daily";
    }

    public static final String API_TOKEN= UtilsMethods.getValue("TOKEN");
    public static final String API_TOKEN_DADATA=UtilsMethods.getValue("TOKEN_DADATA");
    public static final String DADATA_SECRET_KEY=UtilsMethods.getValue("SECRET_KEY");
}
