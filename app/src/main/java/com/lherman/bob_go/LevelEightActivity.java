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

public class LevelEightActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[21][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[5];
    private Coin[] coins = new Coin[1];
    private Button beginButton;
    private GameLogic gameLogic;
    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[1];
    private ImageView[] powerUpBarsUpTop = new ImageView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_eight);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

        ImageView bobImage = findViewById(R.id.bob8);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob8_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob8_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob8_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob8_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob8_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob8_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob8_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle8));
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText8);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob8);

        powerUpBarsUpTop[0] = findViewById(R.id.brightenedUpTopSun8);
        powerUpBarsUpTop[1] = findViewById(R.id.brightenedUpTopBar8_0);
        powerUpBarsUpTop[2] = findViewById(R.id.brightenedUpTopBar8_1);
        powerUpBarsUpTop[3] = findViewById(R.id.brightenedUpTopBar8_2);
        powerUpBarsUpTop[4] = findViewById(R.id.brightenedUpTopBar8_3);
        powerUpBarsUpTop[5] = findViewById(R.id.brightenedUpTopBar8_4);
        powerUpBarsUpTop[6] = findViewById(R.id.brightenedUpTopBar8_5);
        powerUpBarsUpTop[7] = findViewById(R.id.brightenedUpTopBar8_6);
        powerUpBarsUpTop[8] = findViewById(R.id.brightenedUpTopBar8_7);
        powerUpBarsUpTop[9] = findViewById(R.id.brightenedUpTopBar8_8);
        powerUpBarsUpTop[10] = findViewById(R.id.brightenedUpTopBar8_9);
        powerUpBarsUpTop[11] = findViewById(R.id.brightenedUpTopBar8_10);

        gameLogic.setPowerUpLogic(powerUps, brightenedUpBobImage, powerUpBarsUpTop);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton8);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelEightHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton8);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_4x3));
        daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_4x4), screenWidth, screenHeight);
        daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_4x5), screenWidth, screenHeight);

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_5x1));
        daGrid[5][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x2), screenWidth, screenHeight);
        daGrid[5][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x3), screenWidth, screenHeight);
        daGrid[5][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x4), screenWidth, screenHeight);
        daGrid[5][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x5), screenWidth, screenHeight);

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x1));
        daGrid[7][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x2), screenWidth, screenHeight);
        daGrid[7][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x3), screenWidth, screenHeight);
        daGrid[7][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x4), screenWidth, screenHeight);
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_8x1));
        daGrid[8][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x2), screenWidth, screenHeight);
        daGrid[8][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x3), screenWidth, screenHeight);
        daGrid[8][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x4), screenWidth, screenHeight);
        daGrid[8][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x5), screenWidth, screenHeight);

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x3));
        daGrid[9][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_9x4), screenWidth, screenHeight);
        daGrid[9][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_9x5), screenWidth, screenHeight);

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x3));
        daGrid[10][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_10x4), screenWidth, screenHeight);
        daGrid[10][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_10x5), screenWidth, screenHeight);

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x3));
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_12x4), screenWidth, screenHeight);
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_13x1));
        daGrid[13][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x2), screenWidth, screenHeight);
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x3), screenWidth, screenHeight);
        daGrid[13][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x4), screenWidth, screenHeight);
        daGrid[13][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x5), screenWidth, screenHeight);

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x3));
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_16x4), screenWidth, screenHeight);
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x1));
        daGrid[17][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x2), screenWidth, screenHeight);
        daGrid[17][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x3), screenWidth, screenHeight);
        daGrid[17][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x4), screenWidth, screenHeight);
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_19x1));
        daGrid[19][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x2), screenWidth, screenHeight);
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x3), screenWidth, screenHeight);
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x4), screenWidth, screenHeight);
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x5), screenWidth, screenHeight);

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x1));
        daGrid[20][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_20x2), screenWidth, screenHeight);
        daGrid[20][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_20x3), screenWidth, screenHeight);
        daGrid[20][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_20x4), screenWidth, screenHeight);
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_20x5), screenWidth, screenHeight);

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin8_0));
        coins[0].setImageX(daGrid[4][3].getImageX());
        coins[0].setImageY(daGrid[4][3].getImageY());

        return coins;
    }

    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp8_0));
        powerUps[0].setImageX(daGrid[0][0].getImageX());
        powerUps[0].setImageY(daGrid[0][0].getImageY());

        return powerUps;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[4];
        thePath0[0] = daGrid[7][5];
        thePath0[1] = daGrid[6][5];
        thePath0[2] = daGrid[6][0];
        thePath0[3] = daGrid[6][5];

        float[] xHaterMoveSpeeds0 = new float[4];
        xHaterMoveSpeeds0[0] = (float)0.25;
        xHaterMoveSpeeds0[1] = 0;
        xHaterMoveSpeeds0[2] = 0;
        xHaterMoveSpeeds0[3] = (float)0.25;

        float[] yHaterMoveSpeeds0 = new float[4];
        yHaterMoveSpeeds0[0] = 0;
        yHaterMoveSpeeds0[1] = 6;
        yHaterMoveSpeeds0[2] = 6;
        yHaterMoveSpeeds0[3] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater8_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[4];
        thePath1[0] = daGrid[11][5];
        thePath1[1] = daGrid[11][0];
        thePath1[2] = daGrid[11][5];
        thePath1[3] = daGrid[12][5];

        float[] xHaterMoveSpeeds1 = new float[4];
        xHaterMoveSpeeds1[0] = 0;
        xHaterMoveSpeeds1[1] = 0;
        xHaterMoveSpeeds1[2] = (float)0.25;
        xHaterMoveSpeeds1[3] = (float)0.25;

        float[] yHaterMoveSpeeds1 = new float[4];
        yHaterMoveSpeeds1[0] = 6;
        yHaterMoveSpeeds1[1] = 6;
        yHaterMoveSpeeds1[2] = 0;
        yHaterMoveSpeeds1[3] = 0;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater8_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[16];
        thePath2[0] = daGrid[14][5];
        thePath2[1] = daGrid[14][0];
        thePath2[2] = daGrid[14][5];
        thePath2[3] = daGrid[15][5];
        thePath2[4] = daGrid[15][0];
        thePath2[5] = daGrid[15][5];
        thePath2[6] = daGrid[16][5];
        thePath2[7] = daGrid[17][5];
        thePath2[8] = daGrid[18][5];
        thePath2[9] = daGrid[18][0];
        thePath2[10] = daGrid[18][5];
        thePath2[11] = daGrid[17][5];
        thePath2[12] = daGrid[16][5];
        thePath2[13] = daGrid[15][5];
        thePath2[14] = daGrid[15][0];
        thePath2[15] = daGrid[15][5];

        float[] xHaterMoveSpeeds2 = new float[16];
        xHaterMoveSpeeds2[0] = 0;
        xHaterMoveSpeeds2[1] = 0;
        xHaterMoveSpeeds2[2] = (float)0.25;
        xHaterMoveSpeeds2[3] = 0;
        xHaterMoveSpeeds2[4] = 0;
        xHaterMoveSpeeds2[5] = (float)0.25;
        xHaterMoveSpeeds2[6] = (float)0.25;
        xHaterMoveSpeeds2[7] = (float)0.25;
        xHaterMoveSpeeds2[8] = 0;
        xHaterMoveSpeeds2[9] = 0;
        xHaterMoveSpeeds2[10] = (float)0.25;
        xHaterMoveSpeeds2[11] = (float)0.25;
        xHaterMoveSpeeds2[12] = (float)0.25;
        xHaterMoveSpeeds2[13] = 0;
        xHaterMoveSpeeds2[14] = 0;
        xHaterMoveSpeeds2[15] = (float)0.25;

        float[] yHaterMoveSpeeds2 = new float[16];
        yHaterMoveSpeeds2[0] = 6;
        yHaterMoveSpeeds2[1] = 6;
        yHaterMoveSpeeds2[2] = 0;
        yHaterMoveSpeeds2[3] = 6;
        yHaterMoveSpeeds2[4] = 6;
        yHaterMoveSpeeds2[5] = 0;
        yHaterMoveSpeeds2[6] = 0;
        yHaterMoveSpeeds2[7] = 0;
        yHaterMoveSpeeds2[8] = 6;
        yHaterMoveSpeeds2[9] = 6;
        yHaterMoveSpeeds2[10] = 0;
        yHaterMoveSpeeds2[11] = 0;
        yHaterMoveSpeeds2[12] = 0;
        yHaterMoveSpeeds2[13] = 6;
        yHaterMoveSpeeds2[14] = 6;
        yHaterMoveSpeeds2[15] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater8_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[16];
        thePath3[0] = daGrid[15][5];
        thePath3[1] = daGrid[15][0];
        thePath3[2] = daGrid[15][5];
        thePath3[3] = daGrid[16][5];
        thePath3[4] = daGrid[17][5];
        thePath3[5] = daGrid[18][5];
        thePath3[6] = daGrid[18][0];
        thePath3[7] = daGrid[18][5];
        thePath3[8] = daGrid[17][5];
        thePath3[9] = daGrid[16][5];
        thePath3[10] = daGrid[15][5];
        thePath3[11] = daGrid[15][0];
        thePath3[12] = daGrid[15][5];
        thePath3[13] = daGrid[14][5];
        thePath3[14] = daGrid[14][0];
        thePath3[15] = daGrid[14][5];

        float[] xHaterMoveSpeeds3 = new float[16];
        xHaterMoveSpeeds3[0] = 0;
        xHaterMoveSpeeds3[1] = 0;
        xHaterMoveSpeeds3[2] = (float)0.25;
        xHaterMoveSpeeds3[3] = (float)0.25;
        xHaterMoveSpeeds3[4] = (float)0.25;
        xHaterMoveSpeeds3[5] = 0;
        xHaterMoveSpeeds3[6] = 0;
        xHaterMoveSpeeds3[7] = (float)0.25;
        xHaterMoveSpeeds3[8] = (float)0.25;
        xHaterMoveSpeeds3[9] = (float)0.25;
        xHaterMoveSpeeds3[10] = 0;
        xHaterMoveSpeeds3[11] = 0;
        xHaterMoveSpeeds3[12] = (float)0.25;
        xHaterMoveSpeeds3[13] = 0;
        xHaterMoveSpeeds3[14] = 0;
        xHaterMoveSpeeds3[15] = (float)0.25;

        float[] yHaterMoveSpeeds3 = new float[16];
        yHaterMoveSpeeds3[0] = 6;
        yHaterMoveSpeeds3[1] = 6;
        yHaterMoveSpeeds3[2] = 0;
        yHaterMoveSpeeds3[3] = 0;
        yHaterMoveSpeeds3[4] = 0;
        yHaterMoveSpeeds3[5] = 6;
        yHaterMoveSpeeds3[6] = 6;
        yHaterMoveSpeeds3[7] = 0;
        yHaterMoveSpeeds3[8] = 0;
        yHaterMoveSpeeds3[9] = 0;
        yHaterMoveSpeeds3[10] = 6;
        yHaterMoveSpeeds3[11] = 6;
        yHaterMoveSpeeds3[12] = 0;
        yHaterMoveSpeeds3[13] = 6;
        yHaterMoveSpeeds3[14] = 6;
        yHaterMoveSpeeds3[15] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater8_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[16];
        thePath4[0] = daGrid[18][5];
        thePath4[1] = daGrid[18][0];
        thePath4[2] = daGrid[18][5];
        thePath4[3] = daGrid[17][5];
        thePath4[4] = daGrid[16][5];
        thePath4[5] = daGrid[15][5];
        thePath4[6] = daGrid[15][0];
        thePath4[7] = daGrid[15][5];
        thePath4[8] = daGrid[14][5];
        thePath4[9] = daGrid[14][0];
        thePath4[10] = daGrid[14][5];
        thePath4[11] = daGrid[15][5];
        thePath4[12] = daGrid[15][0];
        thePath4[13] = daGrid[15][5];
        thePath4[14] = daGrid[16][5];
        thePath4[15] = daGrid[17][5];

        float[] xHaterMoveSpeeds4 = new float[16];
        xHaterMoveSpeeds4[0] = 0;
        xHaterMoveSpeeds4[1] = 0;
        xHaterMoveSpeeds4[2] = (float)0.25;
        xHaterMoveSpeeds4[3] = (float)0.25;
        xHaterMoveSpeeds4[4] = (float)0.25;
        xHaterMoveSpeeds4[5] = 0;
        xHaterMoveSpeeds4[6] = 0;
        xHaterMoveSpeeds4[7] = (float)0.25;
        xHaterMoveSpeeds4[8] = 0;
        xHaterMoveSpeeds4[9] = 0;
        xHaterMoveSpeeds4[10] = (float)0.25;
        xHaterMoveSpeeds4[11] = 0;
        xHaterMoveSpeeds4[12] = 0;
        xHaterMoveSpeeds4[13] = (float)0.25;
        xHaterMoveSpeeds4[14] = (float)0.25;
        xHaterMoveSpeeds4[15] = (float)0.25;

        float[] yHaterMoveSpeeds4 = new float[16];
        yHaterMoveSpeeds4[0] = 6;
        yHaterMoveSpeeds4[1] = 6;
        yHaterMoveSpeeds4[2] = 0;
        yHaterMoveSpeeds4[3] = 0;
        yHaterMoveSpeeds4[4] = 0;
        yHaterMoveSpeeds4[5] = 6;
        yHaterMoveSpeeds4[6] = 6;
        yHaterMoveSpeeds4[7] = 0;
        yHaterMoveSpeeds4[8] = 6;
        yHaterMoveSpeeds4[9] = 6;
        yHaterMoveSpeeds4[10] = 0;
        yHaterMoveSpeeds4[11] = 6;
        yHaterMoveSpeeds4[12] = 6;
        yHaterMoveSpeeds4[13] = 0;
        yHaterMoveSpeeds4[14] = 0;
        yHaterMoveSpeeds4[15] = 0;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater8_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}