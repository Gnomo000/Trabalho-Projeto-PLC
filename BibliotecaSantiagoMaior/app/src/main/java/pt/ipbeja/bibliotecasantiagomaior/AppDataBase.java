package pt.ipbeja.bibliotecasantiagomaior;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "userDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
