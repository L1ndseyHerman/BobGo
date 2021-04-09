package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private int screenHeight;
    private int startHeight;
    private int pauseCounter=0;
    private final Button[] buttons = new Button[3];
    private ImageView bobImageMainScreen;
    private final Handler handler = new Handler();
    private final Timer timer = new Timer();
    private boolean isJumping = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        screenHeight = size.y;

        TextView bobText = findViewById(R.id.bobText);
        TextView goText = findViewById(R.id.goText);

        bobText.setX(7* screenWidth /24);
        bobText.setY(screenHeight/12);
        bobText.setWidth(screenWidth /3);
        bobText.setHeight(screenHeight/4);

        goText.setX(15* screenWidth /24);
        goText.setY(screenHeight/12);
        goText.setWidth(screenWidth /3);
        goText.setHeight(screenHeight/4);

        bobImageMainScreen = findViewById(R.id.bobMainScreen);

        bobImageMainScreen.setX(screenWidth /12);
        startHeight = 5*screenHeight/14;
        bobImageMainScreen.setY(startHeight);
        bobImageMainScreen.getLayoutParams().width = screenWidth /12;
        bobImageMainScreen.getLayoutParams().height = screenHeight/7;

        buttons[0] = findViewById(R.id.levelButton);
        buttons[1] = findViewById(R.id.randomButton);
        buttons[2] = findViewById(R.id.testingButton);

        for (int index=0; index<buttons.length; index++)
        {
            buttons[index].setX((screenWidth /12) + (index* screenWidth /3));
            buttons[index].setY(2*screenHeight/3);
            buttons[index].setWidth(screenWidth /6);
            buttons[index].setHeight(screenHeight/4);
        }


        //  The code below makes it go to the Game Activity when the beginButton is pressed:
        buttons[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //  This is a variable of type "Intent"... idk....
                //                                                      Tells it to go to that screen
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageOneActivity.class);
                //  Passing stuff betw screens:
                //startIntent.putExtra("com.lherman.quicklauncher.SOMETHING", "HELLO WORLD!");
                //  Going to that screen now:
                startActivity(startIntent);
            }
        });

        //  Goes to RandomActivity:
        buttons[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), RandomActivity.class);
                startActivity(startIntent);
            }
        });

        //  Goes to TestingActivity:
        buttons[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), TestingActivity.class);
                startActivity(startIntent);
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        infiniteBobAnimation();
                    }
                });
            }
        },0, 35);

    }


    public void infiniteBobAnimation()
    {
        if (isJumping)
        {
            if (bobImageMainScreen.getY()-(screenHeight/49) >= 0)
            {
                bobImageMainScreen.setY(bobImageMainScreen.getY()-(screenHeight/49));
            }
            else
            {
                isJumping = false;
            }
        }
        else
        {
            if (bobImageMainScreen.getY()+(screenHeight/49) <= startHeight)
            {
                bobImageMainScreen.setY(bobImageMainScreen.getY()+(screenHeight/49));
            }
            else
            {
                pauseCounter++;
                if (pauseCounter > 14)
                {
                    pauseCounter = 0;
                    isJumping = true;
                }
            }
        }
    }

}