package th.ac.camt.insugar_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryCalculatorActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_calculator);

        listView = (ListView) findViewById(R.id.listview_his_calculator);
        listView.setAdapter(new EfficientAdapter(getApplicationContext()));
    }

    public class EfficientAdapter extends BaseAdapter {

        public Context mContext;
        public LayoutInflater mInflater;

        public EfficientAdapter(Context context){
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null){
                // load layout
                convertView = mInflater.inflate(R.layout.history_listview_calculator, null);
                holder = new ViewHolder();
                holder.tvTDD = (TextView) convertView.findViewById(R.id.his_listview_tDD);
                holder.tvDateTime = (TextView) convertView.findViewById(R.id.his_listview_dateTime);
                convertView.setTag(holder);
            } else {
                // re Widget
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvDateTime.setText("2017-04-09 15:00:29");
            holder.tvTDD.setText(String.valueOf(position) + "50");

            return convertView;
        }
    }
    public class ViewHolder{
        TextView tvDateTime;
        TextView tvTDD;
    }
}
