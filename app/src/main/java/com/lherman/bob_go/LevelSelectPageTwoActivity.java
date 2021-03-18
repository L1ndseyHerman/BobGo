package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelSelectPageTwoActivity extends AppCompatActivity
{

    private int screenWidth, screenHeight;
    private Button buttons[] = new Button[5];
    private TextView levelDescriptions[] = new TextView[4];
    private TextView scoreTexts[] = new TextView[4];
    private int scoreInts[] = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select_page_two);

        SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int defaultValue = 0;
        scoreInts[0] = sharedPrefReturn.getInt("levelFiveHighScore", defaultValue);
        scoreInts[1] = sharedPrefReturn.getInt("levelSixHighScore", defaultValue);
        scoreInts[2] = sharedPrefReturn.getInt("levelSevenHighScore", defaultValue);
        scoreInts[3] = sharedPrefReturn.getInt("levelEightHighScore", defaultValue);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        buttons[0] = findViewById(R.id.levelFiveButton);
        buttons[1] = findViewById(R.id.levelSixButton);
        buttons[2] = findViewById(R.id.levelSevenButton);
        buttons[3] = findViewById(R.id.levelEightButton);
        buttons[4] = findViewById(R.id.backButton4);

        levelDescriptions[0] = findViewById(R.id.levelFiveDescription);
        levelDescriptions[1] = findViewById(R.id.levelSixDescription);
        levelDescriptions[2] = findViewById(R.id.levelSevenDescription);
        levelDescriptions[3] = findViewById(R.id.levelEightDescription);

        scoreTexts[0] = findViewById(R.id.levelFiveScore);
        scoreTexts[0].setText("High Score: " + scoreInts[0]);

        scoreTexts[1] = findViewById(R.id.levelSixScore);
        scoreTexts[1].setText("High Score: " + scoreInts[1]);

        scoreTexts[2] = findViewById(R.id.levelSevenScore);
        scoreTexts[2].setText("High Score: " + scoreInts[2]);

        scoreTexts[3] = findViewById(R.id.levelEightScore);
        scoreTexts[3].setText("High Score: " + scoreInts[3]);

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
            levelDescriptions[index].setHeight(2*screenHeight/11);
        }

        for (int index=0; index<scoreTexts.length; index++)
        {
            scoreTexts[index].setX((screenWidth/36) + (screenWidth/6) + (screenWidth/12) + (4*screenWidth/9) + (screenWidth/12));
            scoreTexts[index].setY((9*screenHeight/110) + (index*2*screenHeight/11));
            scoreTexts[index].setWidth(screenWidth/6);
            scoreTexts[index].setHeight(2*screenHeight/11);
        }

        //  The back button:
        buttons[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageOneActivity.class);
                startActivity(startIntent);
            }
        });

    }
}