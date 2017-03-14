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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic1Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic2Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic3Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic4Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic5Activity;
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
                    case R.id.navItem1:
                        Intent intent = new Intent(KnowledgeActivity.this,
                                MenuActivity.class);
                        startActivity(intent);
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
                        Intent intentKL = new Intent(KnowledgeActivity.this,
                                KnowledgeActivity.class);
                        startActivity(intentKL);
                        finish();
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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.navItem8:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return false;
            }
        });

        int[] resId = { R.drawable.type1diabetes
                , R.drawable.shblue1, R.drawable.shblue1, R.drawable.shblue1
                , R.drawable.shblue1};

        String[] list = { "โรคเบาหวานชนิดที่ 1 [Type 1 diabetes]"
                , "Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 Topic2 "
                , "Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 Topic3 "
                , "Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 Topic4 "
                , "Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 Topic5 "
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
    }
}