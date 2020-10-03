package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class HighScoresActivity extends AppCompatActivity
{

    private Handler handler2 = new Handler();
    private Timer timer2 = new Timer();
    private float bobX2 = 0;
    private float bobY2 = 0;
    private ImageView bobHighScore;
    private boolean jumpingNow2 = false;
    private boolean fallingNow2 = false;
    private int height2 = 0;
    private int width2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //  Will this work on a tablet?
        //  Should hide border at top:
        getSupportActionBar().hide();

        //  Screen size stuff:
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width2 = size.x;
        height2 = size.y;
        bobY2 = 5*height2/7;

        bobHighScore = findViewById(R.id.bobHighScore);
        bobHighScore.setX(bobX2);
        bobHighScore.setY(bobY2);
        bobHighScore.getLayoutParams().height = height2/7;
        bobHighScore.getLayoutParams().width = width2/12;

        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        movementStuff();
                    }
                });
            }
            //},0, 40);
        },0, 80);
    }


    public void movementStuff()
    {
        //bobX2 = bobX2 + 7;

        //  6 timer calls per one grid square crossing:
        bobX2 = bobX2 + width2/72;
        bobHighScore.setX(bobX2);
        //  BTS Idol :)
        //System.out.println("Woo hoo!");
        if (jumpingNow2 == true)
        {
            //bobY2 = bobY2 - 7;
            //  Same proportion for y-direction, 7*6=42
            bobY2 = bobY2 - height2/42;
            bobHighScore.setY(bobY2);
            //if (bobY > 151)
            if (bobY2 < 3*height2/7)
            {
                jumpingNow2 = false;
                fallingNow2 = true;
            }
        }
        if (fallingNow2 == true)
        {
            //bobY2 = bobY2 + 7;
            bobY2 = bobY2 + height2/42;
            bobHighScore.setY(bobY2);
            if (bobY2 > 5*height2/7)
            {
                fallingNow2 = false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //System.out.println("BobY = " + bobY2 + " Bob Height = " + height2);

        //if (bobY > 151)
        if (jumpingNow2==false && fallingNow2==false)
        {
            jumpingNow2 = true;
        }


        TextView theText = findViewById(R.id.textViewTest);

        //theText.setText(theText.getText() + stringWidth + "," + stringHeight + "," + xString + "," + squareRight.getY() + "," + squareRight.getWidth() + "," + squareRight.getHeight());
        theText.setText("Some Text");

        //  Below just has to be there for some reason:
        return true;
    }




}