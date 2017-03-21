package th.ac.camt.insugar_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //GlobalClass global =(GlobalClass)getApplicationContext();
    //private String URL = global.BASE_URL+"/Login.php";
    private TextView btnGoToRegister;
    private Button btnLogin;
    private EditText txtEmail;
    private EditText txtPassword;


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
        txtEmail = (EditText) findViewById(R.id.login_email);
        txtPassword = (EditText) findViewById(R.id.login_password);
    }

    @Override
    public void onClick(View v) {
        if (v == btnGoToRegister){
            Intent goToRegis = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(goToRegis);
        } else if(v == btnLogin){

            Intent goToMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(goToMenu);

            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            //new LoginTask().execute(email, password);
        }
    }
/*
    private class LoginTask extends AsyncTask<String, Void, User[]> {


        @Override
        protected User[] doInBackground(String... params) {

            User[] users = getLogin(params[0], params[1]);
            return users;
        }

        @Override
        protected void onPostExecute(User[] users) {
            super.onPostExecute(users);
            if (!(users[0].getEmail().isEmpty())) {
                //userPreferences("User", users[0]);
               // global.idUser = users[0].getId();
                //global.fullNameUser = users[0].getFullName();
               // global.birthDateUser = users[0].getBirthDate();
               // global.genderUser = users[0].getGender();
               // global.phoneUser = users[0].getPhone();
               // global.emailUser = users[0].getEmail();
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);

            } else {
                Toast.makeText(getApplicationContext(), "email or password incorrect", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void userPreferences(String key, User user) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, String.valueOf(user));
        editor.commit();
    }

    private User[] getLogin(String email, String password) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody data = new FormBody.Builder()
                    .add("email", email)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(URL)
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

            return users;

        } catch (Exception e) {
            //Log.i("Exception", e.toString());
        }
        return null;
    }

*/
}
