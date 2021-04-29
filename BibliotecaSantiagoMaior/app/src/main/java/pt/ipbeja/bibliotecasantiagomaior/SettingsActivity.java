package pt.ipbeja.bibliotecasantiagomaior;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.polyak.iconswitch.IconSwitch;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    private IconSwitch iconSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // initializing the IconSwitch object
        iconSwitch = (IconSwitch) findViewById(R.id.iconSwitch);

        // IconSwitch  Checked Change Listener
        iconSwitch.setCheckedChangeListener(new IconSwitch.CheckedChangeListener() {
        @Override
        public void onCheckChanged(IconSwitch.Checked current) {
            //simple witch case
            switch (current) {

                case LEFT:
                    //showing simple toast message to the user
                    Toast.makeText(SettingsActivity.this, "Light Mode", Toast.LENGTH_SHORT).show();
                    break;

                case RIGHT:
                    //showing simple toast message to the user
                    Toast.makeText(SettingsActivity.this, "Dark Mode", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        });
    }
}