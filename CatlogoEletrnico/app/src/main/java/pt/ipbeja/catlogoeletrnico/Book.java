package pt.ipbeja.catlogoeletrnico;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String titleEn;
    private String author;
    private String edition;
    private String publisher;
    private String synopse;
    private String genders;
    private int quantity;

    public Book(long id, String title, String titleEn, String author, String edition, String publisher, String synopse, String genders, int quantity) {
        this.id = id;
        this.title = title;
        this.titleEn = titleEn;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.synopse = synopse;
        this.genders = genders;
        this.quantity = quantity;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopse() {
        return synopse;
    }

    public void setSynopse(String synopse) {
        this.synopse = synopse;
    }

    public String getGenders() {
        return genders;
    }

    public void setGenders(String genders) {
        this.genders = genders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
