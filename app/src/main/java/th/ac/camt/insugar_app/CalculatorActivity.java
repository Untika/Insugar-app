package th.ac.camt.insugar_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import th.ac.camt.insugar_app.Model.Check;
import th.ac.camt.insugar_app.Model.User;


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
    private EditText sumCarbo;
    private Spinner spinnerMultiply;
    private TextView txtTDD;
    private DecimalFormat df2;
    private SharedPreferences sharedPreferences;
    private String date;
    private boolean checkInputDialog = false;
    private User user;
    private EditText sumActivity;
    private Button btnListFood;
    private Button btnListActivity;
    private EditText targetBloodSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        byWidGet();
        if(date.equals("")){
            openDialog();
            date = String.valueOf(new SimpleDateFormat("yyyy/MM/dd", new Locale("TH")).format(new Date()));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("DATE",date);
            editor.commit();
        }else if(!(date.equals(String.valueOf(new SimpleDateFormat("yyyy/MM/dd", new Locale("TH")).format(new Date()))))){
            openDialog();
        }
        Log.i("Data :", date);
        Log.i("Locale :", String.valueOf(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", new Locale("TH")).format(new Date())));
        initInstance();
        spinner.setOnItemSelectedListener(this);
        spinnerMultiply.setOnItemSelectedListener(this);
        btnCal.setOnClickListener(this);
        btnListFood.setOnClickListener(this);
        btnListActivity.setOnClickListener(this);
        tDD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tDD.getText().length() > 0) {
                    tDD.setError(null);
                    weight.setError(null);
                    weight.setEnabled(false);
                    spinnerMultiply.setEnabled(false);
                    global.tDD = Double.parseDouble(tDD.getText().toString());
                } else {
                    weight.setEnabled(true);
                    weight.setFocusable(true);
                    spinnerMultiply.setEnabled(true);
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
                if (weight.getText().length() > 0) {
                    tDD.setEnabled(false);
                    try {
                        global.tDD = Double.parseDouble(weight.getText().toString()) * Double.parseDouble(spinnerMultiply.getSelectedItem().toString());
                        global.tDD = Double.parseDouble(df2.format(global.tDD));
                        Log.i("GTDD", String.valueOf(global.tDD));
                        txtTDD.setText(String.valueOf(global.tDD));
                    } catch (Exception e) {

                    }
                } else if (weight.getText().length() == 0) {
                    try {
                        global.tDD = 0.0;
                        tDD.setText("");
                        txtTDD.setText(null);
                        tDD.setEnabled(true);
                        tDD.setFocusable(true);
                    } catch (Exception e) {

                    }
                }
            }
        });
        txtTDD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (txtTDD.getText().length() > 0) {
                    tDD.setError(null);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(checkInputDialog){
            date = String.valueOf(new SimpleDateFormat("yyyy/MM/dd", new Locale("TH")).format(new Date()));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("DATE",date);
            editor.commit();
            Log.i("HHHHHHHHHHHH", "mark");
        }
    }

    private void openDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CalculatorActivity.this);
        alertDialog.setTitle(R.string.dialog_title);
        View v = getLayoutInflater().inflate(R.layout.dialog_longinsulin, null);

        final Spinner spinnerLongInsulin = (Spinner) v.findViewById(R.id.dialog_spinner);
        final EditText edtUnitLongInsulin = (EditText) v.findViewById(R.id.dialog_edtUnit);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalculatorActivity.this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.long_insulin_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLongInsulin.setAdapter(adapter);

        alertDialog.setPositiveButton("บันทึก", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setView(v);
        final AlertDialog dialog = alertDialog.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean wantToCloseDialog = false;

                if (spinnerLongInsulin.getSelectedItemId() != 0 && edtUnitLongInsulin.getText().length() != 0) {
                    wantToCloseDialog = true;
                }
                if (wantToCloseDialog) {
                    global.longInsulinName = spinnerLongInsulin.getSelectedItem().toString();
                    global.longInsulinUnit = Integer.parseInt(edtUnitLongInsulin.getText().toString());

                    Log.i("nameL :", String.valueOf(global.longInsulinName));
                    Log.i("unitL :", String.valueOf(global.longInsulinUnit));

                    new LongInsulinTask().execute(global.longInsulinName, (String.valueOf(user.getId())), String.valueOf(global.longInsulinUnit));
                    checkInputDialog = true;
                    dialog.dismiss();
                }

                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });

    }

    private class LongInsulinTask extends AsyncTask<String,Void,Check[]> {
        @Override
        protected Check[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("insulinName", params[0])
                        .add("idUser", params[1])
                        .add("unit", params[2])
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_ADD_LONGINSULIN)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Log.i("gof", result);

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Check>>() {
                }.getType();
                Collection<Check> enums = gson.fromJson(result, listType);
                Check[] checks = enums.toArray(new Check[enums.size()]);
                //Log.i("gof2", checks.toString());
                return checks;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Check[] result) {
            super.onPostExecute(result);
            switch (result[0].getCheck()) {
                case "Done Insert":
                    Toast.makeText(getApplicationContext(), "บันทึกเรียบร้อย", Toast.LENGTH_LONG).show();
                    break;
                case "Error Insert":
                    Toast.makeText(getApplicationContext(), "บันทึกไม่สำเร็จ", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "เกิดข้อผิดพลาด ไม่สามารถบันทึกได้", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    private void byWidGet() {
        global = (GlobalClass) getApplicationContext();
        user = global.getUser();
        sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        date = sharedPreferences.getString("DATE","");
        df2 = new DecimalFormat(".##");
        tDD = (EditText) findViewById(R.id.cal_edtTDD);
        weight = (EditText) findViewById(R.id.cal_edtWeight);
        spinner = (Spinner) findViewById(R.id.cal_spinner);
        btnCal = (Button) findViewById(R.id.cal_btn_cal);
        bloodSugar = (EditText) findViewById(R.id.cal_edtBloodSugar);
        sumCarbo = (EditText) findViewById(R.id.cal_sumCarbo);
        spinnerMultiply = (Spinner) findViewById(R.id.cal_multiplyWeight);
        txtTDD = (TextView) findViewById(R.id.cal_tvTDD);
        sumActivity = (EditText) findViewById(R.id.cal_sumActivity);
        btnListFood = (Button) findViewById(R.id.cal_btn_listFoods);
        btnListActivity = (Button) findViewById(R.id.cal_btn_listActivity);
        targetBloodSugar = (EditText) findViewById(R.id.cal_targetBloodSugar);
    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                CalculatorActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_launcher);
                        alertDialog.setMessage("\n          โมบายแอพพลิเคชันนี้พัฒนาขึ้นเพื่อช่วยให้ผู้ป่วยโรคเบาหวานชนิดที่ 1 สามารถคำนวณการฉีดอินซูลินในแต่ละมื้ออาหาร" +
                                "ในแต่ละวันได้อย่างเหมาะสมกับปริมาณการรับประทานอาหารของผู้ใช้ โดยผู้ใช้สามารถเห็นค่าการเปลี่ยนแปลงของระดับน้ำตาลในเลือดได้จากการบันทึกข้อมูลใ" +
                                "นแต่ละวัน และยังมีส่วนของการให้ความรู้เกี่ยวกับโรคเบาหวานเพื่อให้ผู้ใช้ได้ศึกษาเพิ่มเติมอีกด้วย\n");
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ปิด", new DialogInterface.OnClickListener() {
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

        if (parent == spinner) {
            if (position > 0 && position < 6) {
                global.insulinName = adapter.getItem(position).toString();
                global.insulinType = "Short Insulin";
                Log.i("Insulin Type :", global.insulinType);
            } else if (position >= 6) {
                global.insulinName = adapter.getItem(position).toString();
                global.insulinType = "Rapid Insulin";
                Log.i("Insulin Type :", global.insulinType);
            }
        } else if (parent == spinnerMultiply) {
            try {
                global.tDD = Double.parseDouble(weight.getText().toString()) * Double.parseDouble(spinnerMultiply.getSelectedItem().toString());
                global.tDD = Double.parseDouble(df2.format(global.tDD));
                Log.i("GTDD", String.valueOf(global.tDD));
                txtTDD.setText(String.valueOf(global.tDD));
            } catch (Exception e) {

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(CalculatorActivity.this, "No Select", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                float sCarbo = data.getFloatExtra("SumCarbo",0);
                sumCarbo.setText(String.valueOf(sCarbo));
                sumCarbo.setError(null);
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK){
                int sActivity = data.getIntExtra("SumActivity",0);
                sumActivity.setText(String.valueOf(sActivity));
                sumActivity.setError(null);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnListFood) {
            Intent intent = new Intent(CalculatorActivity.this, SumFoodListActivity.class);
            startActivityForResult(intent, 1);
        } else if (v == btnListActivity){
            Intent intent = new Intent(CalculatorActivity.this, SumActivityListActivity.class);
            startActivityForResult(intent, 2);
        } else if (v == btnCal) {
            if (tDD.getText().length() == 0 && txtTDD.getText().length() == 0 && weight.getText().length() == 0) {
                tDD.setError("ห้ามเว้นว่าง");
                weight.setError("ห้ามเว้นว่าง");
            }
            if (bloodSugar.getText().length() == 0) {
                bloodSugar.setError("ห้ามเว้นว่าง");
            }
            if (targetBloodSugar.getText().length() == 0) {
                targetBloodSugar.setError("ห้ามเว้นว่าง");
            }
            if (spinner.getSelectedItemPosition() == 0) {
                ((TextView) spinner.getSelectedView()).setError("");
            }
            if (sumCarbo.getText().length() == 0) {
                sumCarbo.setError("ห้ามเว้นว่าง");
            }
            if (sumActivity.getText().length() == 0) {
                sumActivity.setError("ห้ามเว้นว่าง");
            }
            if ((tDD.getText().length() != 0 || txtTDD.getText().length() != 0) && bloodSugar.getText().length() != 0 && targetBloodSugar.getText().length() != 0
                    && spinner.getSelectedItemPosition() != 0 && sumCarbo.getText().length() != 0 && sumActivity.getText().length() != 0) {

                bloodSugar.setError(null);
                global.bloodSugar = Double.parseDouble(bloodSugar.getText().toString());
                Log.i("blood sugar :", String.valueOf(global.bloodSugar));

                //check type of insulin for get results1
                double results1 = 0;
                if (global.insulinType.equals("Short Insulin")) {
                    results1 = Math.round(1500 / global.tDD);
                } else if (global.insulinType.equals("Rapid Insulin")) {
                    results1 = Math.round(1800 / global.tDD);
                }
                Log.i("results1 :", String.valueOf(results1));

                targetBloodSugar.setError(null);
                global.targetBloodSugar = Double.parseDouble(targetBloodSugar.getText().toString());
                Log.i("Target BS :", String.valueOf(global.targetBloodSugar));

                global.resultBloodSugar = global.bloodSugar - global.targetBloodSugar;
                Log.i("result BS :", String.valueOf(global.resultBloodSugar));

                if (global.resultBloodSugar <= results1) {
                    global.unit1 = 1;
                } else {
                    global.unit1 = (float) (global.resultBloodSugar / results1);
                    if (global.unit1 == 0) {
                        global.unit1 = 1;
                    }
                }
                Log.i("unit1 :", String.valueOf(global.unit1));

              /*  //calculate the unit
                if (global.bloodSugar <= results1) {
                    global.unit1 = 1;
                } else {
                    global.unit1 = (float) (global.bloodSugar / results1);
                    if (global.unit1 == 0) {
                        global.unit1 = 1;
                    }
                }
                Log.i("unit1 :", String.valueOf(global.unit1)); */

                ///// sum carbo /////
                sumCarbo.setError(null);
                global.sumCarbo = Double.parseDouble(sumCarbo.getText().toString());
                Log.i("Sum Carbo :", String.valueOf(global.sumCarbo));

                double results2 = Math.round(500 / global.tDD);
                Log.i("results2 :", String.valueOf(results2));

                if (global.sumCarbo <= results2) {
                    global.unit2 = 1;
                } else {
                    global.unit2 = (float) (global.sumCarbo / results2);
                    if (global.unit2 == 0) {
                        global.unit2 = 1;
                    }
                }
                Log.i("unit2 :", String.valueOf(global.unit2));

                global.sumUnit = global.unit1 + global.unit2;
                Log.i("sum unit1+2 :", String.valueOf(global.sumUnit));

                sumActivity.setError(null);
                global.sumActivity = Integer.parseInt(sumActivity.getText().toString());

                global.finalSumUnits = (int) Math.round(global.sumUnit - global.sumActivity);
                Log.i("final sum unit :", String.valueOf(global.finalSumUnits));

             /*   Log.i("Log doo :",String.valueOf(user.getId()));
                Log.i("Log doo :",global.insulinName);
                Log.i("Log doo :",weight.getText().toString());
                Log.i("Log doo :",String.valueOf(global.tDD));
                Log.i("Log doo :",String.valueOf(global.bloodSugar));
                Log.i("Log doo :",String.valueOf(global.unit1));
                Log.i("Log doo :",String.valueOf(results1));
                Log.i("Log doo :",String.valueOf(results2));
                Log.i("Log doo :",String.valueOf(global.sumCarbo));
                Log.i("Log doo :",String.valueOf(global.unit2));
                Log.i("Log doo :",String.valueOf(global.sumUnit));*/

                new CalculatorTask().execute(String.valueOf(user.getId()), global.insulinName, weight.getText().toString(),
                        String.valueOf(global.tDD), String.valueOf(global.bloodSugar), String.valueOf(global.unit1),
                        String.valueOf(results1), String.valueOf(results2), String.valueOf(global.sumCarbo),
                        String.valueOf(global.unit2), String.valueOf(global.sumUnit), String.valueOf(global.sumActivity),
                        String.valueOf(global.finalSumUnits), String.valueOf(global.targetBloodSugar), String.valueOf(global.resultBloodSugar));
            }
        }
    }

    private class CalculatorTask extends AsyncTask<String,Void,Check[]>{
        @Override
        protected Check[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("idUser", params[0])
                        .add("insulinName", params[1])
                        .add("weight", params[2])
                        .add("TDD", params[3])
                        .add("bloodSugar", params[4])
                        .add("unit1", params[5])
                        .add("result1", params[6])
                        .add("result2", params[7])
                        .add("sumCarbo", params[8])
                        .add("unit2", params[9])
                        .add("sumUnits", params[10])
                        .add("sumActivity", params[11])
                        .add("finalSumUnits", params[12])
                        .add("targetBloodSugar", params[13])
                        .add("resultBloodSugar", params[14])
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_ADD_CALCULATOR)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Log.i("gof", result);

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Check>>() {
                }.getType();
                Collection<Check> enums = gson.fromJson(result, listType);
                Check[] checks = enums.toArray(new Check[enums.size()]);
                Log.i("gof2", checks.toString());
                return checks;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Check[] result) {
            super.onPostExecute(result);
            switch (result[0].getCheck()) {
                case "Done Insert":
                    Intent intent = new Intent(CalculatorActivity.this, ResultActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case "Error Insert":
                    Toast.makeText(getApplicationContext(), "คำนวณไม่สำเร็จ", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "เกิดข้อผิดพลาด ไม่สามารถคำนวณได้", Toast.LENGTH_LONG).show();
                    break;
            }


            }
        }

/*
    private class CalculatorTask extends AsyncTask<String, Void, Check[]> {
        @Override
        protected Check[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("idUser", params[0])
                        .add("insulinName", params[1])
                        .add("weight", params[2])
                        .add("TDD", params[3])
                        .add("bloodSugar", params[4])
                        .add("unit1", params[5])
                        .add("result1", params[6])
                        .add("result2", params[7])
                        .add("sumCarbo", params[8])
                        .add("unit2", params[9])
                        .add("sumUnits", params[10])
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_CALCULATOR)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Log.i("gof", result);

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Check>>() {
                }.getType();
                Collection<Check> enums = gson.fromJson(result, listType);
                Check[] checks = enums.toArray(new Check[enums.size()]);
                Log.i("gof2", checks.toString());
                return checks;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Check[] result) {
            super.onPostExecute(result);

            if (result[0].getCheck().equals("Done Insert")) {
                Toast.makeText(getApplicationContext(), "เรียบร้อย", Toast.LENGTH_LONG).show();

            }

        }
    }
    */
}