package pt.ipbeja.catlogoeletrnico.models.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.User;

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


    @Delete
    void delete(User user);

}
