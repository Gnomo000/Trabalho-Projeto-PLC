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

public class BookDetailsViewModel extends AndroidViewModel {
    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public BookDetailsViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
    }

    public User getActiveSession() {
        return sessionRepository.getActiveSession();
    }

    public LiveData<List<Book>> getBookById(int id){
        return biblioRepository.getBookById(id);
    }

    public LiveData<Book> getBookByTitle(String title){
        return biblioRepository.getBookByTitle(title);
    }

    public void addRequest(Request request){
        this.biblioRepository.addRequest(request);
    }

    public void updateBookQuantity(String title,int quantity){
        this.biblioRepository.updateBookQuantity(title,quantity);
    }
}
