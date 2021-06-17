package pt.ipbeja.catlogoeletrnico;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class AppDataBaseUser extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static AppDataBaseUser INSTANCE;

    public static AppDataBaseUser getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBaseUser.class,
                    "userDB")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO User (name,date,email,phone,username,password,image) VALUES (" +
                                    "'Daniel Rodrigues'," +
                                    "'06/07/2002'," +
                                    "'21707@stu.ipbeja.pt'," +
                                    "'927487980'," +
                                    "'Gnomo'," +
                                    "'pass'," +
                                    "'https://s4.anilist.co/file/anilistcdn/user/avatar/large/b5271510-MiFJxt0NwyNc.png')");
                            db.execSQL("INSERT INTO User (name,date,email,phone,username,password,image) VALUES (" +
                                    "'Diogo Vaz'," +
                                    "'19/03/2002'," +
                                    "'21132@stu.ipbeja.pt'," +
                                    "'960497033'," +
                                    "'Vazinho'," +
                                    "'1234'," +
                                    "'https://s4.anilist.co/file/anilistcdn/character/large/b89361-x71P6YLrndd8.png')");
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
