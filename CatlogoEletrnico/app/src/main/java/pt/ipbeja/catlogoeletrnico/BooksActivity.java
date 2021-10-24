package pt.ipbeja.catlogoeletrnico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class BooksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterBook adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchBook;

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        adapter = new RecyclerViewAdapterBook(this, AppDataBaseBook.getInstance(this).getBookDao().getAll());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_books);

        TextView textViewName = headerView.findViewById(R.id.navName);
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(MainActivity.loggedInUser.getUsername());
        Glide.with(this).load(MainActivity.loggedInUser.getImage()).into(imageViewImage);
        textViewEmail.setText(MainActivity.loggedInUser.getEmail());

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
                List<Book> book = AppDataBaseBook.getInstance(BooksActivity.this).getBookDao().getBookByTitleList(editTextSearchBook.getText().toString());
                adapter = new RecyclerViewAdapterBook(BooksActivity.this,book);
                recyclerView  = findViewById(R.id.recyclerView);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(BooksActivity.this,2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);
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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_books);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_history: {
                Intent intent = new Intent(this,HistoryActivity.class);
                startActivity(intent);
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
                BooksActivity.this.finish();
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}