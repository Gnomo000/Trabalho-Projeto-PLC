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
        switch (selectedTheme){
            case THEME_DARK:
                setTheme(R.style.Theme_BibliotecaSantiagoMaior_Dark_Default);
                Log.i("Teste","Escuro");
                break;
            case THEME_LIGHT:
                setTheme(R.style.Theme_BibliotecaSantiagoMaior_Light_Default);
                Log.i("Teste","Claro");
                break;
        }

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



        // IconSwitch  Checked Change Listener
        iconSwitch.setCheckedChangeListener(new IconSwitch.CheckedChangeListener() {
        @Override
        public void onCheckChanged(IconSwitch.Checked current) {

            SharedPreferences.Editor editor = sharedPref.edit();
            //simple witch case
            switch (current) {

                case LEFT:
                    //showing simple toast message to the user
                    Toast.makeText(SettingsActivity.this, "Tema Claro", Toast.LENGTH_SHORT).show();
                    editor.putString(SETTINGS_THEME, THEME_LIGHT);
                    editor.apply();
                    recreate();
                    break;

                case RIGHT:
                    Toast.makeText(SettingsActivity.this, "Tema Escuro", Toast.LENGTH_SHORT).show();
                    editor.putString(SETTINGS_THEME, THEME_DARK);
                    editor.apply();
                    recreate();
                    break;
            }
        }
        });
    }

    public void onClickAboutUs(View view) {
        AlertDialog.Builder alertAboutUs = new AlertDialog.Builder(this);
        alertAboutUs.setTitle("Sobre Nós");
        alertAboutUs.setMessage("ETC");
        alertAboutUs.create().show();
    }
}