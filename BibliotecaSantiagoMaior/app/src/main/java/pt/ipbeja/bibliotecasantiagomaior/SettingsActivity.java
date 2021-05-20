package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.polyak.iconswitch.IconSwitch;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClickAboutUs(View view) {
        AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
        alertAboutUs.setTitle("Sobre Nós");
        alertAboutUs.setMessage("Escola Básica de Santiago Maior é uma instituição publica que integra a rede de ensino nacional \r\n" +
                "Catálogo Eletrónico é uma aplicação desenvolvida para facilitar a requizição de livros da nossa biblioteca");
        alertAboutUs.create().show();
    }
}