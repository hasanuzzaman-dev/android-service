package com.hasan.androidservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hasan.androidservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyBoundService myBoundService = null;
    private boolean isBound = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyLocalBinder binder = (MyBoundService.MyLocalBinder) iBinder;
            myBoundService = binder.getMyBoundService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Services
        binding.startServiceBtn.setOnClickListener(view -> {
            startService(new Intent(this, MyService.class));
        });

        binding.stopServiceBtn.setOnClickListener(view -> {
            stopService(new Intent(this, MyService.class));
        });

        // Intent Services
        binding.startMyIntentService.setOnClickListener(view -> {
            startService(new Intent(this, MyIntentService.class));
        });

        binding.showRandomNumberBtn.setOnClickListener(view -> {
            if (isBound){
                int random = myBoundService.generateRandomNumber();
                Toast.makeText(myBoundService, "random: "+random, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound){
            unbindService(connection);
        }
    }
}