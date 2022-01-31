package pt.ipbeja.catlogoeletrnico.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.BiblioRepository;
import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.models.Request;
import pt.ipbeja.catlogoeletrnico.models.SessionRepository;
import pt.ipbeja.catlogoeletrnico.models.User;

public class HomeViewModel extends AndroidViewModel {

    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
    }

    public LiveData<List<Request>> getRequestListByEmail(String email){
        return biblioRepository.getRequestListByEmail(email);
    }

    public void updateRequestStatus(int id, String status){
        this.biblioRepository.updateRequestStatus(id,status);
    }

    public LiveData<List<Book>> getAllBooksMoreZero(){
        return biblioRepository.getAllBooksMoreZero();
    }

    public User getActiveSession() {
        return sessionRepository.getActiveSession();
    }

    public void clearSession(){
        this.sessionRepository.clearSession();
    }

    public boolean getTheme(){
        return sessionRepository.getTheme();
    }

    public void saveTheme(boolean b){
         this.sessionRepository.saveTheme(b);
    }
}
