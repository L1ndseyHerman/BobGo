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

public class LevelSevenActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[17][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[3];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;
    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[1];
    private ImageView[] powerUpBarsUpTop = new ImageView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_seven);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob7);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob7_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob7_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob7_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob7_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob7_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob7_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob7_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle7));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText7);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob7);

        powerUpBarsUpTop[0] = findViewById(R.id.brightenedUpTopSun7);
        powerUpBarsUpTop[1] = findViewById(R.id.brightenedUpTopBar7_0);
        powerUpBarsUpTop[2] = findViewById(R.id.brightenedUpTopBar7_1);
        powerUpBarsUpTop[3] = findViewById(R.id.brightenedUpTopBar7_2);
        powerUpBarsUpTop[4] = findViewById(R.id.brightenedUpTopBar7_3);
        powerUpBarsUpTop[5] = findViewById(R.id.brightenedUpTopBar7_4);
        powerUpBarsUpTop[6] = findViewById(R.id.brightenedUpTopBar7_5);
        powerUpBarsUpTop[7] = findViewById(R.id.brightenedUpTopBar7_6);
        powerUpBarsUpTop[8] = findViewById(R.id.brightenedUpTopBar7_7);
        powerUpBarsUpTop[9] = findViewById(R.id.brightenedUpTopBar7_8);
        powerUpBarsUpTop[10] = findViewById(R.id.brightenedUpTopBar7_9);
        powerUpBarsUpTop[11] = findViewById(R.id.brightenedUpTopBar7_10);

        gameLogic.setPowerUpLogic(powerUps, brightenedUpBobImage, powerUpBarsUpTop);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton7);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelSevenHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton7);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_4x4));
        daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_4x5), screenWidth, screenHeight);

        daGrid[5][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_5x0), screenWidth, screenHeight);
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x4));
        daGrid[5][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_5x5), screenWidth, screenHeight);

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_8x4));
        daGrid[8][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_8x5), screenWidth, screenHeight);

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_10x5));

        daGrid[11][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_11x0), screenWidth, screenHeight);
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x3));
        daGrid[11][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_11x4), screenWidth, screenHeight);
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x3));
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x4));
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x3));
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_15x4));
        daGrid[15][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_15x5), screenWidth, screenHeight);

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin7_0));
        coins[0].setImageX(daGrid[13][5].getImageX());
        coins[0].setImageY(daGrid[13][5].getImageY());

        return coins;
    }

    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp7_0));
        powerUps[0].setImageX(daGrid[0][0].getImageX());
        powerUps[0].setImageY(daGrid[0][0].getImageY());

        return powerUps;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[4];
        thePath0[0] = daGrid[5][0];
        thePath0[1] = daGrid[5][4];
        thePath0[2] = daGrid[5][5];
        thePath0[3] = daGrid[5][1];

        float[] xHaterMoveSpeeds0 = new float[4];
        xHaterMoveSpeeds0[0] = 0;
        xHaterMoveSpeeds0[1] = 0;
        xHaterMoveSpeeds0[2] = 0;
        xHaterMoveSpeeds0[3] = 0;

        float[] yHaterMoveSpeeds0 = new float[4];
        yHaterMoveSpeeds0[0] = 3;
        yHaterMoveSpeeds0[1] = (float)0.25;
        yHaterMoveSpeeds0[2] = 3;
        yHaterMoveSpeeds0[3] = (float)0.25;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater7_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[4];
        thePath1[0] = daGrid[8][5];
        thePath1[1] = daGrid[14][5];
        thePath1[2] = daGrid[15][5];
        thePath1[3] = daGrid[9][5];

        float[] xHaterMoveSpeeds1 = new float[4];
        xHaterMoveSpeeds1[0] = 3;
        xHaterMoveSpeeds1[1] = (float)0.25;
        xHaterMoveSpeeds1[2] = 3;
        xHaterMoveSpeeds1[3] = (float)0.25;

        float[] yHaterMoveSpeeds1 = new float[4];
        yHaterMoveSpeeds1[0] = 0;
        yHaterMoveSpeeds1[1] = 0;
        yHaterMoveSpeeds1[2] = 0;
        yHaterMoveSpeeds1[3] = 0;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater7_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[4];
        thePath2[0] = daGrid[11][0];
        thePath2[1] = daGrid[11][3];
        thePath2[2] = daGrid[11][4];
        thePath2[3] = daGrid[11][1];

        float[] xHaterMoveSpeeds2 = new float[4];
        xHaterMoveSpeeds2[0] = 0;
        xHaterMoveSpeeds2[1] = 0;
        xHaterMoveSpeeds2[2] = 0;
        xHaterMoveSpeeds2[3] = 0;

        float[] yHaterMoveSpeeds2 = new float[4];
        yHaterMoveSpeeds2[0] = 3;
        yHaterMoveSpeeds2[1] = (float)0.25;
        yHaterMoveSpeeds2[2] = 3;
        yHaterMoveSpeeds2[3] = (float)0.25;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater7_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}