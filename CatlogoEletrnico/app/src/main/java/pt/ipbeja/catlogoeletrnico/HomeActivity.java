package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapterMyBooks;
    private RecyclerViewAdapterBook adapterAllBooks;
    public static boolean isActive = false;

    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isActive = true;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Request> request = AppDataBaseRequest.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail());

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
                    AppDataBaseRequest.getInstance(this).getRequestDao().update("Atrazado",request.get(i).getId());
                }else {
                    AppDataBaseRequest.getInstance(this).getRequestDao().update("Por Levantar",request.get(i).getId());
                }

            }
        }

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(isActive = true){
            Button button = findViewById(R.id.buttonMain);
            button.setClickable(false);
            button.setBackgroundColor(0xFFFFFF);
        }

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        textViewName = headerView.findViewById(R.id.navName);

        textViewName.setText("");
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(MainActivity.loggedInUser.getUsername());
        Glide.with(this).load(MainActivity.loggedInUser.getImage()).into(imageViewImage);
        textViewEmail.setText(MainActivity.loggedInUser.getEmail());


        LinearLayoutManager layoutManagerMyBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerAllBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myBooks =  findViewById(R.id.recyclerViewMyBooks);
        RecyclerView allBooks = findViewById(R.id.recyclerViewallBooks);
        myBooks.setLayoutManager(layoutManagerMyBooks);
        allBooks.setLayoutManager(layoutManagerAllBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);
        if (AppDataBaseRequest.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail()).size() == 0){
            isEmpty.setVisibility(View.VISIBLE);
            myBooks.setVisibility(View.GONE);
        }else {
            isEmpty.setVisibility(View.GONE);
            myBooks.setVisibility(View.VISIBLE);
        }
        

        adapterMyBooks = new RecyclerViewAdapterHistory(this, AppDataBaseRequest.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail()));
        adapterAllBooks = new RecyclerViewAdapterBook(this, AppDataBaseBook.getInstance(this).getBookDao().getAllMoreZero());

        allBooks.setAdapter(adapterAllBooks);
        myBooks.setAdapter(adapterMyBooks);


    }

    @Override
    protected void onStart() {
        super.onStart();
        this.adapterAllBooks.update(AppDataBaseBook.getInstance(this).getBookDao().getAllMoreZero());
        this.adapterMyBooks.update(AppDataBaseRequest.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail()));

        RecyclerView myBooks = findViewById(R.id.recyclerViewMyBooks);

        LinearLayout isEmpty = findViewById(R.id.listIsEmpty);
        if (AppDataBaseRequest.getInstance(this).getRequestDao().getRequestListByEmail(MainActivity.loggedInUser.getEmail()).size() == 0){
            isEmpty.setVisibility(View.VISIBLE);
            myBooks.setVisibility(View.GONE);
        }else {
            isEmpty.setVisibility(View.GONE);
            myBooks.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void goToHistory(View view) {
        Intent intent = new Intent(this,HistoryActivity.class);
        startActivity(intent);
        isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void goToBooks(View view) {
        Intent intent = new Intent(this,BooksActivity.class);
        startActivity(intent);
        isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void getOut(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        isActive = false;
        MainActivity.isLoginDone = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}