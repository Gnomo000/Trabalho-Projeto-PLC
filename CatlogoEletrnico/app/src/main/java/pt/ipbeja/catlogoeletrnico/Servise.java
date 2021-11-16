package pt.ipbeja.catlogoeletrnico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Servise {
    @GET("users/?format=json")
    Call<List<User>> getAllUsers();

    @GET("users/")
    Call<List<User>> getUserByEmail(@Query("email") String email);

    @GET("users/")
    Call<List<User>> getUserByPasswordAndEmail(@Query("password") String pass,@Query("email") String email);
}
