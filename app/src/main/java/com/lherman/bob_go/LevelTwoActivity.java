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

public class LevelTwoActivity extends AppCompatActivity
{
    private GridImageThing[][] daGrid = new GridImageThing[19][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[2];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob2);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob2_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob2_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob2_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob2_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob2_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob2_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob2_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle2));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText2);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton2);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelTwoHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton2);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x3));
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x4));
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_12x5), screenWidth, screenHeight);

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x3));
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x4));
        daGrid[17][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_17x5), screenWidth, screenHeight);

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x3));
        daGrid[18][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_18x4), screenWidth, screenHeight);
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_18x5), screenWidth, screenHeight);

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin2_0));
        coins[0].setImageX(daGrid[0][1].getImageX());
        coins[0].setImageY(daGrid[0][1].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[2];
        thePath0[0] = daGrid[13][0];
        thePath0[1] = daGrid[13][5];

        float[] xHaterMoveSpeeds0 = new float[2];
        xHaterMoveSpeeds0[0] = 0;
        xHaterMoveSpeeds0[1] = 0;

        float[] yHaterMoveSpeeds0 = new float[2];
        yHaterMoveSpeeds0[0] = (float)0.5;
        yHaterMoveSpeeds0[1] = (float)0.5;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater2_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[2];
        thePath1[0] = daGrid[14][0];
        thePath1[1] = daGrid[14][5];

        float[] xHaterMoveSpeeds1 = new float[2];
        xHaterMoveSpeeds1[0] = 0;
        xHaterMoveSpeeds1[1] = 0;

        float[] yHaterMoveSpeeds1 = new float[2];
        yHaterMoveSpeeds1[0] = 1;
        yHaterMoveSpeeds1[1] = 1;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater2_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}