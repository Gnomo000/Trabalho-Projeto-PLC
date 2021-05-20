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

    public static final String THEME_DARK = "dark";
    public static final String THEME_LIGHT = "light";
    public static final String SETTINGS_THEME = "theme";

    private IconSwitch iconSwitch;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.sharedPref = SettingsActivity.this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String selectedTheme = this.sharedPref.getString(SETTINGS_THEME, THEME_LIGHT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // initializing the IconSwitch object
        iconSwitch = (IconSwitch) findViewById(R.id.iconSwitch);


        switch (selectedTheme) {
            case THEME_DARK:
                iconSwitch.setChecked(IconSwitch.Checked.RIGHT);
                break;
            case THEME_LIGHT:
                iconSwitch.setChecked(IconSwitch.Checked.LEFT);
                break;
        }

    }

    public void onClickAboutUs(View view) {
        AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
        alertAboutUs.setTitle("Sobre Nós");
        alertAboutUs.setMessage("Escola Básica de Santiago Maior é uma instituição publica que integra a rede de ensino nacional \r\n" +
                "Catálogo Eletrónico é uma aplicação desenvolvida para facilitar a requizição de livros da nossa biblioteca");
        alertAboutUs.create().show();
    }
}