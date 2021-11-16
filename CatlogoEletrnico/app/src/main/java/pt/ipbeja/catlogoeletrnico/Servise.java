package pt.ipbeja.catlogoeletrnico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Servise {
    @GET("users/?format=json")
    Call<List<User>> getAllUsers();

    @GET("users/{idUser}/?format=json")
    Call<User> getUserByid(@Path("idUser") int id);
}
