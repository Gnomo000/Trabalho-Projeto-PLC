package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static Activity closeRegisterActivity;

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

    public void createUser(View view) {
        EditText editTextName = findViewById(R.id.editTextRegisterName);
        EditText editTextDate = findViewById(R.id.editTextRegisterDate);
        EditText editTextEmail = findViewById(R.id.editTextRegisterEmail);
        EditText editTextPhone = findViewById(R.id.editTextRegisterPhone);
        EditText editTextUserName = findViewById(R.id.editTextRegisterUsername);
        TextInputLayout editTextPassword = findViewById(R.id.editTextRegisterPassword);

        if (AppDataBaseUser.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
            alertAboutUs.setMessage("Email já existente");
            alertAboutUs.create().show();
        }else {
            if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                    || editTextPhone.getText().toString().isEmpty() || editTextUserName.getText().toString().isEmpty() || editTextPassword.getEditText().getText().toString().isEmpty()){

                AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                alertAboutUs.setMessage("Dados não preenchidos");
                alertAboutUs.create().show();

            }else{

                User user = new User(0,editTextName.getText().toString(),editTextDate.getText().toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUserName.getText().toString(),editTextPassword.getEditText().getText().toString(),
                        "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png");
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                AppDataBaseUser.getInstance(this).getUserDao().add(user);
            }
        }
    }

    public void deleteUser(View view) {
        List<User> user = AppDataBaseUser.getInstance(this).getUserDao().getAll();

        for (int i = 0; i < user.size(); i++) {
            AppDataBaseUser.getInstance(this).getUserDao().delete(user.get(i));
        }
    }
}