package th.ac.camt.insugar_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SumFoodListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_food_list);

        TextView txtView = (TextView) findViewById(R.id.tvResult);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            String foodData = (String) bd.get("result");
            txtView.setText(foodData);
        } else {
            txtView.setText("No Foods");
        }

        initInstance();
    }

    private void initInstance() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listfood, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_item_add) {
            Intent intent = new Intent(SumFoodListActivity.this, FoodListActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.menu_item_success){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
