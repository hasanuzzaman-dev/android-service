package com.hasan.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {

    // Here is parent child relationship
    // MyLocalBinder extends Binder and Binder implements IBinder

    private IBinder iBinder = new MyLocalBinder();

    public MyBoundService() {
    }

    // যেহেতু MyBoundService এর কোনো অবজেক্ট create করা যায় না
    // তাই কোনো ক্লাস থেকে এর কোনো মেথড কল করা যায় না

    public class MyLocalBinder extends Binder {
        MyBoundService getMyBoundService() {
            return MyBoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
       return iBinder;
    }

    public int generateRandomNumber(){
        Random random = new Random();
        return 1 + random.nextInt(100);
    }
}