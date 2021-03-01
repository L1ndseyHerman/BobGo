package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity
{

    private int screenWidth, screenHeight;
    private Button buttons[] = new Button[5];
    private TextView levelDescriptions[] = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        buttons[0] = findViewById(R.id.levelOneButton);
        buttons[1] = findViewById(R.id.levelTwoButton);
        buttons[2] = findViewById(R.id.levelThreeButton);
        buttons[3] = findViewById(R.id.levelFourButton);
        buttons[4] = findViewById(R.id.backButton3);

        levelDescriptions[0] = findViewById(R.id.levelOneDescription);
        levelDescriptions[1] = findViewById(R.id.levelTwoDescription);
        levelDescriptions[2] = findViewById(R.id.levelThreeDescription);
        levelDescriptions[3] = findViewById(R.id.levelFourDescription);

        for (int index=0; index<buttons.length; index++)
        {
            buttons[index].setX(screenWidth/36);
            buttons[index].setY((screenHeight/22) + (index*2*screenHeight/11));
            buttons[index].setWidth(screenWidth/6);
            buttons[index].setHeight(screenHeight/11);
        }

        for (int index=0; index<levelDescriptions.length; index++)
        {
            levelDescriptions[index].setX((screenWidth/36) + (screenWidth/6) + (screenWidth/12));
            levelDescriptions[index].setY((9*screenHeight/110) + (index*2*screenHeight/11));
            levelDescriptions[index].setWidth(4*screenWidth/9);
            levelDescriptions[index].setHeight(screenHeight/11);
        }


        //  The code below makes it go to the Game Activity when the beginButton is pressed:
        buttons[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //  This is a variable of type "Intent"... idk....
                //                                                      Tells it to go to that screen
                Intent startIntent = new Intent(getApplicationContext(), LevelOneActivity.class);
                //  Passing stuff betw screens:
                //startIntent.putExtra("com.lherman.quicklauncher.SOMETHING", "HELLO WORLD!");
                //  Going to that screen now:
                startActivity(startIntent);
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelTwoActivity.class);
                startActivity(startIntent);
            }
        });

        //  Goes back to MainActivity:
        buttons[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

    }

}
