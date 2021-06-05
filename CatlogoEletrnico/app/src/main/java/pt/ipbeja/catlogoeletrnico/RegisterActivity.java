package pt.ipbeja.catlogoeletrnico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

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
        System.out.println(date);
        editTextDate.setText(date);
    }

    public void createUser(View view) {
        EditText editTextName = findViewById(R.id.editTextRegisterName);
        EditText editTextDate = findViewById(R.id.editTextRegisterDate);
        EditText editTextEmail = findViewById(R.id.editTextRegisterEmail);
        EditText editTextPhone = findViewById(R.id.editTextRegisterPhone);
        EditText editTextUserName = findViewById(R.id.editTextRegisterUsername);
        TextInputLayout editTextPassword = findViewById(R.id.editTextRegisterPassword);
        TextInputLayout editTextPasswordConfirm = findViewById(R.id.editTextRegisterPasswordConfirm);

        if (AppDataBase.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
            alertAboutUs.setMessage("Email já existente");
            alertAboutUs.create().show();
        }else {
            if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                    || editTextPhone.getText().toString().isEmpty() || editTextUserName.getText().toString().isEmpty() || editTextPassword.getEditText().getText().toString().isEmpty() || editTextPasswordConfirm.getEditText().getText().toString().isEmpty()){

                AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                alertAboutUs.setMessage("Dados não preenchidos");
                alertAboutUs.create().show();

            }else{
                if (editTextPassword.getEditText().getText().toString() == editTextPasswordConfirm.getEditText().getText().toString()) {
                    //TODO: Erro ao tentar ver se as senhas estão iguais
                    User user = new User(0,editTextName.getText().toString(),editTextDate.getText().toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUserName.getText().toString(),editTextPassword.getEditText().getText().toString());
                    AppDataBase.getInstance(this).getUserDao().add(user);
                    AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                    alertAboutUs.setMessage("OK");
                    alertAboutUs.create().show();
                }else{
                    AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                    alertAboutUs.setMessage("!!!!"+editTextPassword.getEditText().getText().toString()+"!!!!"+editTextPasswordConfirm.getEditText().getText().toString()+"!!!!");
                    alertAboutUs.create().show();
                }

            }

        }
    }

    public void deleteUser(View view) {
        List<User> user = AppDataBase.getInstance(this).getUserDao().getAll();

        for (int i = 0; i < user.size(); i++) {
            AppDataBase.getInstance(this).getUserDao().delete(user.get(i));
        }
    }
}