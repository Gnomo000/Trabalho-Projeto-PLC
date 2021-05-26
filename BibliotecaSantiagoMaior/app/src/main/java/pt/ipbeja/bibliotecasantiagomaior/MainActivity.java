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

public class MainActivity extends AppCompatActivity {



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

    }

}