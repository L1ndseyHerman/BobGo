package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
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
    private boolean fallingNow = false;
    private int height = 0;
    private int width = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_testing);

        //  Should hide border at top:
        getSupportActionBar().hide();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        bobY = 5*height/7;
        //bobY = 70*6;
        //System.out.println(bobY);

        bobSeventy = findViewById(R.id.babybob);
        bobSeventy.setX(bobX);
        bobSeventy.setY(bobY);
        bobSeventy.getLayoutParams().height = height/7;
        bobSeventy.getLayoutParams().width = width/12;

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
        //bobX = bobX + 7;
        //  14 timer calls per one grid square crossing, 12*14=166
        bobX = bobX + width/166;
        bobSeventy.setX(bobX);
        //  BTS Idol :)
        //System.out.println("Woo hoo!");
        if (jumpingNow == true)
        {
            //bobY = bobY - 7;
            //  Same proportion for y-direction, 7*14=98
            bobY = bobY - height/98;
            bobSeventy.setY(bobY);
            //if (bobY > 151)
            if (bobY < 3*height/7)
            {
                jumpingNow = false;
                fallingNow = true;
            }
        }
        if (fallingNow == true)
        {
            //bobY = bobY + 7;
            //  Same proportion for y-direction, 7*14=98
            bobY = bobY + height/98;
            bobSeventy.setY(bobY);
            if (bobY > 5*height/7)
            {
                fallingNow = false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        System.out.println("BobY = " + bobY + " Bob Height = " + height);

        //if (bobY > 151)
        if (jumpingNow==false && fallingNow==false)
        {
            jumpingNow = true;
        }
        //  Below just has to be there for some reason:
        return true;
    }

}