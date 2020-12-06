package com.example.smartalarm.deviceAction;

import android.os.AsyncTask;
import android.util.Log;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

// Class implementation of strategy pattern
public class CoffeeMachine extends AsyncTask<String,String,String> {
    public CoffeeMachine(){
        setWater(0);
        setGround(0);
        setUrl("");
    }

    public CoffeeMachine(int water, int ground, String url) {
        setWater(water);
        setGround(ground);
        setUrl(url);
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d("COFFEEMACIHNE", url);
        Log.d("COFFEEMACIHNE", "water: " + Integer.toString(water));
        Log.d("COFFEEMACIHNE", "ground: " + Integer.toString(water));

        final FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder.add("water", Integer.toString(water));
        formBodyBuilder.add("ground", Integer.toString(ground));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        Log.d("Debug", str == null ? "" : str);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public void setWater(int water){
        this.water = water;
    }

    public int getWater(){
        return water;
    }

    public void setGround(int ground){
        this.ground = ground;
    }

    public int getGround(){
        return ground;
    }

    private String url;
    private int water;
    private int ground;
}
