package th.ac.camt.insugar_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigation;
    private EditText tDD;
    private EditText weight;
    GlobalClass global;
    private Spinner spinner;
    private Button btnCal;
    private EditText bloodSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        byWidGet();
        initInstance();
        spinner.setOnItemSelectedListener(this);
        btnCal.setOnClickListener(this);
        tDD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tDD.getText().length()>0) {
                    tDD.setError(null);
                    weight.setError(null);
                    global.tDD = Double.parseDouble(tDD.getText().toString());
                }
            }
        });
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(weight.getText().length()>0){
                    try{
                        global.tDD = Double.parseDouble(weight.getText().toString())*0.5;
                        tDD.setText(String.valueOf(global.tDD));
                        tDD.setEnabled(false);
                    }catch (Exception e){

                    }
                }else if(weight.getText().length()==0){
                    try{
                        global.tDD = 0.0;
                        tDD.setText("");
                        tDD.setEnabled(true);
                    }catch (Exception e){

                    }
                }else {
                    tDD.setEnabled(true);
                    tDD.setFocusable(true);
                }
            }
        });
    }



    private void byWidGet() {
        global = (GlobalClass) getApplicationContext();
        tDD = (EditText) findViewById(R.id.cal_edtTDD);
        weight = (EditText) findViewById(R.id.cal_edtWeight);
        spinner = (Spinner) findViewById(R.id.cal_spinner);
        btnCal = (Button) findViewById(R.id.cal_btn_cal);
        bloodSugar = (EditText) findViewById(R.id.cal_edtBloodSugar);
    }

    private void initInstance() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                CalculatorActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem0:
                        Intent intentAcc = new Intent(CalculatorActivity.this,
                                AccountActivity.class);
                        startActivity(intentAcc);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem1:
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem3:
                        Intent intentBlo = new Intent(CalculatorActivity.this,
                                HistoryActivity.class);
                        startActivity(intentBlo);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem4:
                        Intent intentKL = new Intent(CalculatorActivity.this,
                                KnowledgeActivity.class);
                        startActivity(intentKL);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem5:
                        Intent intentSet = new Intent(CalculatorActivity.this,
                                SettingsActivity.class);
                        startActivity(intentSet);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem6:
                        AlertDialog DialogHelp = new AlertDialog.Builder(
                                CalculatorActivity.this).create();
                        DialogHelp.setTitle("ช่วยเหลือ");
                        DialogHelp.setIcon(R.mipmap.ic_help_black_24dp);
                        DialogHelp.setMessage("\n Coming Soon... \n");
                        DialogHelp.setButton(DialogInterface.BUTTON_POSITIVE,"ปิด", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        DialogHelp.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                CalculatorActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_info_black_24dp);
                        alertDialog.setMessage("\n          โมบายแอพพลิเคชันนี้พัฒนาขึ้นเพื่อช่วยให้ผู้ป่วยโรคเบาหวานชนิดที่ 1 สามารถคำนวณการฉีดอินซูลินในแต่ละมื้ออาหาร" +
                                "ในแต่ละวันได้อย่างเหมาะสมกับปริมาณการรับประทานอาหารของผู้ใช้ โดยผู้ใช้สามารถเห็นค่าการเปลี่ยนแปลงของระดับน้ำตาลในเลือดได้จากการบันทึกข้อมูลใ" +
                                "นแต่ละวัน และยังมีส่วนของการให้ความรู้เกี่ยวกับโรคเบาหวานเพื่อให้ผู้ใช้ได้ศึกษาเพิ่มเติมอีกด้วย\n");
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"ปิด", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        drawerLayout.closeDrawer(GravityCompat.START);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();

        if(position >0 && position < 6 ){
            global.insulinName = adapter.getItem(position).toString();
            global.insulinType = "Short Insulin";
            Log.i("Insulin Type :", global.insulinType);
        }else if(position >=6) {
            global.insulinName = adapter.getItem(position).toString();
            global.insulinType = "Rapid Insulin";
            Log.i("Insulin Type :", global.insulinType);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(CalculatorActivity.this, "No Select", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnCal){
            if(tDD.getText().length()==0 && weight.getText().length()==0){
                tDD.setError("ห้ามเว้นว่าง");
                weight.setError("ห้ามเว้นว่าง");
            }
            if(bloodSugar.getText().length()==0){
                bloodSugar.setError("ห้ามเว้นว่าง");
            }
            if(spinner.getSelectedItemPosition()==0){
                ((TextView)spinner.getSelectedView()).setError("");
            }
            if(tDD.getText().length()!=0 && weight.getText().length()!=0 && bloodSugar.getText().length()!=0 && spinner.getSelectedItemPosition()!=0){

                bloodSugar.setError(null);
                global.bloodSugar = Double.parseDouble(bloodSugar.getText().toString());
                Log.i("blood sugar :", String.valueOf(global.bloodSugar));

                //check type of insulin for get results1
                double results1 = 0;
                if (global.insulinType.equals("Short Insulin")) {
                    results1 = 1500 / global.tDD;
                } else if (global.insulinType.equals("Rapid Insulin")) {
                    results1 = 1800 / global.tDD;
                }
                Log.i("results1 :", String.valueOf(results1));

                //calculate the unit
                if (global.bloodSugar <= results1) {
                    global.unit = 1;
                } else {
                    global.unit = (int) (global.bloodSugar / results1);
                    if (global.unit == 0) {
                        global.unit = 1;
                    }
                }
                Log.i("unit :", String.valueOf(global.unit));
            }
        }
    }
}