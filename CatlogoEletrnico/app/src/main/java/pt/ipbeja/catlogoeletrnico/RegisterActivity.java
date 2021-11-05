package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static Activity closeRegisterActivity;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.buttonCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        closeRegisterActivity = this;
        sharedpreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        String login = sharedpreferences.getString("LOGIN", null);
        String pass = sharedpreferences.getString("PASS",null);
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = String.format("%02d",dayOfMonth) + "/" + String.format("%02d",month+1) + "/" + year;
        EditText editTextDate = findViewById(R.id.editTextRegisterDate);
        editTextDate.setText(date);
    }

    public void createUser(View view) throws IOException {
        EditText editTextName = findViewById(R.id.editTextRegisterName);
        EditText editTextDate = findViewById(R.id.editTextRegisterDate);
        EditText editTextEmail = findViewById(R.id.editTextRegisterEmail);
        EditText editTextPhone = findViewById(R.id.editTextRegisterPhone);
        EditText editTextUserName = findViewById(R.id.editTextRegisterUsername);
        TextInputLayout editTextPassword = findViewById(R.id.editTextRegisterPassword);
        EditText editTextImage = findViewById(R.id.editTextRegisterImage);

        editTextDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        if (AppDataBase.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            alertAboutUs.setMessage("Email já existente");
            alertAboutUs.create().show();
        }else {
            if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                    || editTextPhone.getText().toString().isEmpty() || editTextUserName.getText().toString().isEmpty() || editTextPassword.getEditText().getText().toString().isEmpty()
                        || editTextImage.getText().toString().isEmpty()){

                AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this,R.style.MyDialogTheme);
                alertAboutUs.setMessage("Dados não preenchidos");
                alertAboutUs.create().show();

            }else{
                User user = new User(0,editTextName.getText().toString(),editTextDate.getText().toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUserName.getText().toString(),editTextPassword.getEditText().getText().toString(),
                        editTextImage.getText().toString());
                Log.i("POOP",editTextImage.getText().toString());
                Intent intent = new Intent(this,HomeActivity.class);
                AppDataBase.getInstance(this).getUserDao().add(user);
                MainActivity.loggedInUser = AppDataBase.getInstance(RegisterActivity.this).getUserDao().getUserByPasswordAndEmail(editTextEmail.getText().toString(),editTextPassword.getEditText().getText().toString());
                editor = sharedpreferences.edit();
                editor.putString("LOGIN", MainActivity.loggedInUser.getEmail());
                editor.putString("PASS",MainActivity.loggedInUser.getPassword());
                editor.apply();
                MainActivity.isLoginDone = true;
                startActivity(intent);
            }
        }
    }
}