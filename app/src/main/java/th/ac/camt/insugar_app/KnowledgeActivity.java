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
import android.widget.AdapterView;
import android.widget.ListView;

import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic1Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic2Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic3Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic4Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic5Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic6Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic7Activity;
import th.ac.camt.insugar_app.AdapterKnowledge.KnowledgeListAdapter;

public class KnowledgeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    NavigationView navigation;

    ListView listView;
    KnowledgeListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        initInstance();

    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                KnowledgeActivity.this,
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
                        Intent intentAcc = new Intent(KnowledgeActivity.this,
                                AccountActivity.class);
                        startActivity(intentAcc);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem1:
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem2:
                        Intent intentCal = new Intent(KnowledgeActivity.this,
                                CalculatorActivity.class);
                        startActivity(intentCal);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem3:
                        Intent intentBlo = new Intent(KnowledgeActivity.this,
                                HistoryActivity.class);
                        startActivity(intentBlo);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem4:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem5:
                        Intent intentSet = new Intent(KnowledgeActivity.this,
                                SettingsActivity.class);
                        startActivity(intentSet);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navItem6:
                        AlertDialog DialogHelp = new AlertDialog.Builder(
                                KnowledgeActivity.this).create();
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
                                KnowledgeActivity.this).create();
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

        int[] resId = { R.drawable.kl1
                , R.drawable.kl2, R.drawable.kl3, R.drawable.kl4
                , R.drawable.kl5, R.drawable.kl6, R.drawable.kl71};

        String[] list = { "ความรู้ทั่วไปเรื่องโรคเบาหวาน"
                , "อาหารกับผู้เป็นเบาหวาน"
                , "เบาหวานขึ้นตา"
                , "โรคไตจากเบาหวาน "
                , "การดูแลสุขภาพเท้าของผู้ป่วยเบาหวาน"
                , "วิธีการใช้ยาอินซูลินแบบปากกา"
                , "ข้อควรระวังต่างๆ ในผู้ป่วยเบาหวานที่ใช้ยาฉีดอินซูลิน"
        };


        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new KnowledgeListAdapter(this, list, resId);
        listView.setAdapter(listAdapter);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0
                    , View arg1, int arg2, long arg3) {
                Intent intent;
                switch(arg2) {
                    case 0 :
                        Intent intentKL1 = new Intent(KnowledgeActivity.this, KnowledgeTopic1Activity.class);
                        startActivity(intentKL1);
                        break;
                    case 1 :
                        Intent intentKL2 = new Intent(KnowledgeActivity.this, KnowledgeTopic2Activity.class);
                        startActivity(intentKL2);
                        break;
                    case 2 :
                        Intent intentKL3 = new Intent(KnowledgeActivity.this, KnowledgeTopic3Activity.class);
                        startActivity(intentKL3);
                        break;
                    case 3 :
                        Intent intentKL4 = new Intent(KnowledgeActivity.this, KnowledgeTopic4Activity.class);
                        startActivity(intentKL4);
                        break;
                    case 4 :
                        Intent intentKL5 = new Intent(KnowledgeActivity.this, KnowledgeTopic5Activity.class);
                        startActivity(intentKL5);
                        break;
                    case 5 :
                        Intent intentKL6 = new Intent(KnowledgeActivity.this, KnowledgeTopic6Activity.class);
                        startActivity(intentKL6);
                        break;
                    case 6:
                        Intent intentKL7 = new Intent(KnowledgeActivity.this, KnowledgeTopic7Activity.class);
                        startActivity(intentKL7);
                        break;
                }
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
        //Intent intent = new Intent(KnowledgeActivity.this, MenuActivity.class);
        //startActivity(intent);
        finish();
    }
}