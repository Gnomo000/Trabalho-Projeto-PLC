package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    public static boolean loginDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goToMainPage(View view) {
        /*Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        this.finish();
        MainActivity.CloseMainActivity.finish();
        loginDone = true;*/

        EditText editTextName = findViewById(R.id.editTextNameRegister);
        EditText editTextDate = findViewById(R.id.editTextDateRegister);
        EditText editTextEmail = findViewById(R.id.editTextEmailRegister);
        EditText editTextPhone = findViewById(R.id.editTextPhoneRegister);
        EditText editTextUsername = findViewById(R.id.editTextUsernameRegister);
        TextInputLayout editTextPassword = findViewById(R.id.editTextPasswordRegister);

        if (AppDataBase.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
            alertAboutUs.setTitle("Já Existe");
            alertAboutUs.create().show();
        } else {
            User user = new User(0,editTextName.getText().toString(),editTextDate.toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUsername.getText().toString(),editTextPassword.toString());
            AppDataBase.getInstance(this).getUserDao().add(user);
        }

    }
}