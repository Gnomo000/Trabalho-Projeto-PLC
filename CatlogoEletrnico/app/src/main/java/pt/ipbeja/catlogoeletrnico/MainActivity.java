package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private TextInputLayout editTextPassword;
    private BiblioRepository biblioRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        User user = SessionManager.getActiveSession(this);
        if (user != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.biblioRepository = new BiblioRepository(getApplicationContext());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void goRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goLogin(View view) {

        editTextEmail = findViewById(R.id.editTextLoginEmail);
        editTextPassword = findViewById(R.id.editTextPassLoginPass);


        biblioRepository.getUserByEmail(MainActivity.this,editTextEmail.getText().toString()).observe(MainActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    biblioRepository.getUserByPasswordAndEmail(MainActivity.this,editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString()).observe(MainActivity.this, new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            if (user != null) {
                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                SessionManager.saveSession(MainActivity.this,user);
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