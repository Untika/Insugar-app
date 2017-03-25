package th.ac.camt.insugar_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResult;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        byWidGet();

        btnOk.setOnClickListener(this);
    }

    private void byWidGet() {
        txtResult = (TextView) findViewById(R.id.result_result);
        btnOk = (Button) findViewById(R.id.result_btn_ok);
    }

    @Override
    public void onClick(View v) {

    }
}
