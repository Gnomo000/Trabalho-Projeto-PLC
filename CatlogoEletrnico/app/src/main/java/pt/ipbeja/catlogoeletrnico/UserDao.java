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

    @Insert
    void add(User user);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUserByPasswordAndEmail(String email, String password);


    @Query("UPDATE User SET name = :name, date = :date, phone = :phone, username = :username, password = :password ,image = :image ")
    void updateUser(String name, String date,String phone, String username, String password, String image);

    @Delete
    void delete(User user);

}
