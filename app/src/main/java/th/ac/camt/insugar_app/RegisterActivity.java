package th.ac.camt.insugar_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import th.ac.camt.insugar_app.Model.Check;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String URL;
    private EditText txtName;
    private EditText txtAge;
    private RadioGroup txtGender;
    private EditText txtPhone;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignUp;
    private String gender;
    private RadioButton radioSexButton;
    private TextView btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        byWidget();
        btnSignUp.setOnClickListener(this);
        btnBackToLogin.setOnClickListener(this);
    }

    private void byWidget() {
        txtName = (EditText) findViewById(R.id.regis_name);
        txtAge = (EditText) findViewById(R.id.regis_age);
        txtGender = (RadioGroup) findViewById(R.id.regis_gender_group);
        txtPhone = (EditText) findViewById(R.id.regis_phone);
        txtEmail = (EditText) findViewById(R.id.regis_email);
        txtPassword = (EditText) findViewById(R.id.regis_password);
        btnSignUp = (Button) findViewById(R.id.regis_btn_register);
        btnBackToLogin = (TextView) findViewById(R.id.regis_btn_back_to_login);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSignUp){
            int selectedId = txtGender.getCheckedRadioButtonId();
            radioSexButton = (RadioButton) findViewById(selectedId);
            if (radioSexButton.getText().equals("ชาย")) {
                gender = "male";
            } else {
                gender = "female";
            }

            if (txtName.getText().length() != 0 && txtAge.getText().length() != 0 &&
                    txtPhone.getText().length() != 0 && txtEmail.getText().length() != 0 &&
                    txtPassword.getText().length() != 0 ) {
Log.i("user", txtName.getText().toString()+ txtAge.getText().toString()+ gender +
        txtPhone.getText().toString()+ txtEmail.getText().toString()+ txtPassword.getText().toString());

                new UserRegistrationTask().execute(txtName.getText().toString(),txtAge.getText().toString(),gender,
                        txtPhone.getText().toString(),txtEmail.getText().toString(),txtPassword.getText().toString());
            }else{
                // Toast.makeText("sfasfas");
            }
        }else if(v ==btnBackToLogin){
            finish();
        }
    }

    private class UserRegistrationTask extends AsyncTask<String, Void, Check[]> {

        @Override
        protected Check[] doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("name", params[0])
                        .add("age", params[1])
                        .add("gender", params[2])
                        .add("phone", params[3])
                        .add("email", params[4])
                        .add("password", params[5])
                        .build();

                Request request = new Request.Builder()
                        .url(URL)
                        .post(data)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type listType = new TypeToken<ArrayList<Check>>() {
                }.getType();
                Collection<Check> enums = gson.fromJson(result, listType);
                Check[] checks = enums.toArray(new Check[enums.size()]);
                return checks;

            } catch (Exception e) {
                //Log.i("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Check[] result) {
            super.onPostExecute(result);

            if (result[0].getCheck().equals("Done Insert")) {
                Toast.makeText(getApplicationContext(), "เรียบร้อย", Toast.LENGTH_LONG).show();
                finish();
            } else if (result[0].getCheck().equals("email not unique")) {
                txtEmail.setError(" e-mail นี้มีอยุ่ในระบบแล้ว");
            } else {
                Toast.makeText(getApplicationContext(), "ไม่สามารถลงทะเบียนได้ ลองใหม่อีกครั้ง", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }
}
