package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static String emailGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void goRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {

        EditText editTextEmail = findViewById(R.id.editTextLoginEmail);
        TextInputLayout editTextPassword = findViewById(R.id.editTextPassLoginPass);

        if (AppDataBaseUser.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            if (AppDataBaseUser.getInstance(this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString()) != null) {
                emailGeral = editTextEmail.getText().toString();
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
            }
        }
    }
}