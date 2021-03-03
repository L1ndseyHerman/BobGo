package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private int screenWidth, screenHeight;
    private TextView bobText, goText;
    private Button buttons[] = new Button[3];
    private ImageView bobMainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        bobText = findViewById(R.id.bobText);
        goText = findViewById(R.id.goText);

        bobText.setX(7*screenWidth/24);
        bobText.setY(screenHeight/12);
        bobText.setWidth(screenWidth/3);
        bobText.setHeight(screenHeight/4);

        goText.setX(15*screenWidth/24);
        goText.setY(screenHeight/12);
        goText.setWidth(screenWidth/3);
        goText.setHeight(screenHeight/4);

        bobMainScreen = findViewById(R.id.bobMainScreen);

        bobMainScreen.setX(screenWidth/12);
        bobMainScreen.setY(5*screenHeight/12);
        bobMainScreen.getLayoutParams().width = screenWidth/12;
        bobMainScreen.getLayoutParams().height = screenHeight/7;

        buttons[0] = findViewById(R.id.levelButton);
        buttons[1] = findViewById(R.id.randomButton);
        buttons[2] = findViewById(R.id.testingButton);

        for (int index=0; index<buttons.length; index++)
        {
            buttons[index].setX((screenWidth/12) + (index*screenWidth/3));
            buttons[index].setY(2*screenHeight/3);
            buttons[index].setWidth(screenWidth/6);
            buttons[index].setHeight(screenHeight/4);

            /*buttons[index].setX(screenWidth/36);
            buttons[index].setY((screenHeight/22) + (index*2*screenHeight/11));
            buttons[index].setWidth(screenWidth/6);
            buttons[index].setHeight(screenHeight/11);*/
        }


        //  The code below makes it go to the Game Activity when the beginButton is pressed:
        buttons[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //  This is a variable of type "Intent"... idk....
                //                                                      Tells it to go to that screen
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
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

    }
}