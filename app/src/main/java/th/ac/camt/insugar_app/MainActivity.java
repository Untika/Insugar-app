package th.ac.camt.insugar_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import th.ac.camt.insugar_app.Model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnGoToRegister;
    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;
    GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        byWidget();
        btnGoToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void byWidget() {
        global = (GlobalClass) getApplicationContext();
        btnGoToRegister = (TextView) findViewById(R.id.login_btn_go_to_register);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        txtEmail = (EditText) findViewById(R.id.login_email);
        txtPassword = (EditText) findViewById(R.id.login_password);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGoToRegister){
            Intent goToRegis = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(goToRegis);
        } else if(v == btnLogin){
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            new LoginTask().execute(email, password);
        }
    }

    private class LoginTask extends AsyncTask<String, Void, User[]> {

        @Override
        protected User[] doInBackground(String... params) {

            User[] users = getLogin(params[0], params[1]);
            return users;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(User[] users) {
            super.onPostExecute(users);
            if (users != null) {
                global.setUser(users[0]);
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "อีเมลหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
            }
        }
    }

    private User[] getLogin(String email, String password) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody data = new FormBody.Builder()
                    .add("email", email)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(global.URL_LOGIN)
                    .post(data)
                    .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();

            result = "[" + result + "]";
            Log.i("result", result);

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            Collection<User> enums = gson.fromJson(result, listType);
            User[] users = enums.toArray(new User[enums.size()]);

            if (users[0].getId()==0) {
                return null;
            }
            return users;

        } catch (Exception e) {
            //Log.i("Exception", e.toString());
        }
        return null;
    }

}
