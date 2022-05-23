package com.example.blogmobileapp_app.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.blogmobileapp_app.data.model.BlogRequest;
import com.example.blogmobileapp_app.screens.Main.MainActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PostBlogApiService extends AsyncTask<String, Void, Integer> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private BlogRequest blogRequest;

    public PostBlogApiService(BlogRequest blogRequest, Context context) {
        this.blogRequest = blogRequest;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... params) {
        int responseCode = 0;
        try {
            String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(blogRequest.getTitle(), "UTF-8") + "&";
            data += URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(blogRequest.getContent(), "UTF-8") + "&";
            data += URLEncoder.encode("author", "UTF-8") + "=" + URLEncoder.encode(blogRequest.getAuthor(), "UTF-8");


            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Send the request to the server
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            responseCode = conn.getResponseCode();
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseCode;
    }

    protected void onPostExecute(Integer responseCode) {
        String msg;
        if ((responseCode >= 200) && (responseCode <= 299)) {
            msg = "Blog Posted";
            Intent myIntent = new Intent(context, MainActivity.class);
            ActivityCompat.finishAffinity((Activity) context);
            context.startActivity(myIntent);
        } else {
            msg = "Blog Post Failed";
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
