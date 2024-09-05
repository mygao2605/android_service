package com.tuna.huyservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HuyService extends Service {
    //  http://192.168.26.163:8081/huyservice
    private static final String TAG = "HuyService";
    Integer count = 0;
    String nameDevices="vmc01";
    Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Count " + count);
                count++;
                new fetchData().start();
                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(runnable, 10000);

        return START_REDELIVER_INTENT;
    }

    class fetchData extends Thread {
        @Override
        public void run() {
            try {
                URL url = new URL("http://192.168.26.163:8081/huyservice/"+nameDevices);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    Log.e(TAG, "Line " + line);
                }
            } catch (MalformedURLException e) {
//                Log.e(TAG, "Invalid URL", e);
            } catch (IOException e) {
//                Log.e(TAG, "Error fetching data", e);
            }
        }
    }
}
