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

    @Query("SELECT * FROM Request WHERE id = :id")
    Request getById(int id);

    @Query("SELECT * FROM Request WHERE title LIKE '%' || :title || '%'")
    List<Request> getRequestByTitle(String title);

}
