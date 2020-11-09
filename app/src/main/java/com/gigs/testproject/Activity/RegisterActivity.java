package com.gigs.testproject.Activity;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gigs.testproject.API.RestClient;
import com.gigs.testproject.Model.ResponseRegister;
import com.gigs.testproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password;
    private Button regBtn;
    private CheckBox showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        regBtn = (Button) findViewById(R.id.regBtn);
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

        findViewById(R.id.regBtn).setOnClickListener(this);
    }

    private void Registration() {

        name.setError(null);
        email.setError(null);
        password.setError(null);

        String user_name = name.getText().toString().trim();
        String user_email= email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_name)) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(user_email))
        {
            email.setError("Email is required");
            name.requestFocus();
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
            return;
        }
        else if(password.length() <6 )
        {
            password.setError("Password Should be atleast 6 character long");
            password.requestFocus();
            return;
        } else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registering...");
            dialog.setMessage("Please wait we are adding your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            RestClient.getService().register(user_email,user_password,user_name).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if(response.isSuccessful() && response.body() !=null){
                        dialog.dismiss();
                        String message = response.message();

                        Intent intent = new Intent (RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, message+ "", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "cek kembali email dan password anda", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Log.d("asd","fail");
                    Toast.makeText(RegisterActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.regBtn) {
            Registration();
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}

