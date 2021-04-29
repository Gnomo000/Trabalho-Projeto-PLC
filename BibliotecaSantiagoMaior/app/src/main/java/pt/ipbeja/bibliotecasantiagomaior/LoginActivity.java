package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    public static boolean loginDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToMainPage(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        this.finish();
        MainActivity.CloseMainActivity.finish();
        loginDone = true;
    }
}