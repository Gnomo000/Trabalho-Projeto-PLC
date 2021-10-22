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

    @Query("SELECT * FROM Request WHERE email = :email")
    List<Request> getRequestListByEmail(String email);

    @Insert
    void add(Request request);

    @Delete
    void delete(Request request);

    @Query("SELECT * FROM Request WHERE id = :id")
    Request getById(int id);

    @Query("SELECT * FROM Request WHERE title LIKE '%' || :title || '%' OR deliverDate = :deliverDate OR status LIKE '%' ||  :status || '%' OR requestDate = :requestDate")
    List<Request> getRequestByTitle(String title,String deliverDate,String status,String requestDate);

    @Query("UPDATE Request SET status = :status WHERE id = :id")
    void  update(String status,int id);

}
