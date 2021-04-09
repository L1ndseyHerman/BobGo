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

public class LevelSelectPageOneActivity extends AppCompatActivity
{

    private final Button[] buttons = new Button[5];
    private final TextView[] levelDescriptions = new TextView[4];
    private Button nextButton;
    private TextView scoreTexts[] = new TextView[4];
    //private int levelOneHighScore, levelTwoHighScore, levelThreeHighScore;
    private int scoreInts[] = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select_page_one);

        //  Ok, it should be saved, so time to test it:
        //SharedPreferences sharedPrefReturn = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int defaultValue = 0;
        scoreInts[0] = sharedPrefReturn.getInt("levelOneHighScore", defaultValue);
        scoreInts[1] = sharedPrefReturn.getInt("levelTwoHighScore", defaultValue);
        scoreInts[2] = sharedPrefReturn.getInt("levelThreeHighScore", defaultValue);
        scoreInts[3] = sharedPrefReturn.getInt("levelFourHighScore", defaultValue);

        //levelOneHighScore = sharedPrefReturn.getInt("levelOneHighScore", defaultValue);
        //levelTwoHighScore = sharedPrefReturn.getInt("levelTwoHighScore", defaultValue);
        //levelThreeHighScore = sharedPrefReturn.getInt("levelThreeHighScore", defaultValue);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        buttons[0] = findViewById(R.id.levelOneButton);
        buttons[1] = findViewById(R.id.levelTwoButton);
        buttons[2] = findViewById(R.id.levelThreeButton);
        buttons[3] = findViewById(R.id.levelFourButton);
        buttons[4] = findViewById(R.id.backButton3);

        levelDescriptions[0] = findViewById(R.id.levelOneDescription);
        levelDescriptions[1] = findViewById(R.id.levelTwoDescription);
        levelDescriptions[2] = findViewById(R.id.levelThreeDescription);
        levelDescriptions[3] = findViewById(R.id.levelFourDescription);
        //  This one is in the same column as the levelDescriptions, just a Button instead of a TextView.
        nextButton = findViewById(R.id.nextButton);

        scoreTexts[0] = findViewById(R.id.levelOneScore);
        scoreTexts[0].setText("High Score: " + scoreInts[0]);

        scoreTexts[1] = findViewById(R.id.levelTwoScore);
        scoreTexts[1].setText("High Score: " + scoreInts[1]);

        scoreTexts[2] = findViewById(R.id.levelThreeScore);
        scoreTexts[2].setText("High Score: " + scoreInts[2]);

        scoreTexts[3] = findViewById(R.id.levelFourScore);
        scoreTexts[3].setText("High Score: " + scoreInts[3]);

        for (int index=0; index<buttons.length; index++)
        {
            buttons[index].setX(screenWidth /36);
            buttons[index].setY((screenHeight /22) + (index*2* screenHeight /11));
            buttons[index].setWidth(screenWidth /6);
            buttons[index].setHeight(screenHeight /11);
        }

        for (int index=0; index<levelDescriptions.length; index++)
        {
            levelDescriptions[index].setX((screenWidth /36) + (screenWidth /6) + (screenWidth /12));
            levelDescriptions[index].setY((9* screenHeight /110) + (index*2* screenHeight /11));
            levelDescriptions[index].setWidth(4* screenWidth /9);
            levelDescriptions[index].setHeight(2* screenHeight /11);
        }

        //  Just plugin 4 bec it's like levelDescriptions[4].
        nextButton.setX((screenWidth /36) + (screenWidth /6) + (screenWidth /12));
        nextButton.setY((screenHeight /22) + (4*2* screenHeight /11));
        nextButton.setWidth(screenWidth /6);
        nextButton.setHeight(screenHeight /11);

        for (int index=0; index<scoreTexts.length; index++)
        {
            scoreTexts[index].setX((screenWidth /36) + (screenWidth /6) + (screenWidth /12) + (4* screenWidth /9) + (screenWidth /12));
            scoreTexts[index].setY((9* screenHeight /110) + (index*2* screenHeight /11));
            scoreTexts[index].setWidth(screenWidth /6);
            scoreTexts[index].setHeight(2* screenHeight /11);
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

        buttons[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelThreeActivity.class);
                startActivity(startIntent);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelFourActivity.class);
                startActivity(startIntent);
            }
        });

        //  Goes back to MainActivity:
        buttons[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageTwoActivity.class);
                startActivity(startIntent);
            }
        });

    }

}
