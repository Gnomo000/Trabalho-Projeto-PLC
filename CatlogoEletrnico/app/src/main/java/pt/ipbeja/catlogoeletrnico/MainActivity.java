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

    public static boolean isLoginDone;
    public static User loggedInUser;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor;
    private EditText editTextEmail;
    private TextInputLayout editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isLoginDone == true) {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        sharedpreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        String login = sharedpreferences.getString("LOGIN", null);
        String pass = sharedpreferences.getString("PASS",null);

        if (login != null && pass != null) {
            loggedInUser = AppDataBase.getInstance(this).getUserDao().getUserByPasswordAndEmail(login,pass);
            Intent intent = new Intent(this,HomeActivity.class);
            isLoginDone = true;
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
                loggedInUser = AppDataBase.getInstance(this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString());
                Intent intent = new Intent(this,HomeActivity.class);
                isLoginDone = true;
                editor = sharedpreferences.edit();
                editor.putString("LOGIN", loggedInUser.getEmail());
                editor.putString("PASS",loggedInUser.getPassword());
                editor.apply();
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