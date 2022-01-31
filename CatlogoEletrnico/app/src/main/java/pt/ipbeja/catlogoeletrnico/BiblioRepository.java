package pt.ipbeja.catlogoeletrnico;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiblioRepository {

    private final Context context;
    private android.os.Handler handler = new android.os.Handler();

    public BiblioRepository(Context context) {
        this.context = context;
    }

    public LiveData<User> getUserByEmail(Context context, String email) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        BiblioService service = DataSource.getService();
        service.getUserByEmail(email).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> users = response.body();
                    if (users.size() > 0) {
                        userMutableLiveData.postValue(response.body().get(0));
                    }else {
                        userMutableLiveData.postValue(null);
                        Toast toast = Toast.makeText(context, "Erro! Email existentes",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else {
                    userMutableLiveData.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                userMutableLiveData.postValue(null);
            }
        });

        return userMutableLiveData;
    }

    public LiveData<User> getUserByPasswordAndEmail(Context context, String email, String password) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        BiblioService service = DataSource.getService();
        service.getUserByPasswordAndEmail(email,password).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    if (users.size() > 0) {
                        userMutableLiveData.postValue(response.body().get(0));
                    }else {
                        userMutableLiveData.postValue(null);
                        Toast toast = Toast.makeText(context, "Erro! Email/Palavra-chave incorretos/inexistentes",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userMutableLiveData.postValue(null);
            }
        });

        return userMutableLiveData;
    }

    public void createUser(User user) {
        BiblioService service = DataSource.getService();
        service.addUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user1 = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<List<Request>> getRequestListByEmail(Context context, String email) {
        MutableLiveData<List<Request>> userMutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                service.getRequestListByEmail(email).enqueue(new Callback<List<Request>>() {
                    @Override
                    public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                        if (response.isSuccessful()) {
                            List<Request> requests = response.body();
                            if (requests.size() > 0) {
                                userMutableLiveData.postValue(response.body());
                            }else {
                                userMutableLiveData.postValue(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Request>> call, Throwable t) {
                        userMutableLiveData.postValue(null);
                    }
                });
            }
        },3000);

        return userMutableLiveData;
    }

    public void updateRequestStatus(int id, String status) {
        BiblioService service = DataSource.getService();
        Call<Request> call = service.updateRequestStatus(id,status);
        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Request request1 = response.body();
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<List<Book>> getAllBooksMoreZero(){
        MutableLiveData<List<Book>> bookMutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                Call<List<Book>> call = service.getAllBooksMoreZero();
                call.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        bookMutableLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        bookMutableLiveData.postValue(null);
                    }
                });
            }
        },3000);
        return bookMutableLiveData;
    }

    public LiveData<List<Book>> getAllBooks(){
        MutableLiveData<List<Book>> mutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                Call<List<Book>> call = service.getAllBooks();
                call.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        mutableLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        mutableLiveData.postValue(null);
                    }
                });
            }
        },3000);
        return mutableLiveData;
    }

    public LiveData<List<Book>> getBookByTitleList(String string){
        MutableLiveData<List<Book>> mutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                Call<List<Book>> call = service.getBookByTitleList(string);
                call.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        mutableLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        mutableLiveData.postValue(null);
                    }
                });
            }
        },3000);
        return mutableLiveData;
    }

    public LiveData<List<Book>> getBookById(int id){
        MutableLiveData<List<Book>> mutableLiveData = new MutableLiveData<>();
        BiblioService service = DataSource.getService();
        Call<List<Book>> call = service.getBookById(id);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<Book> getBookByTitle(String title){
        MutableLiveData<Book> mutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                Call<List<Book>> call = service.getBookByTitle(title);
                call.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        mutableLiveData.postValue(response.body().get(0));
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        mutableLiveData.postValue(null);
                    }
                });
            }
        },3000);
        return mutableLiveData;
    }

    public void addRequest(Request request) {
        BiblioService service = DataSource.getService();
        service.addRequest(request).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Request request1 = response.body();
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updateBookQuantity(String title, int quantity) {
        BiblioService service = DataSource.getService();
        Call<Book> call = service.updateRequestStatus(title,quantity);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Book book = response.body();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<Request> getRequestById(int id){
        MutableLiveData<Request> userMutableLiveData = new MutableLiveData<>();
        BiblioService service = DataSource.getService();
        service.getRequestById(id).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                Request requests = response.body().get(0);
                userMutableLiveData.postValue(requests);
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                t.printStackTrace();
                userMutableLiveData.postValue(null);
            }
        });
        return userMutableLiveData;
    }

    public LiveData<List<Request>> getRequestByTitle(String email, String string){
        MutableLiveData<List<Request>> userMutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BiblioService service = DataSource.getService();
                service.getRequestByTitle(email,string).enqueue(new Callback<List<Request>>() {
                    @Override
                    public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                        if (response.isSuccessful()) {
                            List<Request> requests = response.body();
                            if (requests.size() > 0) {
                                userMutableLiveData.postValue(response.body());
                            }else {
                                userMutableLiveData.postValue(null);
                                Toast toast = Toast.makeText(context, "Erro! Email existentes",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Request>> call, Throwable t) {
                        t.printStackTrace();
                        userMutableLiveData.postValue(null);
                    }
                });
            }
        },3000);
        return userMutableLiveData;
    }
}
