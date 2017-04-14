package th.ac.camt.insugar_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import th.ac.camt.insugar_app.Model.User;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnLogout;
    GlobalClass global;
    private User user;
    private TextView txtNameAccount;
    private TextView txtEmailAccount;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        byWidget();
        user = global.getUser();
        txtNameAccount.setText(user.getFullName());
        txtEmailAccount.setText(user.getEmail());
        initInstance();
        btnLogout.setOnClickListener(this);
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        btnLogout = (Button) findViewById(R.id.account_btn_logout);
        txtNameAccount = (TextView) findViewById(R.id.account_name);
        txtEmailAccount = (TextView) findViewById(R.id.account_email);
    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(AccountActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("กรุณารอสักครู่...");
        progressDialog.show();
        logOut();
    }

    //sign out method
    public void logOut() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void initInstance() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
