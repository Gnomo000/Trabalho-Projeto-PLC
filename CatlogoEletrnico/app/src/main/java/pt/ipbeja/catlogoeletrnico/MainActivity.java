package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public Activity closeMainActivity;
    public static boolean isLoginDone;

    public static User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isLoginDone == true) {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        closeMainActivity = this;
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
                loggedInUser = AppDataBaseUser.getInstance(this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString());
                Intent intent = new Intent(this,HomeActivity.class);
                isLoginDone = true;
                startActivity(intent);
                finish();
            }else {
                /*AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this,R.style.MyDialogTheme);
                alertAboutUs.setMessage("Senha incorrecta");
                alertAboutUs.create().show();*/
                Toast.makeText(getApplicationContext(), "Senha Incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else {
            /*AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            alertAboutUs.setMessage("Email não existe");
            alertAboutUs.create().show();*/
            Toast.makeText(getApplicationContext(), "Email não existe", Toast.LENGTH_SHORT).show();
        }
    }
}