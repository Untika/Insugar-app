package th.ac.camt.insugar_app;

import android.app.Activity;
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

import th.ac.camt.insugar_app.Model.Food;

public class SumFoodListActivity extends AppCompatActivity {
    private List<Food> foodList;
    private RecyclerView listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_food_list);
        foodList = new ArrayList<Food>();
        listFood = (RecyclerView) findViewById(R.id.listview_sum_food);
        initInstance();
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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
        private List<Food> foods;

        public RecyclerViewAdapter(List<Food> foods) {
            this.foods = foods;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_foodlist, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.foodName.setText(foods.get(position).getName());
            holder.foodCarbo.setText(foods.get(position).getCarbo());
        }

        @Override
        public int getItemCount() {
            return foods.size();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String FoodName = data.getStringExtra("FoodName");
                String FoodChrbo = data.getStringExtra("FoodChrbo");
                foodList.add(new Food(FoodName,FoodChrbo));
                listFood.setAdapter(new RecyclerViewAdapter(foodList));
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
            startActivityForResult(intent, 1);
        } else if (item.getItemId() == R.id.menu_item_success){
            float sCorbo = 0;
            for (Food pair : foodList) {
                sCorbo += Float.parseFloat(pair.getCarbo());
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra("SumCarbo",sCorbo);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
