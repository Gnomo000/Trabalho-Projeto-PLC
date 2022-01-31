package pt.ipbeja.catlogoeletrnico.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.AdapterBook;
import pt.ipbeja.catlogoeletrnico.viewModels.BooksViewModel;

public class BooksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private AdapterBook adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchBook;
    private BooksViewModel booksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerView);
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new AdapterBook(BooksActivity.this);
        recyclerView.setAdapter(adapter);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        booksViewModel.getAllBooks().observe(BooksActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if (books != null && books.size() != 0) {
                    isEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Livro Não Encontrado\nDesculpe!");

                    isEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                adapter.update(books);
            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_books);

        TextView textViewName = headerView.findViewById(R.id.navName);
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(booksViewModel.getActiveSession().getUsername());
        Glide.with(this).load(booksViewModel.getActiveSession().getImage()).into(imageViewImage);
        textViewEmail.setText(booksViewModel.getActiveSession().getEmail());

        editTextSearchBook = findViewById(R.id.editTextSearchBook);
        editTextSearchBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                adapter = new AdapterBook(BooksActivity.this);
                recyclerView.setAdapter(adapter);

                booksViewModel.getBookByTitleList(editTextSearchBook.getText().toString()).observe(BooksActivity.this, new Observer<List<Book>>() {
                    @Override
                    public void onChanged(List<Book> books) {
                        if (books != null && books.size() != 0) {
                            isEmpty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }else {
                            TextView textView = findViewById(R.id.emptyMessage);
                            textView.setText("Livro Não Encontrado\nDesculpe!");

                            isEmpty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        adapter.update(books);
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        booksViewModel.getAllBooks().observe(BooksActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if (books != null && books.size() != 0){
                    BooksActivity.this.adapter.update(books);

                    isEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Livro Não Encontrado\nDesculpe!");

                    isEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_books);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_history: {
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_theme: {

                boolean isDarkModeOn = booksViewModel.getTheme();

                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    booksViewModel.saveTheme(false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    booksViewModel.saveTheme(true);
                }
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                booksViewModel.clearSession();
                BooksActivity.this.finish();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}