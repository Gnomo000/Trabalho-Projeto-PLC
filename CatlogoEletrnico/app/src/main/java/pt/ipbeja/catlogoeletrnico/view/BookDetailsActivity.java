package pt.ipbeja.catlogoeletrnico.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.Request;
import pt.ipbeja.catlogoeletrnico.viewModels.BookDetailsViewModel;

public class BookDetailsActivity extends AppCompatActivity {

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(KEY_ITEMID, id);
        context.startActivity(intent);
    }

    private static final String KEY_ITEMID = "ITEMID";
    private static final String TAG = "BookDetailsActivity";
    private BookDetailsViewModel bookDetailsViewModel;

    private ImageView imageViewBook;
    private TextView textViewBook;
    private TextView textViewBookEn;
    private TextView textViewBookAuthor;
    private TextView textViewBookEdition;
    private TextView textViewBookPublisher;
    private TextView textViewBookCategory;
    private TextView textViewBookSynopse;
    private TextView textViewQuan;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookDetailsViewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);

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
            id = bundle.getInt(KEY_ITEMID, -1);
            if (id == -1) {
                Log.e(TAG, "Invalid position found!");
                finish();
                return;
            }

            bookDetailsViewModel.getBookById(id).observe(this, new Observer<List<Book>>() {
                @Override
                public void onChanged(List<Book> books) {

                    Glide.with(BookDetailsActivity.this).load(books.get(0).getImage()).into(BookDetailsActivity.this.imageViewBook);
                    BookDetailsActivity.this.textViewBook.setText(books.get(0).getTitle());
                    BookDetailsActivity.this.textViewBookEn.setText(books.get(0).getTitleEn());
                    BookDetailsActivity.this.textViewBookAuthor.setText(books.get(0).getAuthor());
                    BookDetailsActivity.this.textViewBookEdition.setText(books.get(0).getEdition());
                    BookDetailsActivity.this.textViewBookPublisher.setText(books.get(0).getPublisher());
                    BookDetailsActivity.this.textViewBookCategory.setText(books.get(0).getGenders());
                    BookDetailsActivity.this.textViewQuan.setText(String.valueOf(books.get(0).getQuantity()));
                    BookDetailsActivity.this.textViewBookSynopse.setText(books.get(0).getSynopse());
                    actionBar.setTitle(books.get(0).getTitle());

                    Button requestButton = findViewById(R.id.requestButton);

                    if (books.get(0).getQuantity() == 0) {
                        requestButton.setText("Esgotado");
                        requestButton.setTextColor(getColor(R.color.buttonColor));
                        requestButton.setClickable(false);
                        requestButton.setBackgroundColor(0xFFFFFF);
                    }else{
                        requestButton.setText("Requisitar");
                    }
                }
            });

        }else {
            Log.e(TAG, "No position specified!");
            finish();
        }

    }

    public void requestBook(View view) {

        bookDetailsViewModel.getBookById(id).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                bookDetailsViewModel.getBookByTitle(books.get(0).getTitle()).observe(BookDetailsActivity.this, new Observer<Book>() {
                    @Override
                    public void onChanged(Book book) {
                        AlertDialog.Builder areYouShure = new AlertDialog.Builder(BookDetailsActivity.this,R.style.MyDialogTheme);
                        areYouShure.setTitle("Requisitar");
                        areYouShure.setMessage("Quer requisitar: "+book.getTitle());
                        areYouShure.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailsActivity.this,R.style.MyDialogTheme);
                                final View view = BookDetailsActivity.this.getLayoutInflater().inflate(R.layout.dialog, null);
                                builder.setView(view);
                                builder.setTitle("Quantidade");
                                final NumberPicker picker = (NumberPicker) view.findViewById(R.id.numberPicker1);
                                picker.setMaxValue(book.getQuantity());
                                picker.setMinValue(1);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int getQuantity = picker.getValue();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                                        String dateNow = sdf.format(Calendar.getInstance().getTime());
                                        LocalDate localDate = LocalDate.now().plusMonths(1);
                                        String dateAfter = dtf.format(localDate);
                                        Request request = new Request(0, bookDetailsViewModel.getActiveSession().getEmail(),book.getTitle(),dateNow,dateAfter,getQuantity,"Por Levantar");
                                        bookDetailsViewModel.addRequest(request);
                                        bookDetailsViewModel.updateBookQuantity(book.getTitle(),getQuantity);
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
                });
            }
        });


    }

    public void zoomIn(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
        PhotoView photoView = mView.findViewById(R.id.photo_view);
        bookDetailsViewModel.getBookById(id).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                Glide.with(BookDetailsActivity.this).load(books.get(0).getImage()).into(photoView);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }
}