package th.ac.camt.insugar_app;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import th.ac.camt.insugar_app.Model.Activity;
import th.ac.camt.insugar_app.Model.Food;

public class ActivityListActivity extends AppCompatActivity {


    private GlobalClass global;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        byWidget();
        initInstance();

        //Get all Food List from service.
        new ActivityListTask().execute();
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        recyclerView = (RecyclerView)findViewById(R.id.listview_activity);
    }

    private class ActivityListTask extends AsyncTask<String, Void, Activity[]> {
        @Override
        protected Activity[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_ACTIVITY_LIST)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Activity>>() {
                }.getType();
                Collection<Food> enums = gson.fromJson(result, listType);
                Activity[] activity = enums.toArray(new Activity[enums.size()]);
                return activity;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ActivityListActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("กรุณารอสักครู่...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Activity[] activity) {
            super.onPostExecute(activity);
            recyclerView.setAdapter(new ActivityListActivity.RecyclerViewAdapter(activity));
            progressDialog.dismiss();
        }
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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<ActivityListActivity.ViewHolder>{
        private Activity[] activity;

        public RecyclerViewAdapter(Activity[] activity) {
            this.activity = activity;
        }

        @Override
        public ActivityListActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_activity, parent, false);
            return new ActivityListActivity.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ActivityListActivity.ViewHolder holder, int position) {
            holder.activityName.setText(activity[position].getName());
            holder.activityUnit.setText(activity[position].getUnit());
        }

        @Override
        public int getItemCount() {
            return activity.length;
        }
    }
    private void initInstance() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
