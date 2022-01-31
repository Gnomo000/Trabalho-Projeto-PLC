package pt.ipbeja.catlogoeletrnico.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.BiblioRepository;
import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.models.SessionRepository;
import pt.ipbeja.catlogoeletrnico.models.User;

public class BooksViewModel extends AndroidViewModel {

    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public BooksViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
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

    public LiveData<List<Book>> getAllBooks(){
        return biblioRepository.getAllBooks();
    }

    public LiveData<List<Book>> getBookByTitleList(String title){
        return biblioRepository.getBookByTitleList(title);
    }
}
