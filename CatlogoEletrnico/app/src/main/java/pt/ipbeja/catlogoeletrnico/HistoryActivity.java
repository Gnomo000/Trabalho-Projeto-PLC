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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        TextView textViewName = (TextView) headerView.findViewById(R.id.navName);
        TextView textViewEmail = (TextView) headerView.findViewById(R.id.navEmail);

        User userList = AppDataBaseUser.getInstance(this).getUserDao().getName(MainActivity.emailGeral);
        textViewName.setText(userList.getUsername());
        textViewEmail.setText(userList.getEmail());
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

    public void goToHistory(View view) {
    }

    public void goToBooks(View view) {
        Intent intent = new Intent(this,BooksActivity.class);
        startActivity(intent);
        HomeActivity.isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
        HomeActivity.isActive = false;
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}