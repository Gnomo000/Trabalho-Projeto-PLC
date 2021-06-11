package pt.ipbeja.catlogoeletrnico;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDataBaseBook extends RoomDatabase {

    public abstract BookDao getBookDao();

    private static AppDataBaseBook INSTANCE;

    public static AppDataBaseBook getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBaseBook.class,
                    "bookDB")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) " +
                                    "VALUES ('Cão Como Nós','Dog Like Us','Manuel Alegre','18','Dom Quixote','É um épagneul-breton a personagem principal do novo livro de Manuel Alegre.'" +
                                    ",'Literatura Romantica',20,'https://img.wook.pt/images/cao-como-nos-manuel-alegre/MXw3MTY3OXwxMDE3NDZ8MTM4MzUyMzIwMDAwMHx3ZWJw/502x')");
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Harry Potter e a Pedra Filosofal'," +
                                    "'Harry Potter and the Philosophers Stone'," +
                                    "'J. K. Rowling'," +
                                    "'Junho de 2020'," +
                                    "'Editorial Presença'," +
                                    "'Harry'," +
                                    "'Juvenil'," +
                                    "40," +
                                    "'https://img.wook.pt/images/harry-potter-e-a-pedra-filosofal-j-k-rowling/MXwyNDA2Njc5OHwyMDEyNDQzMXwxNTkwNTM0MDAwMDAwfHdlYnA=/502x')");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
