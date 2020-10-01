package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTesting extends AppCompatActivity
{
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private float bobX = 0;
    private float bobY = 0;
    private ImageView bobSeventy;
    private boolean jumpingNow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_testing);

        //  Should hide border at top:
        getSupportActionBar().hide();

        bobSeventy = findViewById(R.id.babybob);
        bobSeventy.setX(bobX);
        bobSeventy.setY(bobY);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        methodName();
                    }
                });
            }
        //},0, 20);
        },0, 35);
    }

    public void methodName()
    {
        bobX = bobX + 7;
        bobSeventy.setX(bobX);
        //  BTS Idol :)
        //System.out.println("Woo hoo!");
        if (jumpingNow == true)
        {
            bobY = bobY + 7;
            bobSeventy.setY(bobY);
            if (bobY > 151)
            {
                jumpingNow = false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        System.out.println("A tap!");

        if (bobY > 151)
        {
            jumpingNow = false;
        }
        else
        {
            jumpingNow = true;
        }
        //  Below just has to be there for some reason:
        return true;
    }

}