package th.ac.camt.insugar_app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import th.ac.camt.insugar_app.Model.User;

public class MenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    NavigationView navigation;

    Button btnCal;
    Button btnHis;
    Button btnKnow;
    Button btnSet;
    GlobalClass global;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        global = (GlobalClass) getApplicationContext();
        user = global.getUser();
        Toast.makeText(getApplicationContext(), "สวัสดี คุณ "+ user.getFullName(), Toast.LENGTH_LONG).show();

        initInstance();
        Log.i("nameL test :", String.valueOf(global.longInsulinName));
        Log.i("unitL test :", String.valueOf(global.longInsulinUnit));
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        drawerLayout.closeDrawer(GravityCompat.START);
        Toast.makeText(this, "กรุณากด กลับ อีกครั้งเพื่อออก", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void initInstance() {
        btnCal = (Button) findViewById(R.id.btnCal);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCal = new Intent(MenuActivity.this,
                        CalculatorActivity.class);
                startActivity(intentCal);
            }
        });

        btnHis = (Button) findViewById(R.id.btnHis);
        btnHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHis = new Intent(MenuActivity.this,
                        HistoryActivity.class);
                startActivity(intentHis);
            }
        });

        btnKnow = (Button) findViewById(R.id.btnKnow);
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKnow = new Intent(MenuActivity.this,
                        KnowledgeActivity.class);
                startActivity(intentKnow);
            }
        });

        btnSet = (Button) findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSet = new Intent(MenuActivity.this,
                        SettingsActivity.class);
                startActivity(intentSet);
            }
        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MenuActivity.this,
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
                        Intent intentAcc = new Intent(MenuActivity.this,
                                AccountActivity.class);
                        startActivity(intentAcc);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem1:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem2:
                        Intent intentCal = new Intent(MenuActivity.this,
                                CalculatorActivity.class);
                        startActivity(intentCal);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem3:
                        Intent intentBlo = new Intent(MenuActivity.this,
                                HistoryActivity.class);
                        startActivity(intentBlo);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem4:
                        Intent intentKL = new Intent(MenuActivity.this,
                                KnowledgeActivity.class);
                        startActivity(intentKL);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem5:
                        Intent intentSet = new Intent(MenuActivity.this,
                                SettingsActivity.class);
                        startActivity(intentSet);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                MenuActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_launcher);
                        alertDialog.setMessage("โมบายแอปพลิเคชันนี้อยู่ภายใต้การควบคุมของทีมวิจัย Embedded Systems and Mobile Application โดยความร่วมมือระหว่าง คณะแพทยศาสตร์ คณะพยาบาลศาสตร์ และวิทยาลัยศิลปะ สื่อ และเทคโนโลยี มหาวิทยาลัยเชียงใหม่");
                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"ปิด", new OnClickListener() {
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
}
