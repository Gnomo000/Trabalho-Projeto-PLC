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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pt.ipbeja.catlogoeletrnico.models.Book;
import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.AdapterBook;
import pt.ipbeja.catlogoeletrnico.models.AdapterHistory;
import pt.ipbeja.catlogoeletrnico.models.Request;
import pt.ipbeja.catlogoeletrnico.viewModels.HomeViewModel;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private AdapterHistory adapterMyBooks;
    private AdapterBook adapterAllBooks;
    private boolean isDarkModeOn;
    TextView textViewName;
    private HomeViewModel homeViewModel;
    private RecyclerView myBooks;
    private RecyclerView allBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        homeViewModel.getRequestListByEmail(homeViewModel.getActiveSession().getEmail()).observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests != null && requests.size() != 0) {
                    Date dateDeliver = null;
                    Date dateQ  = Calendar.getInstance().getTime();

                    for (int i = 0; i < requests.size(); i++) {
                        try {
                            dateDeliver = sdf.parse(requests.get(i).getDeliverDate());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (dateQ.after(dateDeliver)) {
                            homeViewModel.updateRequestStatus(requests.get(i).getId(),"Atrazado");
                        }else {
                            homeViewModel.updateRequestStatus(requests.get(i).getId(),"Por Levantar");
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

        textViewName.setText(homeViewModel.getActiveSession().getUsername());
        Glide.with(this).load(homeViewModel.getActiveSession().getImage()).into(imageViewImage);
        textViewEmail.setText(homeViewModel.getActiveSession().getEmail());

        LinearLayoutManager layoutManagerMyBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerAllBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        myBooks =  findViewById(R.id.recyclerViewMyBooks);
        allBooks = findViewById(R.id.recyclerViewallBooks);

        myBooks.setLayoutManager(layoutManagerMyBooks);
        allBooks.setLayoutManager(layoutManagerAllBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        adapterMyBooks = new AdapterHistory(HomeActivity.this);
        myBooks.setAdapter(adapterMyBooks);

        adapterAllBooks = new AdapterBook(HomeActivity.this);
        allBooks.setAdapter(adapterAllBooks);

        homeViewModel.getRequestListByEmail(homeViewModel.getActiveSession().getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests != null && requests.size() != 0) {
                    isEmpty.setVisibility(View.GONE);
                    myBooks.setVisibility(View.VISIBLE);
                }else {
                    isEmpty.setVisibility(View.VISIBLE);
                    myBooks.setVisibility(View.GONE);
                }
                adapterMyBooks.update(requests);
            }
        });

        homeViewModel.getAllBooksMoreZero().observe(HomeActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapterAllBooks.update(books);
            }
        });

        isDarkModeOn = homeViewModel.getTheme();

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

        RecyclerView myBooks = findViewById(R.id.recyclerViewMyBooks);
        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);

        homeViewModel.getRequestListByEmail(homeViewModel.getActiveSession().getEmail()).observe(HomeActivity.this, new Observer<List<Request>>() {
            @Override
            public void onChanged(List<Request> requests) {
                if (requests != null && requests.size() != 0) {
                    isEmpty.setVisibility(View.GONE);
                    myBooks.setVisibility(View.VISIBLE);
                }else {
                    isEmpty.setVisibility(View.VISIBLE);
                    myBooks.setVisibility(View.GONE);
                }
                adapterMyBooks.update(requests);
            }
        });

        homeViewModel.getAllBooksMoreZero().observe(HomeActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapterAllBooks.update(books);
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
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_books: {
                Intent intent = new Intent(this, BooksActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_out: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                homeViewModel.clearSession();
                HomeActivity.this.finish();
                break;
            }
            case R.id.nav_theme: {

                if (isDarkModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    homeViewModel.saveTheme(false);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    homeViewModel.saveTheme(true);
                }
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}