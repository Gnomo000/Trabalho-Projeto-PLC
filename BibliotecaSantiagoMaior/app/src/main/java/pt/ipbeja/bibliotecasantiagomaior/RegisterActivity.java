package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static boolean loginDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        TextInputLayout editTextPasswordConfirm = findViewById(R.id.editTextPasswordRegisterConfirm);


        /*do {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
            alertAboutUs.setMessage("Dados não preenchidos");
            alertAboutUs.create().show();
        }while (editTextName.getText() == null || editTextDate.getTextSize() == 0 ||
                editTextEmail.getTextSize() == 0 || editTextPhone.getTextSize() == 0 ||
                editTextUsername.getTextSize() == 0 || editTextPassword.toString().length() == 0);*/


        if (AppDataBase.getInstance(this).getUserDao().getUserByEmail(editTextEmail.getText().toString()) != null) {
            AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
            alertAboutUs.setMessage("Email já existente");
            alertAboutUs.create().show();
        }else {
            if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty() || editTextDate.getText().toString().isEmpty()
                    || editTextPhone.getText().toString().isEmpty() || editTextUsername.getText().toString().isEmpty() || editTextPassword.getEditText().getText().toString().isEmpty() || editTextPasswordConfirm.getEditText().getText().toString().isEmpty()){

                AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                alertAboutUs.setMessage("Dados não preenchidos");
                alertAboutUs.create().show();

            }else{
                if (editTextPassword.getEditText().getText().toString() != editTextPasswordConfirm.getEditText().getText().toString()) {
                    AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                    alertAboutUs.setMessage("Senha não igual");
                    alertAboutUs.create().show();
                }else{
                    User user = new User(0,editTextName.getText().toString(),editTextDate.getText().toString(),editTextEmail.getText().toString(),editTextPhone.getText().toString(),editTextUsername.getText().toString(),editTextPassword.getEditText().getText().toString());
                    AppDataBase.getInstance(this).getUserDao().add(user);
                    AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
                    alertAboutUs.setMessage("OK");
                    alertAboutUs.create().show();
                }

            }

        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = String.format("%02d",dayOfMonth) + "/" + String.format("%02d",month+1) + "/" + year;
        EditText editTextDate = findViewById(R.id.editTextDateRegister);
        System.out.println(date);
        editTextDate.setText(date);
    }

    public void deleteRoom(View view) {
        List<User> user = AppDataBase.getInstance(this).getUserDao().getAll();

        for (int i = 0; i < user.size(); i++) {
            AppDataBase.getInstance(this).getUserDao().delete(user.get(i));
        }
    }
}