package com.lherman.bob_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelThreeActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[12][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[1];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob3);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob3_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob3_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob3_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob3_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob3_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob3_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob3_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle3));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText3);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton3);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelThreeHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton3);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(startIntent);
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid3_11x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin3_0));
        coins[0].setImageX(daGrid[0][0].getImageX());
        coins[0].setImageY(daGrid[0][0].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[0][0];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater3_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}