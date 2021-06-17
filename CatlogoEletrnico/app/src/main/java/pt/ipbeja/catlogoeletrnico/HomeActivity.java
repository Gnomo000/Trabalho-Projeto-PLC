package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        textViewName = headerView.findViewById(R.id.navName);

        textViewName.setText("");
        TextView textViewEmail = headerView.findViewById(R.id.navEmail);
        ImageView imageViewImage = headerView.findViewById(R.id.imageViewDr);

        textViewName.setText(MainActivity.userList.getUsername());
        Glide.with(this).load(MainActivity.userList.getImage()).into(imageViewImage);
        textViewEmail.setText(MainActivity.userList.getEmail());


        LinearLayoutManager layoutManagerMyBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerAllBooks = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myBooks = (RecyclerView) findViewById(R.id.recyclerViewMyBooks);
        RecyclerView allBooks = (RecyclerView) findViewById(R.id.recyclerViewallBooks);
        myBooks.setLayoutManager(layoutManagerMyBooks);
        allBooks.setLayoutManager(layoutManagerAllBooks);

        adapterMyBooks = new RecyclerViewAdapterHistory(this, AppDataBaseRequest.getInstance(this).getRequestDao().getAll());
        myBooks.setAdapter(adapterMyBooks);

        adapterAllBooks = new RecyclerViewAdapterBook(this, AppDataBaseBook.getInstance(this).getBookDao().getAll());
        allBooks.setAdapter(adapterAllBooks);

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