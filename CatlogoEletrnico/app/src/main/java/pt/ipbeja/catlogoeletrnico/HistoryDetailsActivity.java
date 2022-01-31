package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class HistoryDetailsActivity extends AppCompatActivity {

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, HistoryDetailsActivity.class);
        intent.putExtra(KEY_ITEMID, id);
        context.startActivity(intent);
    }

    private static final String KEY_ITEMID = "ITEMID";
    private static final String TAG = "HistoryDetailsActivity";
    private BiblioRepository biblioRepository;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        ActionBar actionBar = getSupportActionBar();
        this.biblioRepository = new BiblioRepository(this);

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

            biblioRepository.getRequestById(id).observe(this, new Observer<Request>() {
                @Override
                public void onChanged(Request request) {
                    HistoryDetailsActivity.this.textViewStade.setText(request.getStatus());
                    HistoryDetailsActivity.this.textViewRequest.setText(request.getRequestDate());
                    HistoryDetailsActivity.this.textViewDelivery.setText(request.getDeliverDate());
                    HistoryDetailsActivity.this.textViewQuantity.setText(String.valueOf(request.getQuantity()));

                    if (request.getStatus().equals("Por Levantar")) {
                        HistoryDetailsActivity.this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                    }else if (request.getStatus().equals("Por Entregar")){
                        HistoryDetailsActivity.this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }else if (request.getStatus().equals("Atrazado")){
                        HistoryDetailsActivity.this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    }else if (request.getStatus().equals("Entrague")){
                        HistoryDetailsActivity.this.viewRect.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                    }else {
                        Log.i("POOP","|"+request.getStatus()+"|");
                    }

                    biblioRepository.getBookByTitle(request.getTitle()).observe(HistoryDetailsActivity.this, new Observer<Book>() {
                        @Override
                        public void onChanged(Book book) {

                            Glide.with(HistoryDetailsActivity.this).load(book.getImage()).into(HistoryDetailsActivity.this.imageViewBook);
                            HistoryDetailsActivity.this.textViewBook.setText(book.getTitle());
                            HistoryDetailsActivity.this.textViewBookEn.setText(book.getTitleEn());
                            HistoryDetailsActivity.this.textViewBookAuthor.setText(book.getAuthor());
                            HistoryDetailsActivity.this.textViewBookEdition.setText(book.getEdition());
                            HistoryDetailsActivity.this.textViewBookPublisher.setText(book.getPublisher());
                            HistoryDetailsActivity.this.textViewBookCategory.setText(book.getGenders());
                            HistoryDetailsActivity.this.textViewQuan.setText(String.valueOf(book.getQuantity()));
                            HistoryDetailsActivity.this.textViewBookSynopse.setText(book.getSynopse());
                            actionBar.setTitle(book.getTitle());
                        }
                    });

                }
            });



        }else {
            Log.e(TAG, "No position specified!");
            finish();
        }

    }

    public void zoomIn(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
        PhotoView photoView = mView.findViewById(R.id.photo_view);
        Glide.with(this).load(this.book.getImage()).into(photoView);
        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
}