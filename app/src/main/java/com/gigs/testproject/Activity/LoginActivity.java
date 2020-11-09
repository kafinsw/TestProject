package com.gigs.testproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gigs.testproject.API.RestClient;
import com.gigs.testproject.API.TinyDB;
import com.gigs.testproject.Model.ResponseLogin;
import com.gigs.testproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button logBtn;
    private TextView register, signIn;
    private CheckBox showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        logBtn = (Button) findViewById(R.id.logBtn);
        register = (TextView) findViewById(R.id.register);
        signIn = (TextView) findViewById(R.id.signIn);
        showPass = (CheckBox) findViewById(R.id.checkBox);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPass.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        logBtn.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    private void Login() {
        email.setError(null);
        password.setError(null);

        String user_email= email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_email))
        {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches())
        {
            email.setError("Email has been used");
            email.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(user_password))
        {
            password.setError("Password is required");
            password.requestFocus();
        }
        else {
            ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
            dialog.setTitle("Checking...");
            dialog.setMessage("Harap tunggu, kami sedang memeriksa Email Anda");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            RestClient.getService().login(user_password,user_email).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if(response.isSuccessful() && response.body() !=null){
                        TinyDB tinyDB = new TinyDB(LoginActivity.this);
                        assert response.body() != null;
                        tinyDB.putString("token", response.body().getData().getToken());

                        dialog.dismiss();
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    } else{
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logBtn:
//                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                Login();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
