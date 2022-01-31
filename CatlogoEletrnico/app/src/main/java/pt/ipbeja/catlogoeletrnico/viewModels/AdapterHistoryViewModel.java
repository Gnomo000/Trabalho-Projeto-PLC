package pt.ipbeja.catlogoeletrnico.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pt.ipbeja.catlogoeletrnico.models.BiblioRepository;
import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.models.SessionRepository;

public class AdapterHistoryViewModel extends AndroidViewModel {

    private SessionRepository sessionRepository;
    private BiblioRepository biblioRepository;

    public AdapterHistoryViewModel(@NonNull Application application) {
        super(application);
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
        this.biblioRepository = new BiblioRepository(application.getApplicationContext());
    }

    public LiveData<Book> getBookByTitle(String title){
        return biblioRepository.getBookByTitle(title);
    }
}
