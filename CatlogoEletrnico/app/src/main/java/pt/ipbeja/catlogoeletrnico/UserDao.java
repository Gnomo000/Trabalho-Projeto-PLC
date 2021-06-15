package pt.ipbeja.catlogoeletrnico;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Update
    void update(User user);

    @Insert
    void add(User user);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUserByPasswordAndEmail(String email, String password);

    @Query("SELECT * FROM User WHERE email = :email")
    User getName(String email);

    @Delete
    void delete(User user);

}
