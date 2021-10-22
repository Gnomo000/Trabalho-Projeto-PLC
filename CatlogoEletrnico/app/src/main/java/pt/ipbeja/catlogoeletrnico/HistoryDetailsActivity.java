package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class HistoryDetailsActivity extends AppCompatActivity {

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, HistoryDetailsActivity.class);
        intent.putExtra(KEY_ITEMID, id);
        context.startActivity(intent);
    }

    private static final String KEY_ITEMID = "ITEMID";
    private static final String TAG = "HistoryDetailsActivity";

    private ImageView imageViewBook;
    private TextView textViewBook;
    private TextView textViewBookEn;
    private TextView textViewBookAuthor;
    private TextView textViewBookEdition;
    private TextView textViewBookPublisher;
    private TextView textViewBookCategory;
    private TextView textViewBookSynopse;
    private TextView textViewQuan;
    private TextView textViewStade;
    private TextView textViewRequest;
    private TextView textViewDelivery;
    private TextView textViewQuantity;
    private View viewRect;

    private Request request;
    private Book book;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        ActionBar actionBar = getSupportActionBar();

        this.imageViewBook = findViewById(R.id.imageViewBook);
        this.textViewBook = findViewById(R.id.bookTitle);
        this.textViewBookEn = findViewById(R.id.bookTitleEn);
        this.textViewBookAuthor = findViewById(R.id.textViewAuthor);
        this.textViewBookEdition = findViewById(R.id.textViewEdition);
        this.textViewBookPublisher = findViewById(R.id.textViewPublisher);
        this.textViewBookCategory = findViewById(R.id.textViewCategory);
        this.textViewBookSynopse = findViewById(R.id.textViewSynopse);
        this.textViewQuan = findViewById(R.id.textViewQuan);

        this.textViewStade = findViewById(R.id.textViewState);
        this.textViewRequest = findViewById(R.id.textViewRequestDate);
        this.textViewDelivery = findViewById(R.id.textViewDeliveryDate);
        this.textViewQuantity = findViewById(R.id.textViewquantity);
        this.viewRect = findViewById(R.id.myRectangleView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt(KEY_ITEMID, -1);
            if (id == -1) {
                Log.e(TAG, "Invalid position found!");
                finish();
                return;
            }
            this.request = AppDataBaseRequest.getInstance(this).getRequestDao().getById(id);
            this.book = AppDataBaseBook.getInstance(this).getBookDao().getBookByTitle(this.request.getTitle());
            this.user = AppDataBaseUser.getInstance(this).getUserDao().getUserByEmail(this.request.getEmail());


            Glide.with(this).load(this.book.getImage()).into(this.imageViewBook);
            this.textViewBook.setText(this.book.getTitle());
            this.textViewBookEn.setText(this.book.getTitleEn());
            this.textViewBookAuthor.setText(this.book.getAuthor());
            this.textViewBookEdition.setText(this.book.getEdition());
            this.textViewBookPublisher.setText(this.book.getPublisher());
            this.textViewBookCategory.setText(this.book.getGenders());
            this.textViewQuan.setText(String.valueOf(this.book.getQuantity()));
            this.textViewBookSynopse.setText(this.book.getSynopse());
            actionBar.setTitle(this.book.getTitle());

            this.textViewStade.setText(this.request.getStatus());
            this.textViewRequest.setText(this.request.getRequestDate());
            this.textViewDelivery.setText(this.request.getDeliverDate());
            this.textViewQuantity.setText(String.valueOf(this.request.getQuantity()));


            if (request.getStatus().equals("Por Levantar")) {
                this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
            }else if (request.getStatus().equals("Por Entregar")){
                this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }else if (request.getStatus().equals("Atrazado")){
                this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }else if (request.getStatus().equals("Entrague")){
                this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
            }else {
                Log.i("POOP","|"+request.getStatus()+"|");
            }

        }else {
            Log.e(TAG, "No position specified!");
            finish();
        }

    }
}