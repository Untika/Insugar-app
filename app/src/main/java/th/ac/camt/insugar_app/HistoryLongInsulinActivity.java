package th.ac.camt.insugar_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import th.ac.camt.insugar_app.Model.LongInsulin;
import th.ac.camt.insugar_app.Model.User;

public class HistoryLongInsulinActivity extends AppCompatActivity {

    GlobalClass global;
    private User user;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_long_insulin);
        byWidget();


        //Get all LongInsulin from service.
        new LongInsulinTask().execute(String.valueOf(user.getId()));
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        user = global.getUser();
        recyclerView = (RecyclerView)findViewById(R.id.listview_his_longInsulin);
    }

    private class LongInsulinTask extends AsyncTask<String, Void, LongInsulin[]> {
        @Override
        protected LongInsulin[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("idUser", params[0])
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_GET_LONGINSULIN_LIST)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<LongInsulin>>() {
                }.getType();
                Collection<LongInsulin> enums = gson.fromJson(result, listType);
                LongInsulin[] longInsulins = enums.toArray(new LongInsulin[enums.size()]);
                return longInsulins;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(LongInsulin[] longInsulins) {
            super.onPostExecute(longInsulins);

            recyclerView.setAdapter(new RecyclerViewAdapter(longInsulins));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView longInsulinName;
        TextView longInsulinUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.item_longinsulin_date);
            longInsulinName = (TextView)itemView.findViewById(R.id.item_longinsulin_name);
            longInsulinUnit = (TextView)itemView.findViewById(R.id.item_longinsulin_unit);

        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
        private LongInsulin[] longInsulins;

        public RecyclerViewAdapter(LongInsulin[] longInsulins) {
            this.longInsulins = longInsulins;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_longinsulin, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.date.setText(longInsulins[position].getDate());
            holder.longInsulinName.setText(longInsulins[position].getInsulinName());
            holder.longInsulinUnit.setText(longInsulins[position].getUnit());
        }

        @Override
        public int getItemCount() {
            return longInsulins.length;
        }
    }
}
