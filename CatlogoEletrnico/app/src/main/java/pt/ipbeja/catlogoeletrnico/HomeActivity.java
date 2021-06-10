package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    public static boolean isActive = false;
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

    public void goToHome(View view) {
    }
}