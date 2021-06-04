package pt.ipbeja.catlogoeletrnico;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void add(User user);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);

    @Delete
    void delete(User user);

}
