package com.example.blogmobileapp_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.blogmobileapp_app.Tasks.LoginUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameView;
    private EditText passwordView;
    private String username;
    public TextView userView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = getIntent().getStringExtra("username");
        String passedUsername = getIntent().getStringExtra("username_key");

        userView = (TextView) findViewById(R.id.userName);
        userView.setText(passedUsername);
        passwordView =  findViewById(R.id.password);

        Button mLoginButton = findViewById(R.id.button_first);
        mLoginButton.setOnClickListener(view -> attemptLogin());

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(myIntent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (username != null) {
            usernameView.setText(username);
        }
    }

    private void attemptLogin() {
        usernameView.setError(null);
        passwordView.setError(null);

        String username = usernameView.getText().toString().toLowerCase();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // validate info
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.field_required));
            focusView = passwordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.field_required));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            LoginUser loginUser = new LoginUser(this);
            loginUser.execute(username, password);
        }
    }
}
