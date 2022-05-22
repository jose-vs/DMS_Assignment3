package com.example.blogmobileapp_app.utils;

import static com.example.blogmobileapp_app.data.Variables.API_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginUser extends AsyncTask<String, Void, Integer> {

    private boolean loggedIn;


    @SuppressLint("StaticFieldLeak")
    private Context context;

    public LoginUser(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Integer doInBackground(String... strings) {

        String username = strings[0];
        int responseCode = 0;

        try {
            URL url = new URL(API_URL + "/users/"+username);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Log.d("Bruh", strings[0]+".."+url);
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                Log.d("line", line);
                sb.append(line);
            }

            JSONObject json = new JSONObject(sb.toString());

            bufferedReader.close();
            inputStream.close();

            responseCode = conn.getResponseCode();

            conn.disconnect();

            if (json != null) {
                Log.d("some message", strings[0]+":"+strings[1]);
                try {
                    JSONArray jsonArray = (JSONArray) json.get("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        String password = (String) jsonObject.get("password");
                        if (password.equals(strings[1])) {
                            Log.d("Password is ", "correct");
                            loggedIn = true;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseCode;

    }

//    protected void onPostExecute(Integer responseCode) {
//        String msg;
//        Log.d("some login", "this.success" + success );
//        if ((responseCode >= 200) && (responseCode <= 299) && success) {
//            msg = "Login was successful";
//            Intent myIntent = new Intent(mContext, MainActivity.class);
//            myIntent.putExtra("username", username);
//            ActivityCompat.finishAffinity((Activity) mContext);
//            mContext.startActivity(myIntent);
//        } else {
//            msg = "Login failed";
//        }
//        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//    }

}
