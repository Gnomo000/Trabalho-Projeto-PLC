package pt.ipbeja.catlogoeletrnico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
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

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchMyBook;
    private BiblioRepository biblioRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerView);
        this.biblioRepository = new BiblioRepository(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        biblioRepository.getRequestListByEmail(this,SessionManager.getActiveSession(this).getEmail()).observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                adapter = new RecyclerViewAdapterHistory(HistoryActivity.this,requests);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_history);

        TextView textViewName = headerView.findViewById(R.id.navName);
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(SessionManager.getActiveSession(this).getUsername());
        Glide.with(this).load(SessionManager.getActiveSession(this).getImage()).into(imageViewImage);
        textViewEmail.setText(SessionManager.getActiveSession(this).getEmail());

        editTextSearchMyBook = findViewById(R.id.editTextSearchMyBook);
        editTextSearchMyBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                RecyclerView myBooks = findViewById(R.id.recyclerView);
                LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

                biblioRepository.getRequestByTitle(SessionManager.getActiveSession(HistoryActivity.this).getEmail(),editTextSearchMyBook.getText().toString()).observe(HistoryActivity.this, new Observer<List<Request>>() {
                    @Override
                    public void onChanged(List<Request> requests) {
                        if (requests.size() != 0) {
                            adapter = new RecyclerViewAdapterHistory(HistoryActivity.this,requests);
                            recyclerView  = findViewById(R.id.recyclerView);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(HistoryActivity.this,2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(adapter);

                            isEmpty.setVisibility(View.GONE);
                            myBooks.setVisibility(View.VISIBLE);
                        }else {
                            isEmpty.setVisibility(View.VISIBLE);
                            myBooks.setVisibility(View.GONE);
                        }
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

        RecyclerView myBooks = findViewById(R.id.recyclerView);
        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        biblioRepository.getRequestListByEmail(this,SessionManager.getActiveSession(this).getEmail()).observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests.size() == 0){
                    isEmpty.setVisibility(View.VISIBLE);
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Sem Livros Requisitados\nRequesite!");
                    myBooks.setVisibility(View.GONE);
                }else {
                    isEmpty.setVisibility(View.GONE);
                    myBooks.setVisibility(View.VISIBLE);
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Livro não Encontrado");
                }
                HistoryActivity.this.adapter.update(requests);
                recyclerView.setAdapter(adapter);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_history);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_books: {
                Intent intent = new Intent(this,BooksActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_theme: {

                boolean isDarkModeOn = SessionManager.getTheme(HistoryActivity.this);

                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SessionManager.saveTheme(HistoryActivity.this,false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SessionManager.saveTheme(HistoryActivity.this,true);
                }
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                SessionManager.clearSession(HistoryActivity.this);
                HistoryActivity.this.finish();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}