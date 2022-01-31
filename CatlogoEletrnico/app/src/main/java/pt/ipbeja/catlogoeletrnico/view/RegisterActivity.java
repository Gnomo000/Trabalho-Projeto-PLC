package pt.ipbeja.catlogoeletrnico.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import pt.ipbeja.catlogoeletrnico.R;
import pt.ipbeja.catlogoeletrnico.models.User;
import pt.ipbeja.catlogoeletrnico.viewModels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static Activity closeRegisterActivity;
    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextUserName;
    private TextInputLayout editTextPassword;
    private EditText editTextImage;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

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
        editTextName = findViewById(R.id.editTextRegisterName);
        editTextDate = findViewById(R.id.editTextRegisterDate);
        editTextEmail = findViewById(R.id.editTextRegisterEmail);
        editTextPhone = findViewById(R.id.editTextRegisterPhone);
        editTextUserName = findViewById(R.id.editTextRegisterUsername);
        editTextPassword = findViewById(R.id.editTextRegisterPassword);
        editTextImage = findViewById(R.id.editTextRegisterImage);
        editTextDate.setInputType(InputType.TYPE_CLASS_DATETIME);

        registerViewModel.getUserByEmail(RegisterActivity.this,editTextEmail.getText().toString()).observe(RegisterActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(RegisterActivity.this,R.style.MyDialogTheme);
                    alertAboutUs.setMessage("Email já existente");
                    alertAboutUs.create().show();
                }else {
                    if (editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                            || editTextPhone.getText().toString().isEmpty() || editTextUserName.getText().toString().isEmpty() || editTextPassword.getEditText().getText().toString().isEmpty()
                            || editTextImage.getText().toString().isEmpty()) {

                        AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(RegisterActivity.this,R.style.MyDialogTheme);
                        alertAboutUs.setMessage("Dados não preenchidos");
                        alertAboutUs.create().show();
                    }else {
                        User newUser = User.createUser(editTextName.getText().toString(),editTextDate.getText().toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUserName.getText().toString(),editTextPassword.getEditText().getText().toString(),editTextImage.getText().toString());
                        registerViewModel.createUser(newUser);
                        registerViewModel.getUserByEmail(RegisterActivity.this,newUser.getEmail()).observe(RegisterActivity.this, new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                registerViewModel.saveSession(user);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });

    }
}