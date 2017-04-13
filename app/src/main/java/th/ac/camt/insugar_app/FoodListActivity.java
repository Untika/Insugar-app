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
import th.ac.camt.insugar_app.Model.Food;

public class FoodListActivity extends AppCompatActivity {


    private GlobalClass global;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        byWidget();
        initInstance();

        //Get all Food List from service.
        new FoodListTask().execute();
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        recyclerView = (RecyclerView)findViewById(R.id.listview_food);
    }

    private class FoodListTask extends AsyncTask<String, Void, Food[]> {
        @Override
        protected Food[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_FOOD_LIST)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Food>>() {
                }.getType();
                Collection<Food> enums = gson.fromJson(result, listType);
                Food[] foods = enums.toArray(new Food[enums.size()]);
                return foods;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FoodListActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("กรุณารอสักครู่...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Food[] foods) {
            super.onPostExecute(foods);
            recyclerView.setAdapter(new FoodListActivity.RecyclerViewAdapter(foods));
            progressDialog.dismiss();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName;
        TextView foodCarbo;

        public ViewHolder(View itemView) {
            super(itemView);
            foodName = (TextView)itemView.findViewById(R.id.food_name);
            foodCarbo = (TextView)itemView.findViewById(R.id.food_carbo);
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<FoodListActivity.ViewHolder>{
        private Food[] foods;

        public RecyclerViewAdapter(Food[] foods) {
            this.foods = foods;
        }

        @Override
        public FoodListActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_foodlist, parent, false);
            return new FoodListActivity.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(FoodListActivity.ViewHolder holder, int position) {
            holder.foodName.setText(foods[position].getName());
            holder.foodCarbo.setText(foods[position].getCarbo());
        }

        @Override
        public int getItemCount() {
            return foods.length;
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
