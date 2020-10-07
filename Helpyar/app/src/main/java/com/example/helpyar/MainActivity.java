package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //code used to show splash screen!

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                   Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                   startActivity(intent);
                    finish();

                }
            }
        };
        thread.start();
    }
}
