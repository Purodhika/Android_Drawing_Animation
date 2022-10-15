package com.example.purodhikasharma_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exercise02 extends AppCompatActivity {
    AnimationDrawable frameAnimation = null;

    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise02);

        // Handle Start Button
        startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Handle Stop Button
        stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private void startAnimation()
    {
        //
        // get the pointer for the image view
        ImageView img = (ImageView)findViewById(R.id.gifImageView);

        // setup the image that we will send in the animation
        BitmapDrawable frame1 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.p1);
        BitmapDrawable frame2 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.p2);
        BitmapDrawable frame3 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.p3);
        BitmapDrawable frame4 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.p4);
        BitmapDrawable frame5= (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.p5);


        int reasonableDuration = 200;
        frameAnimation = new AnimationDrawable();
        frameAnimation.setOneShot(false);	// loop continuously
        frameAnimation.addFrame(frame1, reasonableDuration);
        frameAnimation.addFrame(frame2, reasonableDuration);
        frameAnimation.addFrame(frame3, reasonableDuration);
        frameAnimation.addFrame(frame4, reasonableDuration);
        frameAnimation.addFrame(frame5, reasonableDuration);


        img.setBackground(frameAnimation);

        frameAnimation.setVisible(true,true);
        frameAnimation.start();
    }
    private void stopAnimation()
    {
        frameAnimation.stop();
        frameAnimation.setVisible(false,false);
    }
}

