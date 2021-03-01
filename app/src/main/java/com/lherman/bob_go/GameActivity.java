package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity
{

    private int screenWidth, screenHeight;
    private Button buttons[] = new Button[5];

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

        for (int index=0; index<buttons.length; index++)
        {
            /*daGrid[index][index2].setImageHeight(screenHeight/7);
            daGrid[index][index2].setImageWidth(screenWidth/12);
            daGrid[index][index2].setImageX(index*screenWidth/12);
            daGrid[index][index2].setImageY((screenHeight / 14) + (index2*screenHeight/7));*/

            buttons[index].setX(screenWidth/36);
            buttons[index].setY((screenHeight/22) + (index*2*screenHeight/11));
            buttons[index].setWidth(screenWidth/6);
            buttons[index].setHeight(screenHeight/11);
        }


        //  The code below makes it go to the Game Activity when the beginButton is pressed:
        //Button levelOneButton = findViewById(R.id.levelOneButton);

        //  Has the same stuff as an ImageView! X, Y, LayoutParams.w/h, etc!! :D

        //levelOneButton.setOnClickListener(new View.OnClickListener()
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

        //Button levelTwoButton = findViewById(R.id.levelTwoButton);
        //levelTwoButton.setOnClickListener(new View.OnClickListener()
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
        //Button backButton = findViewById(R.id.backButton3);
        //backButton.setOnClickListener(new View.OnClickListener()
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
