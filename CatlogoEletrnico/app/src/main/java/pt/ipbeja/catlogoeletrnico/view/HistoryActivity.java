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

import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.AdapterHistory;
import pt.ipbeja.catlogoeletrnico.models.Request;
import pt.ipbeja.catlogoeletrnico.viewModels.HistoryViewModel;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private AdapterHistory adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchMyBook;
    private HistoryViewModel historyViewModel;

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
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new AdapterHistory(HistoryActivity.this);
        recyclerView.setAdapter(adapter);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        historyViewModel.getRequestListByEmail(historyViewModel.getActiveSession().getEmail()).observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests != null && requests.size() != 0) {
                    isEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Sem Livros Requisitados\nRequesite!");

                    isEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                adapter.update(requests);
            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_history);

        TextView textViewName = headerView.findViewById(R.id.navName);
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(historyViewModel.getActiveSession().getUsername());
        Glide.with(this).load(historyViewModel.getActiveSession().getImage()).into(imageViewImage);
        textViewEmail.setText(historyViewModel.getActiveSession().getEmail());

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

                adapter = new AdapterHistory(HistoryActivity.this);
                recyclerView.setAdapter(adapter);

                historyViewModel.getRequestByTitle(historyViewModel.getActiveSession().getEmail(),editTextSearchMyBook.getText().toString()).observe(HistoryActivity.this, new Observer<List<Request>>() {
                    @Override
                    public void onChanged(List<Request> requests) {
                        if (requests != null && requests.size() != 0) {
                            isEmpty.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }else {
                            TextView textView = findViewById(R.id.emptyMessage);
                            textView.setText("Sem Livros Requisitados\nRequesite!");

                            isEmpty.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        adapter.update(requests);
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

        historyViewModel.getRequestListByEmail(historyViewModel.getActiveSession().getEmail()).observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests != null && requests.size() != 0){
                    HistoryActivity.this.adapter.update(requests);

                    isEmpty.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    TextView textView = findViewById(R.id.emptyMessage);
                    textView.setText("Sem Livros Requisitados\nRequesite!");

                    isEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_history);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_books: {
                Intent intent = new Intent(this, BooksActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_theme: {

                boolean isDarkModeOn = historyViewModel.getTheme();

                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    historyViewModel.saveTheme(false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    historyViewModel.saveTheme(true);
                }
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                historyViewModel.clearSession();
                HistoryActivity.this.finish();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}