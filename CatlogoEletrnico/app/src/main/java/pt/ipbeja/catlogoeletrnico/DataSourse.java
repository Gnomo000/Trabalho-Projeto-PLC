package pt.ipbeja.catlogoeletrnico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSourse {
    private static final String BASE_URL = "https://swapi.dev/api/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Servise service;

    public static Servise getService() {
        if (service == null) {
            service = retrofit.create(Servise.class);
        }
        return service;
    }
}
