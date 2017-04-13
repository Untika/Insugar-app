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
import th.ac.camt.insugar_app.Model.Calculator;
import th.ac.camt.insugar_app.Model.User;

public class HistoryCalculatorActivity extends AppCompatActivity {

    GlobalClass global;
    private User user;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);
        byWidget();


        //Get all LongInsulin from service.
        new HistoryCalculatorActivity.CalculatorTask().execute(String.valueOf(user.getId()));
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        user = global.getUser();
        recyclerView = (RecyclerView)findViewById(R.id.listview_his_calculator);
    }

    private class CalculatorTask extends AsyncTask<String, Void, Calculator[]> {
        @Override
        protected Calculator[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("idUser", params[0])
                        .build();

                Request request = new Request.Builder()
                        .url(global.URL_GET_CALCULATOR_LIST)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Calculator>>() {
                }.getType();
                Collection<Calculator> enums = gson.fromJson(result, listType);
                Calculator[] calculator = enums.toArray(new Calculator[enums.size()]);
                return calculator;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Calculator[] calculator) {
            super.onPostExecute(calculator);

            recyclerView.setAdapter(new HistoryCalculatorActivity.RecyclerViewAdapter(calculator));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView calculatorDateTime;
        TextView calculatorTDD;
        TextView calculatorBloodSugar;
        TextView calculatorInsulinName;
        TextView calculatorFinalSumInsulinUnits;

        public ViewHolder(View itemView) {
            super(itemView);
            calculatorDateTime = (TextView)itemView.findViewById(R.id.item_calculator_dateTime);
            calculatorTDD = (TextView)itemView.findViewById(R.id.item_calculator_tdd);
            calculatorBloodSugar = (TextView)itemView.findViewById(R.id.item_calculator_bloodSugar);
            calculatorInsulinName = (TextView)itemView.findViewById(R.id.item_calculator_insulinName);
            calculatorFinalSumInsulinUnits = (TextView)itemView.findViewById(R.id.item_calculator_insulinUnit);
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<HistoryCalculatorActivity.ViewHolder>{
        private Calculator[] calculator;

        public RecyclerViewAdapter(Calculator[] calculator) {
            this.calculator = calculator;
        }

        @Override
        public HistoryCalculatorActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_calculator, parent, false);
            return new HistoryCalculatorActivity.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(HistoryCalculatorActivity.ViewHolder holder, int position) {
            holder.calculatorDateTime.setText(calculator[position].getDateTime());
            holder.calculatorTDD.setText(calculator[position].getTdd());
            holder.calculatorBloodSugar.setText(calculator[position].getBloodSugar());
            holder.calculatorInsulinName.setText(calculator[position].getInsulinName());
            holder.calculatorFinalSumInsulinUnits.setText(calculator[position].getFinalSumUnits());
        }

        @Override
        public int getItemCount() {
            return calculator.length;
        }
    }
}
