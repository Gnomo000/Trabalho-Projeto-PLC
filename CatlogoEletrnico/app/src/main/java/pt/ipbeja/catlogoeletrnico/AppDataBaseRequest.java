package pt.ipbeja.catlogoeletrnico;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Request.class}, version = 6969)
public abstract class AppDataBaseRequest extends RoomDatabase {

    public abstract RequestDao getRequestDao();

    private static AppDataBaseRequest INSTANCE;

    public static AppDataBaseRequest getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBaseRequest.class,
                    "requestDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
