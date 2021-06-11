package pt.ipbeja.catlogoeletrnico;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Insert
    void add(Book book);

    @Query("SELECT * FROM Book WHERE title = :title")
    User getBookByTitle(String title);

    @Delete
    void delete(Book book);

}
