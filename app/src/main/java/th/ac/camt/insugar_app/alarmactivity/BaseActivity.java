package th.ac.camt.insugar_app.alarmactivity;

/**
 * Created by MARK on 26/2/2560.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import th.ac.camt.insugar_app.R;
import th.ac.camt.insugar_app.preferences.AlarmPreferencesActivity;
import th.ac.camt.insugar_app.service.AlarmServiceBroadcastReciever;

import java.lang.reflect.Field;

public abstract class BaseActivity  extends AppCompatActivity implements android.view.View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String url = null;
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.menu_item_new:
                Intent newAlarmIntent = new Intent(this, AlarmPreferencesActivity.class);
                startActivity(newAlarmIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void callMathAlarmScheduleService() {
        Intent mathAlarmServiceIntent = new Intent(this, AlarmServiceBroadcastReciever.class);
        sendBroadcast(mathAlarmServiceIntent, null);
    }
}