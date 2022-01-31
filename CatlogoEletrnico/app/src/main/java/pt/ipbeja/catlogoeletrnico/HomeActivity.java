package pt.ipbeja.catlogoeletrnico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapterMyBooks;
    private RecyclerViewAdapterBook adapterAllBooks;
    private boolean isDarkModeOn;
    TextView textViewName;
    private BiblioRepository biblioRepository;
    private RecyclerView myBooks;
    private RecyclerView allBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.biblioRepository = new BiblioRepository(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        biblioRepository.getRequestListByEmail(HomeActivity.this,SessionManager.getActiveSession(this).getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests.size() != 0) {
                    Date dateDeliver = null;
                    Date dateQ  = Calendar.getInstance().getTime();

                    for (int i = 0; i < requests.size(); i++) {
                        try {
                            dateDeliver = sdf.parse(requests.get(i).getDeliverDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (dateQ.after(dateDeliver)) {
                            biblioRepository.updateRequestStatus(requests.get(i).getId(),"Atrazado");
                        }else {
                            biblioRepository.updateRequestStatus(requests.get(i).getId(),"Por Levantar");
                        }
                    }
                }
            }
        });


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

        myBooks =  findViewById(R.id.recyclerViewMyBooks);
        allBooks = findViewById(R.id.recyclerViewallBooks);
        myBooks.setLayoutManager(layoutManagerMyBooks);
        allBooks.setLayoutManager(layoutManagerAllBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        biblioRepository.getRequestListByEmail(HomeActivity.this,SessionManager.getActiveSession(this).getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests.size() == 0) {
                    isEmpty.setVisibility(View.VISIBLE);
                    myBooks.setVisibility(View.GONE);
                }else {
                    isEmpty.setVisibility(View.GONE);
                    myBooks.setVisibility(View.VISIBLE);
                }
            }
        });

        biblioRepository.getRequestListByEmail(HomeActivity.this,SessionManager.getActiveSession(this).getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                adapterMyBooks = new RecyclerViewAdapterHistory(HomeActivity.this,requests);
                myBooks.setAdapter(adapterMyBooks);
            }
        });

        biblioRepository.getAllBooksMoreZero().observe(HomeActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapterAllBooks = new RecyclerViewAdapterBook(HomeActivity.this,books);
                allBooks.setAdapter(adapterAllBooks);
            }
        });

        isDarkModeOn = SessionManager.getTheme(this);

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        biblioRepository.getRequestListByEmail(HomeActivity.this,SessionManager.getActiveSession(this).getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                adapterMyBooks.update(requests);
                myBooks.setAdapter(adapterMyBooks);
            }
        });

        biblioRepository.getAllBooksMoreZero().observe(HomeActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapterAllBooks.update(books);
                allBooks.setAdapter(adapterAllBooks);
            }
        });

        RecyclerView myBooks = findViewById(R.id.recyclerViewMyBooks);
        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        biblioRepository.getRequestListByEmail(HomeActivity.this,SessionManager.getActiveSession(this).getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests.size() == 0) {
                    isEmpty.setVisibility(View.VISIBLE);
                    myBooks.setVisibility(View.GONE);
                }else {
                    isEmpty.setVisibility(View.GONE);
                    myBooks.setVisibility(View.VISIBLE);
                }
            }
        });

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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                SessionManager.clearSession(HomeActivity.this);
                HomeActivity.this.finish();
                break;
            }
            case R.id.nav_theme: {

                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SessionManager.saveTheme(HomeActivity.this,false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SessionManager.saveTheme(HomeActivity.this,true);
                }
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}