package pt.ipbeja.catlogoeletrnico.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.ipbeja.catlogoeletrnico.models.BiblioRepository;
import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.models.Request;
import pt.ipbeja.catlogoeletrnico.models.SessionRepository;
import pt.ipbeja.catlogoeletrnico.models.User;

public class HistoryDetailsViewModel extends AndroidViewModel {
    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public HistoryDetailsViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
    }

    public User getActiveSession() {
        return sessionRepository.getActiveSession();
    }

    public LiveData<Request> getRequestById(int id){
        return biblioRepository.getRequestById(id);
    }

    public LiveData<Book> getBookByTitle(String title){
        return biblioRepository.getBookByTitle(title);
    }
}
