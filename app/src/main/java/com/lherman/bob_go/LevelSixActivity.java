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

    private GridImageThing[][] daGrid = new GridImageThing[38][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[9];
    private Coin[] coins = new Coin[2];
    private Button beginButton;
    private GameLogic gameLogic;

    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[2];
    //  The Sun will be the first one actually:
    private ImageView[] powerUpBarsUpTop = new ImageView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_six);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth/168);

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
        gameLogic.setWinCircleLogic(winCircle, daGrid[0][0]);

        TextView scoreText = findViewById(R.id.scoreText6);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);


        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob6);

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

        daGrid[4][0] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x0), screenWidth, screenHeight);
        daGrid[4][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x1), screenWidth, screenHeight);
        daGrid[4][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x2), screenWidth, screenHeight);
        daGrid[4][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x3), screenWidth, screenHeight);
        daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_4x4), screenWidth, screenHeight);
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
        daGrid[8][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_8x4), screenWidth, screenHeight);
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_9x2));
        daGrid[9][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_9x3), screenWidth, screenHeight);
        daGrid[9][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_9x4), screenWidth, screenHeight);
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
        daGrid[12][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_12x3), screenWidth, screenHeight);
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
        daGrid[14][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_14x4), screenWidth, screenHeight);
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
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_19x5), screenWidth, screenHeight);

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
        daGrid[22][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_22x3), screenWidth, screenHeight);
        daGrid[22][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_22x4), screenWidth, screenHeight);
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x1), screenWidth, screenHeight);
        daGrid[23][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x2), screenWidth, screenHeight);
        daGrid[23][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x3), screenWidth, screenHeight);
        daGrid[23][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_23x4), screenWidth, screenHeight);
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_24x0));
        daGrid[24][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x1), screenWidth, screenHeight);
        daGrid[24][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x2), screenWidth, screenHeight);
        daGrid[24][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x3), screenWidth, screenHeight);
        daGrid[24][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_24x4), screenWidth, screenHeight);
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_25x0));
        daGrid[25][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x1), screenWidth, screenHeight);
        daGrid[25][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x2), screenWidth, screenHeight);
        daGrid[25][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x3), screenWidth, screenHeight);
        daGrid[25][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_25x4), screenWidth, screenHeight);
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_26x0));
        daGrid[26][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x1), screenWidth, screenHeight);
        daGrid[26][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x2), screenWidth, screenHeight);
        daGrid[26][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x3), screenWidth, screenHeight);
        daGrid[26][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_26x4), screenWidth, screenHeight);
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x1), screenWidth, screenHeight);
        daGrid[27][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x2), screenWidth, screenHeight);
        daGrid[27][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x3), screenWidth, screenHeight);
        daGrid[27][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_27x4), screenWidth, screenHeight);
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
        daGrid[29][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_29x3), screenWidth, screenHeight);
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_30x1));
        daGrid[30][2] = new SquareObstacle((ImageView) findViewById(R.id.grid6_30x2), screenWidth, screenHeight);
        daGrid[30][3] = new SquareObstacle((ImageView) findViewById(R.id.grid6_30x3), screenWidth, screenHeight);
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
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_32x5));

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
        daGrid[34][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_34x4), screenWidth, screenHeight);
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
        daGrid[36][4] = new SquareObstacle((ImageView) findViewById(R.id.grid6_36x4), screenWidth, screenHeight);
        daGrid[36][5] = new SquareObstacle((ImageView) findViewById(R.id.grid6_36x5), screenWidth, screenHeight);

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x3));
        daGrid[37][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x4));
        daGrid[37][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid6_37x5));

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

        GridImageThing[] thePath8 = new GridImageThing[1];
        thePath8[0] = daGrid[34][3];

        float[] xHaterMoveSpeeds8 = new float[1];
        xHaterMoveSpeeds8[0] = 0;

        float[] yHaterMoveSpeeds8 = new float[1];
        yHaterMoveSpeeds8[0] = 0;

        haters[8] = new Hater((ImageView) findViewById(R.id.hater6_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, xHaterMoveSpeeds8, yHaterMoveSpeeds8);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}