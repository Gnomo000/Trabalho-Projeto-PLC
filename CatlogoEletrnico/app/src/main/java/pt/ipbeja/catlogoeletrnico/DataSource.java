package pt.ipbeja.catlogoeletrnico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {
    private static final String BASE_URL = "https://my-json-server.typicode.com/Gnomo000/21707-DanielRodrigues-TP-PDM2-2021-2022/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static BooksService service;

    public static BooksService getService() {
        if (service == null) {
            service = retrofit.create(BooksService.class);
        }
        return service;
    }
}
