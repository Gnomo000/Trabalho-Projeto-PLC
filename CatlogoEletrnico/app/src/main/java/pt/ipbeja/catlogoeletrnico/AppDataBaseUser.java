package pt.ipbeja.catlogoeletrnico;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBaseUser extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static AppDataBaseUser INSTANCE;

    public static AppDataBaseUser getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBaseUser.class,
                    "userDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
