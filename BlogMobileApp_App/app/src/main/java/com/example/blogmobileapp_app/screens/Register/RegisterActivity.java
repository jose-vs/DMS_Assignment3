package com.example.blogmobileapp_app.screens.Register;

import static com.example.blogmobileapp_app.data.Variables.API_URL;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blogmobileapp_app.R;
import com.example.blogmobileapp_app.api.LoginApiService;
import com.example.blogmobileapp_app.api.RegisterApiService;

public class RegisterActivity extends AppCompatActivity {

    private Button btnSignUp;
    private EditText edUsername, edName, edEmail, edPassword, edCpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = findViewById(R.id.btnSignUp);
        edUsername = findViewById(R.id.edUsername);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edCpassword = findViewById(R.id.edCPassword);

        btnSignUp.setOnClickListener(view -> {
            if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edUsername.getText().toString())
                    || TextUtils.isEmpty(edPassword.getText().toString()) ||
                    TextUtils.isEmpty(edCpassword.getText().toString())){
                String message = "All inputs required ..";
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
            }else {
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setEmail(edEmail.getText().toString());
                registerRequest.setPassword(edPassword.getText().toString());
                registerRequest.setUsername(edUsername.getText().toString());
                registerRequest.setName(edName.getText().toString());
                registerUser(registerRequest);
            }
        });

    }

    public void registerUser(RegisterRequest registerRequest) {
        RegisterApiService task = new RegisterApiService(registerRequest, this);
        task.execute(API_URL + "users");
    }
}