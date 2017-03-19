package th.ac.camt.insugar_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnGoToRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        byWidget();

        btnGoToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void byWidget() {
        btnGoToRegister = (TextView) findViewById(R.id.login_btn_go_to_register);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGoToRegister){
            Intent goToRegis = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(goToRegis);
        } else if(v == btnLogin){
            Intent goToMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(goToMenu);
        }
    }
}
