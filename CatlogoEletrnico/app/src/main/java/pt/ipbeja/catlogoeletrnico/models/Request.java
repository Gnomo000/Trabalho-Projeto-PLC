package pt.ipbeja.catlogoeletrnico.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Request {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String title;
    private String requestDate;
    private String deliverDate;
    private int quantity;
    private String status;

    public Request(int id, String email, String title, String requestDate, String deliverDate, int quantity, String status) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.requestDate = requestDate;
        this.deliverDate = deliverDate;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
