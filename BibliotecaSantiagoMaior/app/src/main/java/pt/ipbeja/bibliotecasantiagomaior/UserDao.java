package pt.ipbeja.bibliotecasantiagomaior;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void add(User user);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByEmail(String email);
}
