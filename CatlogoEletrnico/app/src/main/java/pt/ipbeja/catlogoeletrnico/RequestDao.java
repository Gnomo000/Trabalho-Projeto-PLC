package pt.ipbeja.catlogoeletrnico;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RequestDao {
    @Query("SELECT * FROM Request")
    List<Request> getAll();

    @Insert
    void add(Request request);

    @Delete
    void delete(Request request);

}
