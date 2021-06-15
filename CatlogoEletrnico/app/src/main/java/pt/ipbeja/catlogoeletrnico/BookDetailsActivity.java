package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
            this.textViewBookSynopse.setText(String.valueOf(this.book.getQuantity()));
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
}