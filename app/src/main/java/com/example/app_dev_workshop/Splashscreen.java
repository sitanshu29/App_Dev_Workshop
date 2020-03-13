package com.example.app_dev_workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        image=(ImageView)findViewById(R.id.nitwlogo);
        Animation bounce_interpolator = AnimationUtils.loadAnimation(this,R.anim.zoom_in);

        image.startAnimation(bounce_interpolator);
        Thread SplashThread = new Thread()
        {
            public void run(){
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(Splashscreen.this, MainActivity.class));
                    //  startActivity(k);
                }
            }

        };
        SplashThread.start();
    }
}
