package th.ac.camt.insugar_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        byWidget();

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
                        .add("idUser", params[0])
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
        protected void onPostExecute(Food[] foods) {
            super.onPostExecute(foods);

        }
    }
}
