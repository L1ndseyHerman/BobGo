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

public class LevelSixActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[56][6];
    private Hater[] haters = new Hater[21];
    private Coin[] coins = new Coin[4];
    private Button beginButton;
    private GameLogic gameLogic;

    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[3];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_six);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bob6);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob6_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob6_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob6_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob6_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob6_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob6_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob6_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle6));
        gameLogic.setWinCircleLogic(winCircle, daGrid[54][3]);

        TextView scoreText = findViewById(R.id.scoreText6);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);


        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob6);

        ImageView[] powerUpBarsUpTop = new ImageView[12];
        //  Sun is included, bars move down by 1:
        powerUpBarsUpTop[0] = findViewById(R.id.brightenedUpTopSun6);
        powerUpBarsUpTop[1] = findViewById(R.id.brightenedUpTopBar6_0);
        powerUpBarsUpTop[2] = findViewById(R.id.brightenedUpTopBar6_1);
        powerUpBarsUpTop[3] = findViewById(R.id.brightenedUpTopBar6_2);
        powerUpBarsUpTop[4] = findViewById(R.id.brightenedUpTopBar6_3);
        powerUpBarsUpTop[5] = findViewById(R.id.brightenedUpTopBar6_4);
        powerUpBarsUpTop[6] = findViewById(R.id.brightenedUpTopBar6_5);
        powerUpBarsUpTop[7] = findViewById(R.id.brightenedUpTopBar6_6);
        powerUpBarsUpTop[8] = findViewById(R.id.brightenedUpTopBar6_7);
        powerUpBarsUpTop[9] = findViewById(R.id.brightenedUpTopBar6_8);
        powerUpBarsUpTop[10] = findViewById(R.id.brightenedUpTopBar6_9);
        powerUpBarsUpTop[11] = findViewById(R.id.brightenedUpTopBar6_10);

        gameLogic.setPowerUpLogic(powerUps, brightenedUpBobImage, powerUpBarsUpTop);


        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton6);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelSixHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton6);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_3x5));

        daGrid[4][0] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x0));
        daGrid[4][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x1));
        daGrid[4][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x2));
        daGrid[4][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x3));
        daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x3));
        daGrid[8][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x2));
        daGrid[9][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_9x3));
        daGrid[9][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_12x2));
        daGrid[12][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_12x3));
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_12x4));
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x3));
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_14x3));
        daGrid[14][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_19x3));
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x4));
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x4));
        daGrid[21][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_22x2));
        daGrid[22][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_22x3));
        daGrid[22][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_22x4));
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x1));
        daGrid[23][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x2));
        daGrid[23][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x3));
        daGrid[23][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x4));
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_24x0));
        daGrid[24][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x1));
        daGrid[24][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x2));
        daGrid[24][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x3));
        daGrid[24][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_25x0));
        daGrid[25][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x1));
        daGrid[25][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x2));
        daGrid[25][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x3));
        daGrid[25][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_26x0));
        daGrid[26][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x1));
        daGrid[26][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x2));
        daGrid[26][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x3));
        daGrid[26][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x4));
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x1));
        daGrid[27][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x2));
        daGrid[27][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x3));
        daGrid[27][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x4));
        daGrid[27][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x2));
        daGrid[29][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x1));
        daGrid[30][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_30x2));
        daGrid[30][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x4));
        daGrid[30][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x3));
        daGrid[32][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x4));
        daGrid[32][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_34x3));
        daGrid[34][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_35x5));

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_36x3));
        daGrid[36][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_36x4));
        daGrid[36][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_36x5));

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x3));
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_37x4));
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_37x5));

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_38x3));
        daGrid[38][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_38x4));
        daGrid[38][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_38x5));

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x3));
        daGrid[40][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_41x3));
        daGrid[41][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x4));
        daGrid[42][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x3));
        daGrid[43][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x4));
        daGrid[43][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_44x3));
        daGrid[44][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_44x4));
        daGrid[44][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x1));
        daGrid[45][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x3));
        daGrid[45][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x4));
        daGrid[45][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_45x5));

        daGrid[46][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x1));
        daGrid[46][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x3));
        daGrid[46][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x4));
        daGrid[46][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_46x5));

        daGrid[47][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_47x1));
        daGrid[47][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_47x3));
        daGrid[47][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_47x4));
        daGrid[47][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_47x5));

        daGrid[48][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_48x1));
        daGrid[48][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_48x3));
        daGrid[48][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_48x4));
        daGrid[48][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_48x5));

        daGrid[49][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_49x0));
        daGrid[49][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_49x1));
        daGrid[49][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_49x2));
        daGrid[49][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_49x3));
        daGrid[49][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_49x4));
        daGrid[49][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_49x5));

        daGrid[50][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_50x0));
        daGrid[50][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_50x1));
        daGrid[50][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_50x2));
        daGrid[50][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_50x3));
        daGrid[50][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_50x4));
        daGrid[50][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_50x5));

        daGrid[51][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_51x0));
        daGrid[51][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_51x1));
        daGrid[51][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_51x2));
        daGrid[51][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_51x3));
        daGrid[51][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_51x4));
        daGrid[51][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_51x5));

        daGrid[52][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_52x0));
        daGrid[52][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_52x1));
        daGrid[52][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_52x2));
        daGrid[52][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_52x3));
        daGrid[52][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_52x4));
        daGrid[52][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_52x5));

        daGrid[53][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_53x0));
        daGrid[53][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_53x1));
        daGrid[53][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_53x2));
        daGrid[53][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_53x3));
        daGrid[53][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_53x4));
        daGrid[53][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_53x5));

        daGrid[54][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_54x0));
        daGrid[54][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_54x1));
        daGrid[54][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_54x2));
        daGrid[54][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_54x3));
        daGrid[54][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_54x4));
        daGrid[54][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_54x5));

        daGrid[55][0] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x0));
        daGrid[55][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x1));
        daGrid[55][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x2));
        daGrid[55][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x3));
        daGrid[55][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x4));
        daGrid[55][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_55x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin6_0));
        coins[0].setImageX(daGrid[16][1].getImageX());
        coins[0].setImageY(daGrid[16][1].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin6_1));
        coins[1].setImageX(daGrid[29][2].getImageX());
        coins[1].setImageY(daGrid[29][2].getImageY());

        coins[2] = new Coin((ImageView) findViewById(R.id.coin6_2));
        coins[2].setImageX(daGrid[37][3].getImageX());
        coins[2].setImageY(daGrid[37][3].getImageY());

        coins[3] = new Coin((ImageView) findViewById(R.id.coin6_3));
        coins[3].setImageX(daGrid[46][5].getImageX());
        coins[3].setImageY(daGrid[46][5].getImageY());

        return coins;
    }


    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp6_0));
        powerUps[0].setImageX(daGrid[4][5].getImageX());
        powerUps[0].setImageY(daGrid[4][5].getImageY());

        powerUps[1] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp6_1));
        powerUps[1].setImageX(daGrid[25][0].getImageX());
        powerUps[1].setImageY(daGrid[25][0].getImageY());

        powerUps[2] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp6_2));
        powerUps[2].setImageX(daGrid[38][3].getImageX());
        powerUps[2].setImageY(daGrid[38][3].getImageY());

        return powerUps;
    }


    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[6][0];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater6_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[1];
        thePath1[0] = daGrid[6][1];

        float[] xHaterMoveSpeeds1 = new float[1];
        xHaterMoveSpeeds1[0] = 0;

        float[] yHaterMoveSpeeds1 = new float[1];
        yHaterMoveSpeeds1[0] = 0;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater6_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[1];
        thePath2[0] = daGrid[6][2];

        float[] xHaterMoveSpeeds2 = new float[1];
        xHaterMoveSpeeds2[0] = 0;

        float[] yHaterMoveSpeeds2 = new float[1];
        yHaterMoveSpeeds2[0] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater6_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[1];
        thePath3[0] = daGrid[6][3];

        float[] xHaterMoveSpeeds3 = new float[1];
        xHaterMoveSpeeds3[0] = 0;

        float[] yHaterMoveSpeeds3 = new float[1];
        yHaterMoveSpeeds3[0] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater6_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[1];
        thePath4[0] = daGrid[6][4];

        float[] xHaterMoveSpeeds4 = new float[1];
        xHaterMoveSpeeds4[0] = 0;

        float[] yHaterMoveSpeeds4 = new float[1];
        yHaterMoveSpeeds4[0] = 0;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater6_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        GridImageThing[] thePath5 = new GridImageThing[1];
        thePath5[0] = daGrid[6][5];

        float[] xHaterMoveSpeeds5 = new float[1];
        xHaterMoveSpeeds5[0] = 0;

        float[] yHaterMoveSpeeds5 = new float[1];
        yHaterMoveSpeeds5[0] = 0;

        haters[5] = new Hater((ImageView) findViewById(R.id.hater6_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

        GridImageThing[] thePath6 = new GridImageThing[1];
        thePath6[0] = daGrid[12][2];

        float[] xHaterMoveSpeeds6 = new float[1];
        xHaterMoveSpeeds6[0] = 0;

        float[] yHaterMoveSpeeds6 = new float[1];
        yHaterMoveSpeeds6[0] = 0;

        haters[6] = new Hater((ImageView) findViewById(R.id.hater6_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, xHaterMoveSpeeds6, yHaterMoveSpeeds6);

        GridImageThing[] thePath7 = new GridImageThing[1];
        thePath7[0] = daGrid[14][3];

        float[] xHaterMoveSpeeds7 = new float[1];
        xHaterMoveSpeeds7[0] = 0;

        float[] yHaterMoveSpeeds7 = new float[1];
        yHaterMoveSpeeds7[0] = 0;

        haters[7] = new Hater((ImageView) findViewById(R.id.hater6_7));
        haters[7].setImageX(thePath7[0].getImageX());
        haters[7].setImageY(thePath7[0].getImageY());
        haters[7].setPath(thePath7, xHaterMoveSpeeds7, yHaterMoveSpeeds7);

        GridImageThing[] thePath8 = new GridImageThing[2];
        thePath8[0] = daGrid[20][0];
        thePath8[1] = daGrid[20][5];

        float[] xHaterMoveSpeeds8 = new float[2];
        xHaterMoveSpeeds8[0] = 0;
        xHaterMoveSpeeds8[1] = 0;

        float[] yHaterMoveSpeeds8 = new float[2];
        yHaterMoveSpeeds8[0] = 1;
        yHaterMoveSpeeds8[1] = 1;

        haters[8] = new Hater((ImageView) findViewById(R.id.hater6_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, xHaterMoveSpeeds8, yHaterMoveSpeeds8);

        GridImageThing[] thePath9 = new GridImageThing[2];
        thePath9[0] = daGrid[21][0];
        thePath9[1] = daGrid[21][5];

        float[] xHaterMoveSpeeds9 = new float[2];
        xHaterMoveSpeeds9[0] = 0;
        xHaterMoveSpeeds9[1] = 0;

        float[] yHaterMoveSpeeds9 = new float[2];
        yHaterMoveSpeeds9[0] = (float)0.66;
        yHaterMoveSpeeds9[1] = (float)0.66;

        haters[9] = new Hater((ImageView) findViewById(R.id.hater6_9));
        haters[9].setImageX(thePath9[0].getImageX());
        haters[9].setImageY(thePath9[0].getImageY());
        haters[9].setPath(thePath9, xHaterMoveSpeeds9, yHaterMoveSpeeds9);

        GridImageThing[] thePath10 = new GridImageThing[2];
        thePath10[0] = daGrid[23][0];
        thePath10[1] = daGrid[27][0];

        float[] xHaterMoveSpeeds10 = new float[2];
        xHaterMoveSpeeds10[0] = (float)1.25;
        xHaterMoveSpeeds10[1] = (float)1.25;

        float[] yHaterMoveSpeeds10 = new float[2];
        yHaterMoveSpeeds10[0] = 0;
        yHaterMoveSpeeds10[1] = 0;

        haters[10] = new Hater((ImageView) findViewById(R.id.hater6_10));
        haters[10].setImageX(thePath10[0].getImageX());
        haters[10].setImageY(thePath10[0].getImageY());
        haters[10].setPath(thePath10, xHaterMoveSpeeds10, yHaterMoveSpeeds10);

        GridImageThing[] thePath11 = new GridImageThing[1];
        thePath11[0] = daGrid[30][1];

        float[] xHaterMoveSpeeds11 = new float[1];
        xHaterMoveSpeeds11[0] = 0;

        float[] yHaterMoveSpeeds11 = new float[1];
        yHaterMoveSpeeds11[0] = 0;

        haters[11] = new Hater((ImageView) findViewById(R.id.hater6_11));
        haters[11].setImageX(thePath11[0].getImageX());
        haters[11].setImageY(thePath11[0].getImageY());
        haters[11].setPath(thePath11, xHaterMoveSpeeds11, yHaterMoveSpeeds11);

        GridImageThing[] thePath12 = new GridImageThing[4];
        thePath12[0] = daGrid[33][3];
        thePath12[1] = daGrid[35][3];
        thePath12[2] = daGrid[35][5];
        thePath12[3] = daGrid[33][5];

        float[] xHaterMoveSpeeds12 = new float[4];
        xHaterMoveSpeeds12[0] = (float)1.75;
        xHaterMoveSpeeds12[1] = 0;
        xHaterMoveSpeeds12[2] = (float)1.75;
        xHaterMoveSpeeds12[3] = 0;

        float[] yHaterMoveSpeeds12 = new float[4];
        yHaterMoveSpeeds12[0] = 0;
        yHaterMoveSpeeds12[1] = (float)1.75;
        yHaterMoveSpeeds12[2] = 0;
        yHaterMoveSpeeds12[3] = (float)1.75;

        haters[12] = new Hater((ImageView) findViewById(R.id.hater6_12));
        haters[12].setImageX(thePath12[0].getImageX());
        haters[12].setImageY(thePath12[0].getImageY());
        haters[12].setPath(thePath12, xHaterMoveSpeeds12, yHaterMoveSpeeds12);

        GridImageThing[] thePath13 = new GridImageThing[1];
        thePath13[0] = daGrid[39][5];

        float[] xHaterMoveSpeeds13 = new float[1];
        xHaterMoveSpeeds13[0] = 0;

        float[] yHaterMoveSpeeds13 = new float[1];
        yHaterMoveSpeeds13[0] = 0;

        haters[13] = new Hater((ImageView) findViewById(R.id.hater6_13));
        haters[13].setImageX(thePath13[0].getImageX());
        haters[13].setImageY(thePath13[0].getImageY());
        haters[13].setPath(thePath13, xHaterMoveSpeeds13, yHaterMoveSpeeds13);

        GridImageThing[] thePath14 = new GridImageThing[1];
        thePath14[0] = daGrid[40][5];

        float[] xHaterMoveSpeeds14 = new float[1];
        xHaterMoveSpeeds14[0] = 0;

        float[] yHaterMoveSpeeds14 = new float[1];
        yHaterMoveSpeeds14[0] = 0;

        haters[14] = new Hater((ImageView) findViewById(R.id.hater6_14));
        haters[14].setImageX(thePath14[0].getImageX());
        haters[14].setImageY(thePath14[0].getImageY());
        haters[14].setPath(thePath14, xHaterMoveSpeeds14, yHaterMoveSpeeds14);

        GridImageThing[] thePath15 = new GridImageThing[1];
        thePath15[0] = daGrid[41][5];

        float[] xHaterMoveSpeeds15 = new float[1];
        xHaterMoveSpeeds15[0] = 0;

        float[] yHaterMoveSpeeds15 = new float[1];
        yHaterMoveSpeeds15[0] = 0;

        haters[15] = new Hater((ImageView) findViewById(R.id.hater6_15));
        haters[15].setImageX(thePath15[0].getImageX());
        haters[15].setImageY(thePath15[0].getImageY());
        haters[15].setPath(thePath15, xHaterMoveSpeeds15, yHaterMoveSpeeds15);

        GridImageThing[] thePath16 = new GridImageThing[1];
        thePath16[0] = daGrid[42][5];

        float[] xHaterMoveSpeeds16 = new float[1];
        xHaterMoveSpeeds16[0] = 0;

        float[] yHaterMoveSpeeds16 = new float[1];
        yHaterMoveSpeeds16[0] = 0;

        haters[16] = new Hater((ImageView) findViewById(R.id.hater6_16));
        haters[16].setImageX(thePath16[0].getImageX());
        haters[16].setImageY(thePath16[0].getImageY());
        haters[16].setPath(thePath16, xHaterMoveSpeeds16, yHaterMoveSpeeds16);

        GridImageThing[] thePath17 = new GridImageThing[1];
        thePath17[0] = daGrid[43][5];

        float[] xHaterMoveSpeeds17 = new float[1];
        xHaterMoveSpeeds17[0] = 0;

        float[] yHaterMoveSpeeds17 = new float[1];
        yHaterMoveSpeeds17[0] = 0;

        haters[17] = new Hater((ImageView) findViewById(R.id.hater6_17));
        haters[17].setImageX(thePath17[0].getImageX());
        haters[17].setImageY(thePath17[0].getImageY());
        haters[17].setPath(thePath17, xHaterMoveSpeeds17, yHaterMoveSpeeds17);

        GridImageThing[] thePath18 = new GridImageThing[1];
        thePath18[0] = daGrid[44][5];

        float[] xHaterMoveSpeeds18 = new float[1];
        xHaterMoveSpeeds18[0] = 0;

        float[] yHaterMoveSpeeds18 = new float[1];
        yHaterMoveSpeeds18[0] = 0;

        haters[18] = new Hater((ImageView) findViewById(R.id.hater6_18));
        haters[18].setImageX(thePath18[0].getImageX());
        haters[18].setImageY(thePath18[0].getImageY());
        haters[18].setPath(thePath18, xHaterMoveSpeeds18, yHaterMoveSpeeds18);

        GridImageThing[] thePath19 = new GridImageThing[1];
        thePath19[0] = daGrid[45][5];

        float[] xHaterMoveSpeeds19 = new float[1];
        xHaterMoveSpeeds19[0] = 0;

        float[] yHaterMoveSpeeds19 = new float[1];
        yHaterMoveSpeeds19[0] = 0;

        haters[19] = new Hater((ImageView) findViewById(R.id.hater6_19));
        haters[19].setImageX(thePath19[0].getImageX());
        haters[19].setImageY(thePath19[0].getImageY());
        haters[19].setPath(thePath19, xHaterMoveSpeeds19, yHaterMoveSpeeds19);

        GridImageThing[] thePath20 = new GridImageThing[2];
        thePath20[0] = daGrid[47][3];
        thePath20[1] = daGrid[54][3];

        float[] xHaterMoveSpeeds20 = new float[2];
        xHaterMoveSpeeds20[0] = (float)2;
        xHaterMoveSpeeds20[1] = (float)2;

        float[] yHaterMoveSpeeds20 = new float[2];
        yHaterMoveSpeeds20[0] = 0;
        yHaterMoveSpeeds20[1] = 0;

        haters[20] = new Hater((ImageView) findViewById(R.id.hater6_20));
        haters[20].setImageX(thePath20[0].getImageX());
        haters[20].setImageY(thePath20[0].getImageY());
        haters[20].setPath(thePath20, xHaterMoveSpeeds20, yHaterMoveSpeeds20);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}