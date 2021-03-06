package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerViewAdapterHistory adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearchMyBook;

    TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        HomeActivity.isActive = true;

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(HomeActivity.isActive = true){
            Button button = findViewById(R.id.buttonHistory);
            button.setClickable(false);
            button.setBackgroundColor(0xFFFFFF);
        }

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        adapter = new RecyclerViewAdapterHistory(this, AppDataBaseRequest.getInstance(this).getRequestDao().getAll());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        textViewName = headerView.findViewById(R.id.navName);

        textViewName.setText("");
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(MainActivity.userList.getUsername());
        Glide.with(this).load(MainActivity.userList.getImage()).into(imageViewImage);
        textViewEmail.setText(MainActivity.userList.getEmail());


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
                List<Request> request = AppDataBaseRequest.getInstance(HistoryActivity.this).getRequestDao().getRequestByTitle(editTextSearchMyBook.getText().toString());
                adapter = new RecyclerViewAdapterHistory(HistoryActivity.this,request);
                recyclerView  = findViewById(R.id.recyclerView);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(HistoryActivity.this,2);
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

    public void goToHome(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        HomeActivity.isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void goToBooks(View view) {
        Intent intent = new Intent(this,BooksActivity.class);
        startActivity(intent);
        HomeActivity.isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void goOut(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        MainActivity.isLoginDone = false;
        HomeActivity.isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}