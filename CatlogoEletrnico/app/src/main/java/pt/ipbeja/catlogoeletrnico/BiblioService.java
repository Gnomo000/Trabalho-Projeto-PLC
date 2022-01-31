package pt.ipbeja.catlogoeletrnico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BiblioService {
    @POST("users/")
    Call<User> addUser(@Body User user);

    @POST("requisitions/")
    Call<Request> addRequest(@Body Request request);

    @GET("users/{email}")
    Call<List<User>> getUserByEmail(@Path("email") String email);

    @GET("users/{email}/{password}")
    Call<List<User>> getUserByPasswordAndEmail(@Path("email") String email, @Path("password") String password);

    @GET("requisitions/{email}")
    Call<List<Request>> getRequestListByEmail(@Path("email") String email);

    @PUT("requisitions/{id}/{status}")
    Call<Request> updateRequestStatus(@Path("id") int id, @Path("status") String status);

    @GET("books/quantity")
    Call<List<Book>> getAllBooksMoreZero();

    @GET("books/")
    Call<List<Book>> getAllBooks();

    @GET("booksType/{string}")
    Call<List<Book>> getBookByTitleList(@Path("string") String string);

    @GET("booksId/{id}")
    Call<List<Book>> getBookById(@Path("id") int id);

    @GET("books/{title}")
    Call<List<Book>> getBookByTitle(@Path("title") String title);

    @GET("requisitions/{email}/{string}")
    Call<List<Request>> getRequestByTitle(@Path("email") String email,@Path("string") String string);

    @GET("requisitionsId/{id}")
    Call<List<Request>> getRequestById(@Path("id") int id);

    @PUT("booksQuantity/{title}/{quantity}")
    Call<Book> updateRequestStatus(@Path("title") String id, @Path("quantity") int quantity );
}
