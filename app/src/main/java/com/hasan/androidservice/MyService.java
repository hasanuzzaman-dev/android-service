package com.hasan.androidservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MediaPlayer mediaPlayer;
    private Handler handler;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service Started");

        //mediaPlayer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(MyService.this, "Service Started", Toast.LENGTH_SHORT).show();
                       }
                   });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
       // stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service Created");

        mediaPlayer = MediaPlayer.create(this,R.raw.sample);
        handler = new Handler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service Destroyed");
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}