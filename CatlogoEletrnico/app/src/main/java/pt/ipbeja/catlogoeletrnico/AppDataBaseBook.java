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
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Factos Escondidos da História de Portugal'," +
                                    "'Hidden Facts from the History of Portugal'," +
                                    "'José Gomes Ferreira'," +
                                    "'Maio de 2021'," +
                                    "'Oficina do Livro'," +
                                    "'Os portugueses chegaram à América décadas antes de Colombo, descobriram a Austrália, participaram nos planos da viagem de Fernão de Magalhães… e outros dados históricos comprovados que permanecem na sombra.'," +
                                    "'História de Portugal'," +
                                    "20," +
                                    "'https://img.wook.pt/images/factos-escondidos-da-historia-de-portugal-jose-gomes-ferreira/MXwyNDcxMDA5MnwyMDg2MzkzMXwxNjE4MjY4NDAwMDAwfHdlYnA=/502x')");
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
                            db.execSQL("INSERT INTO Book (title,titleEn,author,edition,publisher,synopse,genders,quantity,image) VALUES" +
                                    "('Águas Passadas'," +
                                    "'Past waters'," +
                                    "'João Tordo'," +
                                    "'Junho de 2021'," +
                                    "'Companhia das Letras'," +
                                    "'Durante treze dias de Janeiro de 2019, a chuva cai sem misericórdia sobre Lisboa.'," +
                                    "'Policial e Thriller'," +
                                    "37," +
                                    "'https://img.wook.pt/images/aguas-passadas-joao-tordo/MXwyNDc4NTg5OXwyMDk2ODk4N3wxNjIxODEwODAwMDAwfHdlYnA=/502x')");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
