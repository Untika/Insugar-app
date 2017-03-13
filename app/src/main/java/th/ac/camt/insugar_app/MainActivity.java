package th.ac.camt.insugar_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView btnGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        byWidget();
        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToLogin = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(GoToLogin);
            }
        });
    }

    private void byWidget() {
        btnGoToRegister = (TextView) findViewById(R.id.login_btn_go_to_register);
    }
}
