package th.ac.camt.insugar_app.alarmactivity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import th.ac.camt.insugar_app.R;
import th.ac.camt.insugar_app.database.Database;
import th.ac.camt.insugar_app.preferences.AlarmPreferencesActivity;

public class AlarmActivity extends BaseActivity {

    ImageButton newButton;
    ListView mathAlarmListView;
    AlarmListAdapter alarmListAdapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alarm_activity);

        initInstance();

        mathAlarmListView = (ListView) findViewById(android.R.id.list);
        mathAlarmListView.setLongClickable(true);
        mathAlarmListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                final Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
                Builder dialog = new Builder(AlarmActivity.this);
                dialog.setTitle("ลบ");
                dialog.setMessage("ลบเวลาแจ้งเตือนนี้หรือไม่");
                dialog.setPositiveButton("ตกลง", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Database.init(AlarmActivity.this);
                        Database.deleteEntry(alarm);
                        AlarmActivity.this.callMathAlarmScheduleService();

                        updateAlarmList();
                    }
                });
                dialog.setNegativeButton("ยกเลิก", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                return true;
            }
        });

        callMathAlarmScheduleService();

        alarmListAdapter = new AlarmListAdapter(this);
        this.mathAlarmListView.setAdapter(alarmListAdapter);
        mathAlarmListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
                Intent intent = new Intent(AlarmActivity.this, AlarmPreferencesActivity.class);
                intent.putExtra("alarm", alarm);
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.findItem(R.id.menu_item_save).setVisible(false);
        menu.findItem(R.id.menu_item_delete).setVisible(false);
        return result;
    }

    @Override
    protected void onPause() {
        // setListAdapter(null);
        Database.deactivate();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAlarmList();
    }

    public void updateAlarmList(){
        Database.init(AlarmActivity.this);
        final List<Alarm> alarms = Database.getAll();
        alarmListAdapter.setMathAlarms(alarms);

        runOnUiThread(new Runnable() {
            public void run() {
                // reload content
                AlarmActivity.this.alarmListAdapter.notifyDataSetChanged();
                if(alarms.size() > 0){
                    findViewById(android.R.id.empty).setVisibility(View.INVISIBLE);
                }else{
                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkBox_alarm_active) {
            CheckBox checkBox = (CheckBox) v;
            Alarm alarm = (Alarm) alarmListAdapter.getItem((Integer) checkBox.getTag());
            alarm.setAlarmActive(checkBox.isChecked());
            Database.update(alarm);
            AlarmActivity.this.callMathAlarmScheduleService();
            if (checkBox.isChecked()) {
                Toast.makeText(AlarmActivity.this, alarm.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void initInstance() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}