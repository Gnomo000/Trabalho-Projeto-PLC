package pt.ipbeja.catlogoeletrnico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapterMyBooks;
    private RecyclerViewAdapterBook adapterAllBooks;
    public static boolean isDarkModeOn = false;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Request> request = AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(SessionManager.getActiveSession(this).getEmail());

        if (request.size() != 0) {

            Date dateDeliver = null;
            Date dateQ  = Calendar.getInstance().getTime();

            for (int i = 0; i < request.size(); i++) {

                try {
                    dateDeliver = sdf.parse(request.get(0).getDeliverDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateQ.after(dateDeliver)) {
                    AppDataBase.getInstance(this).getRequestDao().update("Atrazado",request.get(i).getId());
                }else {
                    AppDataBase.getInstance(this).getRequestDao().update("Por Levantar",request.get(i).getId());
                }

            }
        }

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        textViewName = headerView.findViewById(R.id.navName);

        textViewName.setText("");
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(SessionManager.getActiveSession(this).getUsername());
        Glide.with(this).load(SessionManager.getActiveSession(this).getImage()).into(imageViewImage);
        textViewEmail.setText(SessionManager.getActiveSession(this).getEmail());


        LinearLayoutManager layoutManagerMyBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerAllBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myBooks =  findViewById(R.id.recyclerViewMyBooks);
        RecyclerView allBooks = findViewById(R.id.recyclerViewallBooks);
        myBooks.setLayoutManager(layoutManagerMyBooks);
        allBooks.setLayoutManager(layoutManagerAllBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);
        if (AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(SessionManager.getActiveSession(this).getEmail()).size() == 0){
            isEmpty.setVisibility(View.VISIBLE);
            myBooks.setVisibility(View.GONE);
        }else {
            isEmpty.setVisibility(View.GONE);
            myBooks.setVisibility(View.VISIBLE);
        }
        

        adapterMyBooks = new RecyclerViewAdapterHistory(this, AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(SessionManager.getActiveSession(this).getEmail()));
        adapterAllBooks = new RecyclerViewAdapterBook(this, AppDataBase.getInstance(this).getBookDao().getAllMoreZero());

        allBooks.setAdapter(adapterAllBooks);
        myBooks.setAdapter(adapterMyBooks);


        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);


        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.adapterAllBooks.update(AppDataBase.getInstance(this).getBookDao().getAllMoreZero());
        this.adapterMyBooks.update(AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(SessionManager.getActiveSession(this).getEmail()));

        RecyclerView myBooks = findViewById(R.id.recyclerViewMyBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);
        if (AppDataBase.getInstance(this).getRequestDao().getRequestListByEmail(SessionManager.getActiveSession(this).getEmail()).size() == 0){
            isEmpty.setVisibility(View.VISIBLE);
            myBooks.setVisibility(View.GONE);
        }else {
            isEmpty.setVisibility(View.GONE);
            myBooks.setVisibility(View.VISIBLE);
        }

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_history: {
                Intent intent = new Intent(this,HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_books: {
                Intent intent = new Intent(this,BooksActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                SessionManager.clearSession(HomeActivity.this);
                HomeActivity.this.finish();
                break;
            }
            case R.id.nav_theme: {

                if (HomeActivity.isDarkModeOn) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();

                }
                else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();


                }
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}