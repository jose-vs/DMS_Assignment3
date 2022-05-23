package com.example.blogmobileapp_app.screens.Blog;

import static com.example.blogmobileapp_app.data.Variables.API_URL;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blogmobileapp_app.R;
import com.example.blogmobileapp_app.api.LoginApiService;
import com.example.blogmobileapp_app.api.PostBlogApiService;
import com.example.blogmobileapp_app.data.model.BlogRequest;

public class BlogActivity extends AppCompatActivity  {

    private EditText titleView;
    private EditText contentView;

    String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        username = (String) getIntent().getStringExtra("username");

        System.out.println(username);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Blog");

        titleView = findViewById(R.id.title);
        contentView = findViewById(R.id.content);
        Button mSubmitBtn = findViewById(R.id.submitButton);
        mSubmitBtn.setOnClickListener(view -> {
            if (TextUtils.isEmpty((titleView.getText().toString())) || TextUtils.isEmpty(contentView.getText().toString())) {
                String message = "All inputs required ..";
                Toast.makeText(BlogActivity.this, message, Toast.LENGTH_LONG).show();
            } else {
                BlogRequest blogRequest = new BlogRequest();
                blogRequest.setTitle(titleView.getText().toString());
                blogRequest.setContent(contentView.getText().toString());
                blogRequest.setAuthor(username);

                submitBlog(blogRequest);
            }
        });

    }

    private void submitBlog(BlogRequest blogRequest) {
        PostBlogApiService task = new PostBlogApiService(blogRequest, this);
        task.execute(API_URL + "blogs/" );
    }
}
