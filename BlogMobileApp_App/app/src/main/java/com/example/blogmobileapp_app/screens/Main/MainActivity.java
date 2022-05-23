package com.example.blogmobileapp_app.screens.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blogmobileapp_app.R;
import com.example.blogmobileapp_app.screens.Blog.BlogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Main" + (String) getIntent().getStringExtra("username"));

        Button button = (Button) this.findViewById(R.id.writeBlog);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BlogActivity.class);
            intent.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent);
        });
    }
}
