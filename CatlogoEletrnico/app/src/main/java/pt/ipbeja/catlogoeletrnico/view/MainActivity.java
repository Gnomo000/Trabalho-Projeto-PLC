package pt.ipbeja.catlogoeletrnico.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.User;
import pt.ipbeja.catlogoeletrnico.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private TextInputLayout editTextPassword;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        User user = mainViewModel.getActiveSession();
        if (user != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void goRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {

        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextPassLoginPass);


        mainViewModel.getUserByEmail(MainActivity.this,editTextEmail.getText().toString()).observe(MainActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    mainViewModel.getUserByPasswordAndEmail(MainActivity.this,editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString()).observe(MainActivity.this, new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            if (user != null) {
                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                mainViewModel.saveSession(user);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Senha Incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Email não existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}