package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(KEY_ITEMID, id);
        context.startActivity(intent);
    }

    private static final String KEY_ITEMID = "ITEMID";
    private static final String TAG = "BookDetailsActivity";

    private ImageView imageViewBook;
    private TextView textViewBook;
    private TextView textViewBookEn;
    private TextView textViewBookAuthor;
    private TextView textViewBookEdition;
    private TextView textViewBookPublisher;
    private TextView textViewBookCategory;
    private TextView textViewBookSynopse;
    private TextView textViewQuan;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt(KEY_ITEMID, -1);
            if (id == -1) {
                Log.e(TAG, "Invalid position found!");
                finish();
                return;
            }
            this.book = AppDataBaseBook.getInstance(this).getBookDao().getById(id);
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

            Button requestButton = findViewById(R.id.requestButton);

            if (this.book.getQuantity() == 0) {
                requestButton.setText("Esgotado");
                requestButton.setTextColor(Color.parseColor("#FF9646"));
                requestButton.setClickable(false);
                requestButton.setBackgroundColor(0xFFFFFF);
            }else{
                requestButton.setText("Requisitar");
            }


        }else {
            Log.e(TAG, "No position specified!");
            finish();
        }
    }

    public void requestBook(View view) {

        Book book = AppDataBaseBook.getInstance(this).getBookDao().getBookByTitle(this.book.getTitle());

        AlertDialog.Builder areYouShure = new AlertDialog.Builder(this);
        areYouShure.setTitle("Requisitar");
        areYouShure.setMessage("Quer requisitar: "+book.getTitle());
        areYouShure.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailsActivity.this);
                final View view = BookDetailsActivity.this.getLayoutInflater().inflate(R.layout.dialog, null);
                builder.setView(view);
                final NumberPicker picker = (NumberPicker) view.findViewById(R.id.numberPicker1);
                picker.setMaxValue(book.getQuantity());
                picker.setMinValue(1);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int day = LocalDate.now().getDayOfMonth();
                        int monthAfter = LocalDate.now().plusMonths(1).getMonthValue();
                        int month = LocalDate.now().getMonthValue();
                        int year = LocalDate.now().getYear();
                        int getQuantity = picker.getValue();

                        String date = day+"/"+month+"/"+year;
                        String dateAfter = day+"/"+monthAfter+"/"+year;

                        Request request = new Request(0,MainActivity.userList.getEmail(),book.getTitle(),date,dateAfter,getQuantity,"Por Levantar");
                        AppDataBaseRequest.getInstance(BookDetailsActivity.this).getRequestDao().add(request);
                        AppDataBaseBook.getInstance(BookDetailsActivity.this).getBookDao().update(getQuantity,book.getTitle());

                        BookDetailsActivity.this.recreate();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.create().hide();
                    }
                });
                builder.create().show();
            }
        });
        areYouShure.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                areYouShure.create().hide();
            }
        });

        areYouShure.create().show();


    }
}