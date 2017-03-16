package th.ac.camt.insugar_app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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

public class MenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    NavigationView navigation;

    Button btnCal;
    Button btnHis;
    Button btnKnow;
    Button btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initInstance();

    }

    public void onBackPressed() {
        drawerLayout.closeDrawer(GravityCompat.START);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("EXIT");
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit ?");
        dialog.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("Cancle", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem1:
                        Intent intent = new Intent(MenuActivity.this,
                                MenuActivity.class);
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem2:
                        Intent intentCal = new Intent(MenuActivity.this,
                                CalculatorActivity.class);
                        startActivity(intentCal);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem3:
                        Intent intentBlo = new Intent(MenuActivity.this,
                                HistoryActivity.class);
                        startActivity(intentBlo);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem4:
                        Intent intentKL = new Intent(MenuActivity.this,
                                KnowledgeActivity.class);
                        startActivity(intentKL);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem5:
                        Intent intentSet = new Intent(MenuActivity.this,
                                SettingsActivity.class);
                        startActivity(intentSet);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem6:
                        AlertDialog DialogHelp = new AlertDialog.Builder(
                                MenuActivity.this).create();
                        DialogHelp.setTitle("ช่วยเหลือ");
                        DialogHelp.setIcon(R.mipmap.ic_launcher);
                        DialogHelp.setMessage("\n Q : Question \n" + "A : Answer \n" + "\n Q : Question \n" + "A : Answer \n"
                        + "\n Q : Question \n" + "A : Answer \n" + "\n Q : Question \n" + "A : Answer \n"
                        + "\n Q : Question \n" + "A : Answer \n" + "\n Q : Question \n" + "A : Answer \n"
                        + "\n Q : Question \n" + "A : Answer \n" + "\n Q : Question \n" + "A : Answer \n"
                        + "\n Q : Question \n" + "A : Answer \n" + "\n Q : Question \n" + "A : Answer \n");
                        DialogHelp.setButton(DialogInterface.BUTTON_POSITIVE,"ปิด", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        DialogHelp.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                MenuActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_launcher);
                        alertDialog.setMessage("\n          I got high got so high\n" + "กินขนมหวานแล้วก็นั่งจิบไวน์\n" +
                                "มีกัญชลีเป็นของคู่กาย\n" + "ตอนแรกกูตาลายตอนนี้กูตาส่าย\n" + "ถ้าเบื่อหน่ายไปผ่อนคลาย\n" + "ออกจากบ้านไปแถวหาดทราย\n" +
                                "หาเวลาว่างให้ตัวเองตอนบ่าย\n" + "ได้หมดถ้าสดชื่นทำให้ใจสบาย\n" + "Say goodbye เรื่องเบื่อหน่าย \n" +
                                "เรื่องแย่ๆ I never mind\n" + "เสียเวลาเสียค่าใช้จ่าย\n" + "เสียแค่นี้กูไม่ค่าตัวตาย\n" + "ไม่เป็นไร แค่ปล่อยไป\n" +
                                "ไม่ต้องคิดให้มันปวดใจ\n" + "วันพรุ้งนี้เราแค่เริ่มใหม่\n" + "Wake up in the morning ลืมมันไป \n");
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