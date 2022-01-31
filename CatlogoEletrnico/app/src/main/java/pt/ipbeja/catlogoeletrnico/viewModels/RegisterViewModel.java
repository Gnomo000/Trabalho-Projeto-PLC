package pt.ipbeja.catlogoeletrnico.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.ipbeja.catlogoeletrnico.models.BiblioRepository;
import pt.ipbeja.catlogoeletrnico.models.SessionRepository;
import pt.ipbeja.catlogoeletrnico.models.User;

public class RegisterViewModel extends AndroidViewModel {
    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
    }

    public LiveData<User> getUserByEmail(Context context, String email){
        return biblioRepository.getUserByEmail(context,email);
    }

    public void createUser(User user){
        this.biblioRepository.createUser(user);
    }

    public void saveSession(User user){
        this.sessionRepository.saveSession(user);
    }
}
