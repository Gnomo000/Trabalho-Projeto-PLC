package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity /*implements DatePickerDialog.OnDateSetListener */{

    private TextView dateText;

    public static Activity CloseMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO invocar o método do SessionManager para validar se existe sessão
        // se existir sessão saltam para a HomeActivity

        CloseMainActivity=this;
        if (LoginActivity.loginDone == true) {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            this.finish();
        }
    }



    public void goToLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

        /*
        dateText = findViewById(R.id.editTextDateRegister);

        findViewById(R.id.buttonCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

         */
    }
    /*
        private void showDatePickerDialog() {
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
        String date = dayOfMonth + "/" + month + "/" + year;
        dateText.setText(date);
    }
     */




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}