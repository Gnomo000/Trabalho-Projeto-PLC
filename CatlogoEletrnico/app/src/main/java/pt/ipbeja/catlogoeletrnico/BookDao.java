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

    @Query("SELECT * FROM Book WHERE id = :id")
    Book getById(int id);

    @Query("UPDATE Book SET quantity = 0 WHERE title = 'Harry Potter e a Pedra Filosofal'")
    void update();

}
