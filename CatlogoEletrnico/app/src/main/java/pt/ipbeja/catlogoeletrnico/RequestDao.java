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

    @Query("SELECT * FROM Request WHERE email = :email ORDER BY id DESC")
    List<Request> getRequestListByEmail(String email);

    @Insert
    void add(Request request);

    @Delete
    void delete(Request request);

    @Query("SELECT * FROM Request WHERE id = :id")
    Request getById(int id);

    @Query("SELECT * FROM Request WHERE email = :email AND title LIKE '%' || :title || '%' OR status LIKE '%' ||  :status || '%'  ORDER BY id DESC")
    List<Request> getRequestByTitle(String email,String title,String status);

    @Query("UPDATE Request SET status = :status WHERE id = :id")
    void  update(String status,int id);


}
