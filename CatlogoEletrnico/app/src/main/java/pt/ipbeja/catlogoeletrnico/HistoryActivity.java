package pt.ipbeja.catlogoeletrnico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchMyBook;

    TextView textViewName;
    private User userEmail;

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
        userEmail = AppDataBase.getInstance(this).getUserDao().getUserByEmail(MainActivity.loggedInUser.getEmail());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        adapter = new RecyclerViewAdapterHistory(this, AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(userEmail.getEmail()));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_history);

        textViewName = headerView.findViewById(R.id.navName);
        textViewName.setText("");
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(MainActivity.loggedInUser.getUsername());
        Glide.with(this).load(MainActivity.loggedInUser.getImage()).into(imageViewImage);
        textViewEmail.setText(MainActivity.loggedInUser.getEmail());

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
                List<Request> request = AppDataBase.getInstance(HistoryActivity.this).getRequestDao().getRequestByTitle(userEmail.getEmail(),editTextSearchMyBook.getText().toString(),editTextSearchMyBook.getText().toString(),editTextSearchMyBook.getText().toString(),editTextSearchMyBook.getText().toString());
                RecyclerView myBooks = findViewById(R.id.recyclerView);
                LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

                if (request.size() != 0) {
                    adapter = new RecyclerViewAdapterHistory(HistoryActivity.this,request);
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
        userEmail = AppDataBase.getInstance(this).getUserDao().getUserByEmail(MainActivity.loggedInUser.getEmail());
        this.adapter.update(AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(userEmail.getEmail()));

        RecyclerView myBooks = findViewById(R.id.recyclerView);
        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        if (AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail()).size() == 0){
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

                // When user taps the enable/disable
                // dark mode button
                if (HomeActivity.isDarkModeOn) {

                    // if dark mode is on it
                    // will turn it off
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_NO);
                    // it will set isDarkModeOn
                    // boolean to false
                    HomeActivity.editor.putBoolean("isDarkModeOn", false);
                    HomeActivity.editor.apply();

                    // change text of Button
                }
                else {

                    // if dark mode is off
                    // it will turn it on
                    AppCompatDelegate
                            .setDefaultNightMode(
                                    AppCompatDelegate
                                            .MODE_NIGHT_YES);

                    // it will set isDarkModeOn
                    // boolean to true
                    HomeActivity.editor.putBoolean("isDarkModeOn", true);
                    HomeActivity.editor.apply();

                    // change text of Button

                }
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                MainActivity.isLoginDone = false;
                SharedPreferences.Editor editor = MainActivity.sharedpreferences.edit();
                editor.remove("LOGIN");
                editor.remove("PASS");
                editor.apply();
                HistoryActivity.this.finish();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}