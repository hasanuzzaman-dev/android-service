package com.hasan.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hasan.androidservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startServiceBtn.setOnClickListener(view -> {
            startService(new Intent(this,MyService.class));
        });

        binding.stopServiceBtn.setOnClickListener(view -> {
            stopService(new Intent(this, MyService.class));
        });


    }
}