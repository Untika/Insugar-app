package th.ac.camt.insugar_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigation;

    private EditText tDD;
    private EditText weight;
    GlobalClass global;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        byWidGet();
        initInstance();
        spinner.setOnItemSelectedListener(this);
        tDD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(tDD.getText().length()>0){

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
                Double tDDCal;
                if(weight.getText().length()>0){
                   // tDD.setEnabled(false);
                    try{
                        tDDCal = Double.parseDouble(weight.getText().toString())*0.5;
                        tDD.setText(String.valueOf(tDDCal));
                        tDD.setEnabled(false);
                        //Log.i("TDD", String.valueOf(tDDCal));
                    }catch (Exception e){

                    }
                }else if(weight.getText().length()==0){
                    try{
                        tDDCal = 0.0;
                        tDD.setText("");
                        tDD.setEnabled(true);
                    }catch (Exception e){

                    }

                }
            }
        });
    }

    private void calTDD() {
        Double tDDCal;
        //String weightStr = weight.getText().toString();

        try{
            tDDCal = Double.parseDouble(weight.getText().toString())*0.5;
            Log.i("TDD", String.valueOf(tDDCal));
        }catch (Exception e){

        }
    }

    private void byWidGet() {
        global = (GlobalClass) getApplicationContext();
        tDD = (EditText) findViewById(R.id.cal_edtTDD);
        weight = (EditText) findViewById(R.id.cal_edtWeight);
        spinner = (Spinner) findViewById(R.id.cal_spinner);

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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.navItem8:
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
        //Intent intent = new Intent(CalculatorActivity.this, MenuActivity.class);
        //startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
        if (position < 1) {

        }else if(position < 6){
            String toast = adapter.getItem(position).toString() + " is Short Insulin";
            Toast.makeText(CalculatorActivity.this, toast, Toast.LENGTH_LONG).show();
        }else {
            String toast = adapter.getItem(position).toString() + " is Rapid Insulin";
            Toast.makeText(CalculatorActivity.this, toast, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(CalculatorActivity.this, "No Select", Toast.LENGTH_LONG).show();
    }
}