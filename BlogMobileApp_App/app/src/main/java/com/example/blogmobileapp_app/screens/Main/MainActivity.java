package com.example.blogmobileapp_app.screens.Main;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.blogmobileapp_app.R;
import com.example.blogmobileapp_app.components.BlogViewFragment;

public class MainActivity extends AppCompatActivity  {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BlogViewFragment fragment = new BlogViewFragment();
            transaction.replace(R.id.testFragment, fragment);
            transaction.commit();
        }

        String username;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                username = extras.getString("username");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("username");
        }
    }
}
