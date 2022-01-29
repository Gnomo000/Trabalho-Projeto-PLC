package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private TextInputLayout editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        User user = SessionManager.getActiveSession(this);

        if (user != null) {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        
    }

    public void goRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {

        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextPassLoginPass);

        if (AppDataBase.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            if (AppDataBase.getInstance(this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString()) != null) {
                User user = AppDataBase.getInstance(this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString());
                Intent intent = new Intent(this,HomeActivity.class);
                SessionManager.saveSession(MainActivity.this,user);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Senha Incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Email não existe", Toast.LENGTH_SHORT).show();
        }
    }
}