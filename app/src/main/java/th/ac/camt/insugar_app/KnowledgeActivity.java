package th.ac.camt.insugar_app;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic1Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic2Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic3Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic4Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic5Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic6Activity;
import th.ac.camt.insugar_app.ActivitySecondKnowledge.KnowledgeTopic7Activity;

public class KnowledgeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    NavigationView navigation;

    //ArrayList thats going to hold the search results
    ArrayList<HashMap<String, Object>> searchResults;

    //ArrayList that will hold the original Data
    ArrayList<HashMap<String, Object>> originalValues;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);

        final EditText searchBox = (EditText) findViewById(R.id.searchBox);
        ListView playerListView = (ListView) findViewById(android.R.id.list);

        //get the LayoutInflater for inflating the customomView
        //this will be used in the custom adapter
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //these arrays are just the data that
        //I'll be using to populate the ArrayList
        //You can use our own methods to get the data
        String names[] = {"ความรู้ทั่วไปเรื่องโรคเบาหวาน", "อาหารกับผู้เป็นเบาหวาน", "เบาหวานขึ้นตา", "โรคไตจากเบาหวาน"
                , "การดูแลสุขภาพเท้าของผู้ป่วยเบาหวาน", "วิธีการใช้ยาอินซูลินแบบปากกา", "ข้อควรระวังต่างๆ ในผู้ป่วยเบาหวานที่ใช้ยาฉีดอินซูลิน"};

        final Integer[] photos = {R.drawable.kl1, R.drawable.kl2, R.drawable.kl3, R.drawable.kl4,
                R.drawable.kl5, R.drawable.kl6, R.drawable.kl7};

        originalValues = new ArrayList<HashMap<String, Object>>();

        //temporary HashMap for populating the
        //Items in the ListView
        HashMap<String, Object> temp;

        //total number of rows in the ListView
        int noOfPlayers = names.length;

        //now populate the ArrayList players
        for (int i = 0; i < noOfPlayers; i++) {
            temp = new HashMap<String, Object>();

            temp.put("name", names[i]);
            temp.put("photo", photos[i]);

            //add the row to the ArrayList
            originalValues.add(temp);
        }
        //searchResults=OriginalValues initially
        searchResults = new ArrayList<HashMap<String, Object>>(originalValues);

        //create the adapter
        //first param-the context
        //second param-the id of the layout file
        //you will be using to fill a row
        //third param-the set of values that
        //will populate the ListView
        final CustomAdapter adapter = new CustomAdapter(this, R.layout.item_knowledge_layout, searchResults);

        //finally,set the adapter to the default ListView
        playerListView.setAdapter(adapter);
        searchBox.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the text in the EditText
                String searchString = searchBox.getText().toString();
                int textLength = searchString.length();
                searchResults.clear();

                for (int i = 0; i < originalValues.size(); i++) {
                    String playerName = originalValues.get(i).get("name").toString();
                    if (textLength <= playerName.length()) {
                        //compare the String in EditText with Names in the ArrayList
                        if (searchString.equalsIgnoreCase(playerName.substring(0, textLength)))
                            searchResults.add(originalValues.get(i));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
            }
        });
/*
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (String.valueOf(title.getText())) {
                  case "ความรู้ทั่วไปเรื่องโรคเบาหวาน":
                                Intent intentKL1 = new Intent(KnowledgeActivity.this, KnowledgeTopic1Activity.class);
                                startActivity(intentKL1);
                                break;
                            case "อาหารกับผู้เป็นเบาหวาน":
                                Intent intentKL2 = new Intent(KnowledgeActivity.this, KnowledgeTopic2Activity.class);
                                startActivity(intentKL2);
                                break;
                            case "เบาหวานขึ้นตา":
                                Intent intentKL3 = new Intent(KnowledgeActivity.this, KnowledgeTopic3Activity.class);
                                startActivity(intentKL3);
                                break;
                            case "โรคไตจากเบาหวาน":
                                Intent intentKL4 = new Intent(KnowledgeActivity.this, KnowledgeTopic4Activity.class);
                                startActivity(intentKL4);
                                break;
                            case "การดูแลสุขภาพเท้าของผู้ป่วยเบาหวาน":
                                Intent intentKL5 = new Intent(KnowledgeActivity.this, KnowledgeTopic5Activity.class);
                                startActivity(intentKL5);
                                break;
                            case "วิธีการใช้ยาอินซูลินแบบปากกา":
                                Intent intentKL6 = new Intent(KnowledgeActivity.this, KnowledgeTopic6Activity.class);
                                startActivity(intentKL6);
                                break;
                            case "ข้อควรระวังต่างๆ ในผู้ป่วยเบาหวานที่ใช้ยาฉีดอินซูลิน":
                                Intent intentKL7 = new Intent(KnowledgeActivity.this, KnowledgeTopic7Activity.class);
                                startActivity(intentKL7);
                                break;
                }
            }
        });
*/

            initInstance();
    }

    //define your custom adapter
    private class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<HashMap<String, Object>> Strings) {

            //let android do the initializing :)
            super(context, textViewResourceId, Strings);
        }


        //class for caching the views in a row
        private class ViewHolder {
            ImageView photo;
            TextView name;

        }

        ViewHolder viewHolder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_knowledge_layout, null);
                viewHolder = new ViewHolder();

                //cache the views
                viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);

                //link the cached views to the convertview
                convertView.setTag(viewHolder);
                listOnClick(viewHolder,convertView);
            } else
                viewHolder = (ViewHolder) convertView.getTag();


            int photoId = (Integer) searchResults.get(position).get("photo");

            //set the data to be displayed
            viewHolder.photo.setImageDrawable(getResources().getDrawable(photoId));
            viewHolder.name.setText(searchResults.get(position).get("name").toString());
            listOnClick(viewHolder,convertView);
            //return the view to be displayed
            return convertView;
        }

        private void listOnClick(final ViewHolder viewHolder, View convertView) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(getApplicationContext(),viewHolder.name.getText(), Toast.LENGTH_LONG).show();
                    switch (String.valueOf(viewHolder.name.getText())) {
                        case "ความรู้ทั่วไปเรื่องโรคเบาหวาน":
                            Intent intentKL1 = new Intent(getApplicationContext(), KnowledgeTopic1Activity.class);
                            startActivity(intentKL1);
                            break;
                        case "อาหารกับผู้เป็นเบาหวาน":
                            Intent intentKL2 = new Intent(getApplicationContext(), KnowledgeTopic2Activity.class);
                            startActivity(intentKL2);
                            break;
                        case "เบาหวานขึ้นตา":
                            Intent intentKL3 = new Intent(getApplicationContext(), KnowledgeTopic3Activity.class);
                            startActivity(intentKL3);
                            break;
                        case "โรคไตจากเบาหวาน":
                            Intent intentKL4 = new Intent(getApplicationContext(), KnowledgeTopic4Activity.class);
                            startActivity(intentKL4);
                            break;
                        case "การดูแลสุขภาพเท้าของผู้ป่วยเบาหวาน":
                            Intent intentKL5 = new Intent(getApplicationContext(), KnowledgeTopic5Activity.class);
                            startActivity(intentKL5);
                            break;
                        case "วิธีการใช้ยาอินซูลินแบบปากกา":
                            Intent intentKL6 = new Intent(getApplicationContext(), KnowledgeTopic6Activity.class);
                            startActivity(intentKL6);
                            break;
                        case "ข้อควรระวังต่างๆ ในผู้ป่วยเบาหวานที่ใช้ยาฉีดอินซูลิน":
                            Intent intentKL7 = new Intent(getApplicationContext(), KnowledgeTopic7Activity.class);
                            startActivity(intentKL7);
                            break;
                    }
                }
            });
        }
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

                    case R.id.navItem8:
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                KnowledgeActivity.this).create();
                        alertDialog.setTitle("เกี่ยวกับ INSUGAR");
                        alertDialog.setIcon(R.mipmap.ic_launcher);
                        alertDialog.setMessage("โมบายแอปพลิเคชันนี้อยู่ภายใต้การควบคุมของทีมวิจัย Embedded Systems and Mobile Application โดยความร่วมมือระหว่าง" +
                                "คณะแพทยศาสตร์ คณะพยาบาลศาสตร์ และวิทยาลัยศิลปะ สื่อ และเทคโนโลยี มหาวิทยาลัยเชียงใหม่");
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
        //Intent intent = new Intent(KnowledgeActivity.this, MenuActivity.class);
        //startActivity(intent);
        finish();
    }
}