package pt.ipbeja.catlogoeletrnico.models.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.Book;

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Query("SELECT * FROM Book WHERE quantity > 0")
    List<Book> getAllMoreZero();

    @Insert
    void add(Book book);

    @Query("SELECT * FROM Book WHERE title = :title")
    Book getBookByTitle(String title);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM Book WHERE id = :id")
    Book getById(int id);

    @Query("UPDATE Book SET quantity = quantity - :quantity WHERE title = :title")
    void update(int quantity,String title);


    @Query("SELECT * FROM Book WHERE title LIKE '%' || :search || '%' OR titleEn LIKE '%' || :search || '%' OR author LIKE '%' || :search || '%'" +
            " OR publisher LIKE '%' || :search || '%' OR genders LIKE '%' || :search || '%'")
    List<Book> getBookByTitleList(String search);
}