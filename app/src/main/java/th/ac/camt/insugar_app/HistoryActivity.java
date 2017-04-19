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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    NavigationView navigation;
    private Button btnHisLongInsulin;
    private Button btnHisCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        byWidGet();
        initInstance();
        btnHisLongInsulin.setOnClickListener(this);
        btnHisCalculator.setOnClickListener(this);
    }

    private void byWidGet() {
        btnHisLongInsulin = (Button) findViewById(R.id.history_btn_hisLongInsulin);
        btnHisCalculator = (Button) findViewById(R.id.history_btn_hisCalculator);
    }

    @Override
    public void onClick(View v) {
        if (v == btnHisLongInsulin){
            Intent intent = new Intent(HistoryActivity.this, HistoryLongInsulinActivity.class);
            startActivity(intent);
        } else if (v == btnHisCalculator){
            Intent intent = new Intent(HistoryActivity.this, HistoryCalculatorActivity.class);
            startActivity(intent);
        }
    }

    private void initInstance() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                HistoryActivity.this,
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
                        Intent intentAcc = new Intent(HistoryActivity.this,
                                AccountActivity.class);
                        startActivity(intentAcc);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem1:
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem2:
                        Intent intentCal = new Intent(HistoryActivity.this,
                                CalculatorActivity.class);
                        startActivity(intentCal);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem3:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem4:
                        Intent intentKL = new Intent(HistoryActivity.this,
                                KnowledgeActivity.class);
                        startActivity(intentKL);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem5:
                        Intent intentSet = new Intent(HistoryActivity.this,
                                SettingsActivity.class);
                        startActivity(intentSet);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                HistoryActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_launcher);
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
        //Intent intent = new Intent(HistoryActivity.this, MenuActivity.class);
        //startActivity(intent);
        finish();
    }
}