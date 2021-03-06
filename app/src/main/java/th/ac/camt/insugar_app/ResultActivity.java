package th.ac.camt.insugar_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResult;
    private Button btnOk;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        byWidGet();
        txtResult.setText(String.valueOf(global.finalSumUnits));
        btnOk.setOnClickListener(this);
    }

    private void byWidGet() {
        global = (GlobalClass) getApplicationContext();
        txtResult = (TextView) findViewById(R.id.result_result);
        btnOk = (Button) findViewById(R.id.result_btn_ok);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
