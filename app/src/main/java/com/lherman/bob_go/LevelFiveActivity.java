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

public class LevelFiveActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[20][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[10];
    private Coin[] coins = new Coin[2];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_five);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob5);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob5_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob5_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob5_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob5_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob5_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob5_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob5_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle5));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText5);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton5);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelFiveHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton5);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageTwoActivity.class);
                startActivity(startIntent);
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_3x3));
        daGrid[3][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_3x4), screenWidth, screenHeight);
        daGrid[3][5] = new SquareObstacle((ImageView) findViewById(R.id.grid5_3x5), screenWidth, screenHeight);

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_4x2));
        daGrid[4][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_4x3), screenWidth, screenHeight);
        daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_4x4), screenWidth, screenHeight);
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_8x3));
        daGrid[8][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_8x4), screenWidth, screenHeight);
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_10x4));
        daGrid[10][5] = new SquareObstacle((ImageView) findViewById(R.id.grid5_10x5), screenWidth, screenHeight);

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_11x2));
        daGrid[11][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_11x3), screenWidth, screenHeight);
        daGrid[11][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_11x4), screenWidth, screenHeight);
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_12x2));
        daGrid[12][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_12x3), screenWidth, screenHeight);
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_12x4));
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_13x2));
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_13x3), screenWidth, screenHeight);
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_16x1));
        daGrid[16][2] = new SquareObstacle((ImageView) findViewById(R.id.grid5_16x2), screenWidth, screenHeight);
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_16x4));
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid5_16x5), screenWidth, screenHeight);

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_18x2));
        daGrid[18][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_18x3), screenWidth, screenHeight);
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x2));
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_19x3), screenWidth, screenHeight);
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x4));
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin5_0));
        coins[0].setImageX(daGrid[8][5].getImageX());
        coins[0].setImageY(daGrid[8][5].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin5_1));
        coins[1].setImageX(daGrid[16][3].getImageX());
        coins[1].setImageY(daGrid[16][3].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[2];
        thePath0[0] = daGrid[6][0];
        thePath0[1] = daGrid[6][5];

        float[] xHaterMoveSpeeds0 = new float[2];
        xHaterMoveSpeeds0[0] = 0;
        xHaterMoveSpeeds0[1] = 0;

        float[] yHaterMoveSpeeds0 = new float[2];
        yHaterMoveSpeeds0[0] = (float)0.25;
        yHaterMoveSpeeds0[1] = (float)0.25;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater5_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[2];
        thePath1[0] = daGrid[4][5];
        thePath1[1] = daGrid[10][5];

        float[] xHaterMoveSpeeds1 = new float[2];
        xHaterMoveSpeeds1[0] = (float)1.25;
        xHaterMoveSpeeds1[1] = (float)1.25;

        float[] yHaterMoveSpeeds1 = new float[2];
        yHaterMoveSpeeds1[0] = 0;
        yHaterMoveSpeeds1[1] = 0;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater5_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[1];
        thePath2[0] = daGrid[11][5];

        float[] xHaterMoveSpeeds2 = new float[1];
        xHaterMoveSpeeds2[0] = 0;

        float[] yHaterMoveSpeeds2 = new float[1];
        yHaterMoveSpeeds2[0] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater5_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[1];
        thePath3[0] = daGrid[12][5];

        float[] xHaterMoveSpeeds3 = new float[1];
        xHaterMoveSpeeds3[0] = 0;

        float[] yHaterMoveSpeeds3 = new float[1];
        yHaterMoveSpeeds3[0] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater5_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[1];
        thePath4[0] = daGrid[13][5];

        float[] xHaterMoveSpeeds4 = new float[1];
        xHaterMoveSpeeds4[0] = 0;

        float[] yHaterMoveSpeeds4 = new float[1];
        yHaterMoveSpeeds4[0] = 0;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater5_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        GridImageThing[] thePath5 = new GridImageThing[1];
        thePath5[0] = daGrid[14][5];

        float[] xHaterMoveSpeeds5 = new float[1];
        xHaterMoveSpeeds5[0] = 0;

        float[] yHaterMoveSpeeds5 = new float[1];
        yHaterMoveSpeeds5[0] = 0;

        haters[5] = new Hater((ImageView) findViewById(R.id.hater5_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

        GridImageThing[] thePath6 = new GridImageThing[1];
        thePath6[0] = daGrid[15][5];

        float[] xHaterMoveSpeeds6 = new float[1];
        xHaterMoveSpeeds6[0] = 0;

        float[] yHaterMoveSpeeds6 = new float[1];
        yHaterMoveSpeeds6[0] = 0;

        haters[6] = new Hater((ImageView) findViewById(R.id.hater5_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, xHaterMoveSpeeds6, yHaterMoveSpeeds6);

        GridImageThing[] thePath7 = new GridImageThing[1];
        thePath7[0] = daGrid[17][5];

        float[] xHaterMoveSpeeds7 = new float[1];
        xHaterMoveSpeeds7[0] = 0;

        float[] yHaterMoveSpeeds7 = new float[1];
        yHaterMoveSpeeds7[0] = 0;

        haters[7] = new Hater((ImageView) findViewById(R.id.hater5_7));
        haters[7].setImageX(thePath7[0].getImageX());
        haters[7].setImageY(thePath7[0].getImageY());
        haters[7].setPath(thePath7, xHaterMoveSpeeds7, yHaterMoveSpeeds7);

        GridImageThing[] thePath8 = new GridImageThing[1];
        thePath8[0] = daGrid[18][5];

        float[] xHaterMoveSpeeds8 = new float[1];
        xHaterMoveSpeeds8[0] = 0;

        float[] yHaterMoveSpeeds8 = new float[1];
        yHaterMoveSpeeds8[0] = 0;

        haters[8] = new Hater((ImageView) findViewById(R.id.hater5_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, xHaterMoveSpeeds8, yHaterMoveSpeeds8);

        GridImageThing[] thePath9 = new GridImageThing[1];
        thePath9[0] = daGrid[19][5];

        float[] xHaterMoveSpeeds9 = new float[1];
        xHaterMoveSpeeds9[0] = 0;

        float[] yHaterMoveSpeeds9 = new float[1];
        yHaterMoveSpeeds9[0] = 0;

        haters[9] = new Hater((ImageView) findViewById(R.id.hater5_9));
        haters[9].setImageX(thePath9[0].getImageX());
        haters[9].setImageY(thePath9[0].getImageY());
        haters[9].setPath(thePath9, xHaterMoveSpeeds9, yHaterMoveSpeeds9);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}