package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static boolean loginDone;
    private TextView datetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        datetext = findViewById(R.id.editTextDateRegister);

        findViewById(R.id.buttonCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdatePickerDialog();
            }
        });
    }

    private void showdatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        datetext.setText(date);
    }
}