package th.ac.camt.insugar_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.ac.camt.insugar_app.Model.Activity;

public class SumActivityListActivity extends AppCompatActivity {

    private List<Activity> activityList;
    private RecyclerView listActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_activity_list);
        activityList = new ArrayList<Activity>();
        listActivity = (RecyclerView) findViewById(R.id.listview_sum_activity);
        initInstance();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView activityName;
        TextView activityUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            activityName = (TextView)itemView.findViewById(R.id.activity_name);
            activityUnit = (TextView)itemView.findViewById(R.id.activity_unit);
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
        private List<Activity> activity;

        public RecyclerViewAdapter(List<Activity> activity) {
            this.activity = activity;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_activity, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.activityName.setText(activity.get(position).getName());
            holder.activityUnit.setText(activity.get(position).getUnit());
        }

        @Override
        public int getItemCount() {
            return activity.size();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == android.app.Activity.RESULT_OK) {
                String ActivityName = data.getStringExtra("ActivityName");
                String ActivityUnit = data.getStringExtra("ActivityUnit");
                activityList.add(new Activity(ActivityName, ActivityUnit));
                listActivity.setAdapter(new RecyclerViewAdapter(activityList));
            }
        }
    }
    private void initInstance() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_item_add) {
            Intent intent = new Intent(SumActivityListActivity.this, ActivityListActivity.class);
            startActivityForResult(intent, 2);
        } else if (item.getItemId() == R.id.menu_item_success){
            int sActivity = 0;
            for (Activity pair : activityList) {
                sActivity += Integer.parseInt(pair.getUnit());
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra("SumActivity",sActivity);
            setResult(android.app.Activity.RESULT_OK,returnIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
