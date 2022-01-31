package pt.ipbeja.catlogoeletrnico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {
    /*Emuldaor*/ //private static final String BASE_URL = "http://10.0.2.2:8000/api/";

    /*APP*/ private static final String BASE_URL = "http://192.168.2.242:8000/api/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static BiblioService service;

    public static BiblioService getService() {
        if (service == null) {
            service = retrofit.create(BiblioService.class);
        }
        return service;
    }
}
