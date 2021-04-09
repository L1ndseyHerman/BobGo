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

    private GridImageThing[][] daGrid = new GridImageThing[52][6];
    private Hater[] haters = new Hater[16];
    private Coin[] coins = new Coin[2];
    private Button beginButton;
    private GameLogic gameLogic;
    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[1];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_eight);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

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
        gameLogic.setWinCircleLogic(winCircle, daGrid[50][4]);

        TextView scoreText = findViewById(R.id.scoreText8);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob8);

        ImageView[] powerUpBarsUpTop = new ImageView[12];
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
                finish(); 
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
        daGrid[4][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_4x4));
        daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_5x1));
        daGrid[5][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x2));
        daGrid[5][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x3));
        daGrid[5][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x4));
        daGrid[5][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x1));
        daGrid[7][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x2));
        daGrid[7][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x3));
        daGrid[7][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_8x1));
        daGrid[8][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x2));
        daGrid[8][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x3));
        daGrid[8][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x4));
        daGrid[8][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_9x3));
        daGrid[9][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_9x4));
        daGrid[9][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_10x3));
        daGrid[10][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_10x4));
        daGrid[10][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_10x5));

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
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_12x4));
        daGrid[12][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_13x1));
        daGrid[13][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x2));
        daGrid[13][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x3));
        daGrid[13][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x4));
        daGrid[13][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_13x5));

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
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x1));
        daGrid[17][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x2));
        daGrid[17][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x3));
        daGrid[17][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_19x1));
        daGrid[19][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x2));
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x3));
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_20x4));
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_22x1));
        daGrid[22][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_22x4));
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid8_23x1));
        daGrid[23][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_23x4));
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x4));
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid8_27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_27x4));
        daGrid[27][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_30x0));
        daGrid[30][1] = new SquareObstacle((ImageView) findViewById(R.id.grid8_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_30x4));
        daGrid[30][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x3));
        daGrid[32][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x4));
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_35x5));

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x3));
        daGrid[36][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x4));
        daGrid[36][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_36x5));

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_37x3));
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_37x4));
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_37x5));

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x3));
        daGrid[38][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x4));
        daGrid[38][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_38x5));

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_40x3));
        daGrid[40][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x3));
        daGrid[41][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x4));
        daGrid[42][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_43x3));
        daGrid[43][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_43x4));
        daGrid[43][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_44x2));
        daGrid[44][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_44x3));
        daGrid[44][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_44x4));
        daGrid[44][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_45x0));
        daGrid[45][1] = new SquareObstacle((ImageView) findViewById(R.id.grid8_45x1));
        daGrid[45][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_45x2));
        daGrid[45][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_45x3));
        daGrid[45][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_45x4));
        daGrid[45][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_45x5));

        daGrid[46][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x1));
        daGrid[46][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x3));
        daGrid[46][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x4));
        daGrid[46][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_46x5));

        daGrid[47][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x1));
        daGrid[47][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x3));
        daGrid[47][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x4));
        daGrid[47][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_47x5));

        daGrid[48][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x1));
        daGrid[48][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x3));
        daGrid[48][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x4));
        daGrid[48][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_48x5));

        daGrid[49][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x0));
        daGrid[49][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x1));
        daGrid[49][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x2));
        daGrid[49][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x3));
        daGrid[49][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x4));
        daGrid[49][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_49x5));

        daGrid[50][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_50x0));
        daGrid[50][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_50x1));
        daGrid[50][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_50x2));
        daGrid[50][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_50x3));
        daGrid[50][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid8_50x4));
        daGrid[50][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_50x5));

        daGrid[51][0] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x0));
        daGrid[51][1] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x1));
        daGrid[51][2] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x2));
        daGrid[51][3] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x3));
        daGrid[51][4] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x4));
        daGrid[51][5] = new SquareObstacle((ImageView) findViewById(R.id.grid8_51x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin8_0));
        coins[0].setImageX(daGrid[4][3].getImageX());
        coins[0].setImageY(daGrid[4][3].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin8_1));
        coins[1].setImageX(daGrid[30][0].getImageX());
        coins[1].setImageY(daGrid[30][0].getImageY());

        return coins;
    }

    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp8_0));
        powerUps[0].setImageX(daGrid[22][4].getImageX());
        powerUps[0].setImageY(daGrid[22][4].getImageY());

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

        GridImageThing[] thePath5 = new GridImageThing[26];
        thePath5[0] = daGrid[24][5];
        thePath5[1] = daGrid[24][0];
        thePath5[2] = daGrid[24][5];
        thePath5[3] = daGrid[25][5];
        thePath5[4] = daGrid[25][0];
        thePath5[5] = daGrid[25][5];
        thePath5[6] = daGrid[26][5];
        thePath5[7] = daGrid[26][0];
        thePath5[8] = daGrid[26][5];
        thePath5[9] = daGrid[27][5];
        thePath5[10] = daGrid[28][5];
        thePath5[11] = daGrid[28][0];
        thePath5[12] = daGrid[28][5];
        thePath5[13] = daGrid[29][5];
        thePath5[14] = daGrid[29][0];
        thePath5[15] = daGrid[29][5];
        thePath5[16] = daGrid[28][5];
        thePath5[17] = daGrid[28][0];
        thePath5[18] = daGrid[28][5];
        thePath5[19] = daGrid[27][5];
        thePath5[20] = daGrid[26][5];
        thePath5[21] = daGrid[26][0];
        thePath5[22] = daGrid[26][5];
        thePath5[23] = daGrid[25][5];
        thePath5[24] = daGrid[25][0];
        thePath5[25] = daGrid[25][5];

        float[] xHaterMoveSpeeds5 = new float[26];
        xHaterMoveSpeeds5[0] = 0;
        xHaterMoveSpeeds5[1] = 0;
        xHaterMoveSpeeds5[2] = (float)0.25;
        xHaterMoveSpeeds5[3] = 0;
        xHaterMoveSpeeds5[4] = 0;
        xHaterMoveSpeeds5[5] = (float)0.25;
        xHaterMoveSpeeds5[6] = 0;
        xHaterMoveSpeeds5[7] = 0;
        xHaterMoveSpeeds5[8] = (float)0.25;
        xHaterMoveSpeeds5[9] = (float)0.25;
        xHaterMoveSpeeds5[10] = 0;
        xHaterMoveSpeeds5[11] = 0;
        xHaterMoveSpeeds5[12] = (float)0.25;
        xHaterMoveSpeeds5[13] = 0;
        xHaterMoveSpeeds5[14] = 0;
        xHaterMoveSpeeds5[15] = (float)0.25;
        xHaterMoveSpeeds5[16] = 0;
        xHaterMoveSpeeds5[17] = 0;
        xHaterMoveSpeeds5[18] = (float)0.25;
        xHaterMoveSpeeds5[19] = (float)0.25;
        xHaterMoveSpeeds5[20] = 0;
        xHaterMoveSpeeds5[21] = 0;
        xHaterMoveSpeeds5[22] = (float)0.25;
        xHaterMoveSpeeds5[23] = 0;
        xHaterMoveSpeeds5[24] = 0;
        xHaterMoveSpeeds5[25] = (float)0.25;

        float[] yHaterMoveSpeeds5 = new float[26];
        yHaterMoveSpeeds5[0] = 6;
        yHaterMoveSpeeds5[1] = 6;
        yHaterMoveSpeeds5[2] = 0;
        yHaterMoveSpeeds5[3] = 6;
        yHaterMoveSpeeds5[4] = 6;
        yHaterMoveSpeeds5[5] = 0;
        yHaterMoveSpeeds5[6] = 6;
        yHaterMoveSpeeds5[7] = 6;
        yHaterMoveSpeeds5[8] = 0;
        yHaterMoveSpeeds5[9] = 0;
        yHaterMoveSpeeds5[10] = 6;
        yHaterMoveSpeeds5[11] = 6;
        yHaterMoveSpeeds5[12] = 0;
        yHaterMoveSpeeds5[13] = 6;
        yHaterMoveSpeeds5[14] = 6;
        yHaterMoveSpeeds5[15] = 0;
        yHaterMoveSpeeds5[16] = 6;
        yHaterMoveSpeeds5[17] = 6;
        yHaterMoveSpeeds5[18] = 0;
        yHaterMoveSpeeds5[19] = 0;
        yHaterMoveSpeeds5[20] = 6;
        yHaterMoveSpeeds5[21] = 6;
        yHaterMoveSpeeds5[22] = 0;
        yHaterMoveSpeeds5[23] = 6;
        yHaterMoveSpeeds5[24] = 6;
        yHaterMoveSpeeds5[25] = 0;

        haters[5] = new Hater((ImageView) findViewById(R.id.hater8_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

        GridImageThing[] thePath6 = new GridImageThing[26];
        thePath6[0] = daGrid[25][5];
        thePath6[1] = daGrid[25][0];
        thePath6[2] = daGrid[25][5];
        thePath6[3] = daGrid[26][5];
        thePath6[4] = daGrid[26][0];
        thePath6[5] = daGrid[26][5];
        thePath6[6] = daGrid[27][5];
        thePath6[7] = daGrid[28][5];
        thePath6[8] = daGrid[28][0];
        thePath6[9] = daGrid[28][5];
        thePath6[10] = daGrid[29][5];
        thePath6[11] = daGrid[29][0];
        thePath6[12] = daGrid[29][5];
        thePath6[13] = daGrid[28][5];
        thePath6[14] = daGrid[28][0];
        thePath6[15] = daGrid[28][5];
        thePath6[16] = daGrid[27][5];
        thePath6[17] = daGrid[26][5];
        thePath6[18] = daGrid[26][0];
        thePath6[19] = daGrid[26][5];
        thePath6[20] = daGrid[25][5];
        thePath6[21] = daGrid[25][0];
        thePath6[22] = daGrid[25][5];
        thePath6[23] = daGrid[24][5];
        thePath6[24] = daGrid[24][0];
        thePath6[25] = daGrid[24][5];

        float[] xHaterMoveSpeeds6 = new float[26];
        xHaterMoveSpeeds6[0] = 0;
        xHaterMoveSpeeds6[1] = 0;
        xHaterMoveSpeeds6[2] = (float)0.25;
        xHaterMoveSpeeds6[3] = 0;
        xHaterMoveSpeeds6[4] = 0;
        xHaterMoveSpeeds6[5] = (float)0.25;
        xHaterMoveSpeeds6[6] = (float)0.25;
        xHaterMoveSpeeds6[7] = 0;
        xHaterMoveSpeeds6[8] = 0;
        xHaterMoveSpeeds6[9] = (float)0.25;
        xHaterMoveSpeeds6[10] = 0;
        xHaterMoveSpeeds6[11] = 0;
        xHaterMoveSpeeds6[12] = (float)0.25;
        xHaterMoveSpeeds6[13] = 0;
        xHaterMoveSpeeds6[14] = 0;
        xHaterMoveSpeeds6[15] = (float)0.25;
        xHaterMoveSpeeds6[16] = (float)0.25;
        xHaterMoveSpeeds6[17] = 0;
        xHaterMoveSpeeds6[18] = 0;
        xHaterMoveSpeeds6[19] = (float)0.25;
        xHaterMoveSpeeds6[20] = 0;
        xHaterMoveSpeeds6[21] = 0;
        xHaterMoveSpeeds6[22] = (float)0.25;
        xHaterMoveSpeeds6[23] = 0;
        xHaterMoveSpeeds6[24] = 0;
        xHaterMoveSpeeds6[25] = (float)0.25;

        float[] yHaterMoveSpeeds6 = new float[26];
        yHaterMoveSpeeds6[0] = 6;
        yHaterMoveSpeeds6[1] = 6;
        yHaterMoveSpeeds6[2] = 0;
        yHaterMoveSpeeds6[3] = 6;
        yHaterMoveSpeeds6[4] = 6;
        yHaterMoveSpeeds6[5] = 0;
        yHaterMoveSpeeds6[6] = 0;
        yHaterMoveSpeeds6[7] = 6;
        yHaterMoveSpeeds6[8] = 6;
        yHaterMoveSpeeds6[9] = 0;
        yHaterMoveSpeeds6[10] = 6;
        yHaterMoveSpeeds6[11] = 6;
        yHaterMoveSpeeds6[12] = 0;
        yHaterMoveSpeeds6[13] = 6;
        yHaterMoveSpeeds6[14] = 6;
        yHaterMoveSpeeds6[15] = 0;
        yHaterMoveSpeeds6[16] = 0;
        yHaterMoveSpeeds6[17] = 6;
        yHaterMoveSpeeds6[18] = 6;
        yHaterMoveSpeeds6[19] = 0;
        yHaterMoveSpeeds6[20] = 6;
        yHaterMoveSpeeds6[21] = 6;
        yHaterMoveSpeeds6[22] = 0;
        yHaterMoveSpeeds6[23] = 6;
        yHaterMoveSpeeds6[24] = 6;
        yHaterMoveSpeeds6[25] = 0;

        haters[6] = new Hater((ImageView) findViewById(R.id.hater8_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, xHaterMoveSpeeds6, yHaterMoveSpeeds6);

        GridImageThing[] thePath7 = new GridImageThing[26];
        thePath7[0] = daGrid[26][5];
        thePath7[1] = daGrid[26][0];
        thePath7[2] = daGrid[26][5];
        thePath7[3] = daGrid[27][5];
        thePath7[4] = daGrid[28][5];
        thePath7[5] = daGrid[28][0];
        thePath7[6] = daGrid[28][5];
        thePath7[7] = daGrid[29][5];
        thePath7[8] = daGrid[29][0];
        thePath7[9] = daGrid[29][5];
        thePath7[10] = daGrid[28][5];
        thePath7[11] = daGrid[28][0];
        thePath7[12] = daGrid[28][5];
        thePath7[13] = daGrid[27][5];
        thePath7[14] = daGrid[26][5];
        thePath7[15] = daGrid[26][0];
        thePath7[16] = daGrid[26][5];
        thePath7[17] = daGrid[25][5];
        thePath7[18] = daGrid[25][0];
        thePath7[19] = daGrid[25][5];
        thePath7[20] = daGrid[24][5];
        thePath7[21] = daGrid[24][0];
        thePath7[22] = daGrid[24][5];
        thePath7[23] = daGrid[25][5];
        thePath7[24] = daGrid[25][0];
        thePath7[25] = daGrid[25][5];

        float[] xHaterMoveSpeeds7 = new float[26];
        xHaterMoveSpeeds7[0] = 0;
        xHaterMoveSpeeds7[1] = 0;
        xHaterMoveSpeeds7[2] = (float)0.25;
        xHaterMoveSpeeds7[3] = (float)0.25;
        xHaterMoveSpeeds7[4] = 0;
        xHaterMoveSpeeds7[5] = 0;
        xHaterMoveSpeeds7[6] = (float)0.25;
        xHaterMoveSpeeds7[7] = 0;
        xHaterMoveSpeeds7[8] = 0;
        xHaterMoveSpeeds7[9] = (float)0.25;
        xHaterMoveSpeeds7[10] = 0;
        xHaterMoveSpeeds7[11] = 0;
        xHaterMoveSpeeds7[12] = (float)0.25;
        xHaterMoveSpeeds7[13] = (float)0.25;
        xHaterMoveSpeeds7[14] = 0;
        xHaterMoveSpeeds7[15] = 0;
        xHaterMoveSpeeds7[16] = (float)0.25;
        xHaterMoveSpeeds7[17] = 0;
        xHaterMoveSpeeds7[18] = 0;
        xHaterMoveSpeeds7[19] = (float)0.25;
        xHaterMoveSpeeds7[20] = 0;
        xHaterMoveSpeeds7[21] = 0;
        xHaterMoveSpeeds7[22] = (float)0.25;
        xHaterMoveSpeeds7[23] = 0;
        xHaterMoveSpeeds7[24] = 0;
        xHaterMoveSpeeds7[25] = (float)0.25;

        float[] yHaterMoveSpeeds7 = new float[26];
        yHaterMoveSpeeds7[0] = 6;
        yHaterMoveSpeeds7[1] = 6;
        yHaterMoveSpeeds7[2] = 0;
        yHaterMoveSpeeds7[3] = 0;
        yHaterMoveSpeeds7[4] = 6;
        yHaterMoveSpeeds7[5] = 6;
        yHaterMoveSpeeds7[6] = 0;
        yHaterMoveSpeeds7[7] = 6;
        yHaterMoveSpeeds7[8] = 6;
        yHaterMoveSpeeds7[9] = 0;
        yHaterMoveSpeeds7[10] = 6;
        yHaterMoveSpeeds7[11] = 6;
        yHaterMoveSpeeds7[12] = 0;
        yHaterMoveSpeeds7[13] = 0;
        yHaterMoveSpeeds7[14] = 6;
        yHaterMoveSpeeds7[15] = 6;
        yHaterMoveSpeeds7[16] = 0;
        yHaterMoveSpeeds7[17] = 6;
        yHaterMoveSpeeds7[18] = 6;
        yHaterMoveSpeeds7[19] = 0;
        yHaterMoveSpeeds7[20] = 6;
        yHaterMoveSpeeds7[21] = 6;
        yHaterMoveSpeeds7[22] = 0;
        yHaterMoveSpeeds7[23] = 6;
        yHaterMoveSpeeds7[24] = 6;
        yHaterMoveSpeeds7[25] = 0;

        haters[7] = new Hater((ImageView) findViewById(R.id.hater8_7));
        haters[7].setImageX(thePath7[0].getImageX());
        haters[7].setImageY(thePath7[0].getImageY());
        haters[7].setPath(thePath7, xHaterMoveSpeeds7, yHaterMoveSpeeds7);

        GridImageThing[] thePath8 = new GridImageThing[26];
        thePath8[0] = daGrid[28][5];
        thePath8[1] = daGrid[28][0];
        thePath8[2] = daGrid[28][5];
        thePath8[3] = daGrid[29][5];
        thePath8[4] = daGrid[29][0];
        thePath8[5] = daGrid[29][5];
        thePath8[6] = daGrid[28][5];
        thePath8[7] = daGrid[28][0];
        thePath8[8] = daGrid[28][5];
        thePath8[9] = daGrid[27][5];
        thePath8[10] = daGrid[26][5];
        thePath8[11] = daGrid[26][0];
        thePath8[12] = daGrid[26][5];
        thePath8[13] = daGrid[25][5];
        thePath8[14] = daGrid[25][0];
        thePath8[15] = daGrid[25][5];
        thePath8[16] = daGrid[24][5];
        thePath8[17] = daGrid[24][0];
        thePath8[18] = daGrid[24][5];
        thePath8[19] = daGrid[25][5];
        thePath8[20] = daGrid[25][0];
        thePath8[21] = daGrid[25][5];
        thePath8[22] = daGrid[26][5];
        thePath8[23] = daGrid[26][0];
        thePath8[24] = daGrid[26][5];
        thePath8[25] = daGrid[27][5];

        float[] xHaterMoveSpeeds8 = new float[26];
        xHaterMoveSpeeds8[0] = 0;
        xHaterMoveSpeeds8[1] = 0;
        xHaterMoveSpeeds8[2] = (float)0.25;
        xHaterMoveSpeeds8[3] = 0;
        xHaterMoveSpeeds8[4] = 0;
        xHaterMoveSpeeds8[5] = (float)0.25;
        xHaterMoveSpeeds8[6] = 0;
        xHaterMoveSpeeds8[7] = 0;
        xHaterMoveSpeeds8[8] = (float)0.25;
        xHaterMoveSpeeds8[9] = (float)0.25;
        xHaterMoveSpeeds8[10] = 0;
        xHaterMoveSpeeds8[11] = 0;
        xHaterMoveSpeeds8[12] = (float)0.25;
        xHaterMoveSpeeds8[13] = 0;
        xHaterMoveSpeeds8[14] = 0;
        xHaterMoveSpeeds8[15] = (float)0.25;
        xHaterMoveSpeeds8[16] = 0;
        xHaterMoveSpeeds8[17] = 0;
        xHaterMoveSpeeds8[18] = (float)0.25;
        xHaterMoveSpeeds8[19] = 0;
        xHaterMoveSpeeds8[20] = 0;
        xHaterMoveSpeeds8[21] = (float)0.25;
        xHaterMoveSpeeds8[22] = 0;
        xHaterMoveSpeeds8[23] = 0;
        xHaterMoveSpeeds8[24] = (float)0.25;
        xHaterMoveSpeeds8[25] = (float)0.25;

        float[] yHaterMoveSpeeds8 = new float[26];
        yHaterMoveSpeeds8[0] = 6;
        yHaterMoveSpeeds8[1] = 6;
        yHaterMoveSpeeds8[2] = 0;
        yHaterMoveSpeeds8[3] = 6;
        yHaterMoveSpeeds8[4] = 6;
        yHaterMoveSpeeds8[5] = 0;
        yHaterMoveSpeeds8[6] = 6;
        yHaterMoveSpeeds8[7] = 6;
        yHaterMoveSpeeds8[8] = 0;
        yHaterMoveSpeeds8[9] = 0;
        yHaterMoveSpeeds8[10] = 6;
        yHaterMoveSpeeds8[11] = 6;
        yHaterMoveSpeeds8[12] = 0;
        yHaterMoveSpeeds8[13] = 6;
        yHaterMoveSpeeds8[14] = 6;
        yHaterMoveSpeeds8[15] = 0;
        yHaterMoveSpeeds8[16] = 6;
        yHaterMoveSpeeds8[17] = 6;
        yHaterMoveSpeeds8[18] = 0;
        yHaterMoveSpeeds8[19] = 6;
        yHaterMoveSpeeds8[20] = 6;
        yHaterMoveSpeeds8[21] = 0;
        yHaterMoveSpeeds8[22] = 6;
        yHaterMoveSpeeds8[23] = 6;
        yHaterMoveSpeeds8[24] = 0;
        yHaterMoveSpeeds8[25] = 0;

        haters[8] = new Hater((ImageView) findViewById(R.id.hater8_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, xHaterMoveSpeeds8, yHaterMoveSpeeds8);

        GridImageThing[] thePath9 = new GridImageThing[26];
        thePath9[0] = daGrid[29][5];
        thePath9[1] = daGrid[29][0];
        thePath9[2] = daGrid[29][5];
        thePath9[3] = daGrid[28][5];
        thePath9[4] = daGrid[28][0];
        thePath9[5] = daGrid[28][5];
        thePath9[6] = daGrid[27][5];
        thePath9[7] = daGrid[26][5];
        thePath9[8] = daGrid[26][0];
        thePath9[9] = daGrid[26][5];
        thePath9[10] = daGrid[25][5];
        thePath9[11] = daGrid[25][0];
        thePath9[12] = daGrid[25][5];
        thePath9[13] = daGrid[24][5];
        thePath9[14] = daGrid[24][0];
        thePath9[15] = daGrid[24][5];
        thePath9[16] = daGrid[25][5];
        thePath9[17] = daGrid[25][0];
        thePath9[18] = daGrid[25][5];
        thePath9[19] = daGrid[26][5];
        thePath9[20] = daGrid[26][0];
        thePath9[21] = daGrid[26][5];
        thePath9[22] = daGrid[27][5];
        thePath9[23] = daGrid[28][5];
        thePath9[24] = daGrid[28][0];
        thePath9[25] = daGrid[28][5];

        float[] xHaterMoveSpeeds9 = new float[26];
        xHaterMoveSpeeds9[0] = 0;
        xHaterMoveSpeeds9[1] = 0;
        xHaterMoveSpeeds9[2] = (float)0.25;
        xHaterMoveSpeeds9[3] = 0;
        xHaterMoveSpeeds9[4] = 0;
        xHaterMoveSpeeds9[5] = (float)0.25;
        xHaterMoveSpeeds9[6] = (float)0.25;
        xHaterMoveSpeeds9[7] = 0;
        xHaterMoveSpeeds9[8] = 0;
        xHaterMoveSpeeds9[9] = (float)0.25;
        xHaterMoveSpeeds9[10] = 0;
        xHaterMoveSpeeds9[11] = 0;
        xHaterMoveSpeeds9[12] = (float)0.25;
        xHaterMoveSpeeds9[13] = 0;
        xHaterMoveSpeeds9[14] = 0;
        xHaterMoveSpeeds9[15] = (float)0.25;
        xHaterMoveSpeeds9[16] = 0;
        xHaterMoveSpeeds9[17] = 0;
        xHaterMoveSpeeds9[18] = (float)0.25;
        xHaterMoveSpeeds9[19] = 0;
        xHaterMoveSpeeds9[20] = 0;
        xHaterMoveSpeeds9[21] = (float)0.25;
        xHaterMoveSpeeds9[22] = (float)0.25;
        xHaterMoveSpeeds9[23] = 0;
        xHaterMoveSpeeds9[24] = 0;
        xHaterMoveSpeeds9[25] = (float)0.25;

        float[] yHaterMoveSpeeds9 = new float[26];
        yHaterMoveSpeeds9[0] = 6;
        yHaterMoveSpeeds9[1] = 6;
        yHaterMoveSpeeds9[2] = 0;
        yHaterMoveSpeeds9[3] = 6;
        yHaterMoveSpeeds9[4] = 6;
        yHaterMoveSpeeds9[5] = 0;
        yHaterMoveSpeeds9[6] = 0;
        yHaterMoveSpeeds9[7] = 6;
        yHaterMoveSpeeds9[8] = 6;
        yHaterMoveSpeeds9[9] = 0;
        yHaterMoveSpeeds9[10] = 6;
        yHaterMoveSpeeds9[11] = 6;
        yHaterMoveSpeeds9[12] = 0;
        yHaterMoveSpeeds9[13] = 6;
        yHaterMoveSpeeds9[14] = 6;
        yHaterMoveSpeeds9[15] = 0;
        yHaterMoveSpeeds9[16] = 6;
        yHaterMoveSpeeds9[17] = 6;
        yHaterMoveSpeeds9[18] = 0;
        yHaterMoveSpeeds9[19] = 6;
        yHaterMoveSpeeds9[20] = 6;
        yHaterMoveSpeeds9[21] = 0;
        yHaterMoveSpeeds9[22] = 0;
        yHaterMoveSpeeds9[23] = 6;
        yHaterMoveSpeeds9[24] = 6;
        yHaterMoveSpeeds9[25] = 0;

        haters[9] = new Hater((ImageView) findViewById(R.id.hater8_9));
        haters[9].setImageX(thePath9[0].getImageX());
        haters[9].setImageY(thePath9[0].getImageY());
        haters[9].setPath(thePath9, xHaterMoveSpeeds9, yHaterMoveSpeeds9);

        GridImageThing[] thePath10 = new GridImageThing[4];
        thePath10[0] = daGrid[39][3];
        thePath10[1] = daGrid[41][3];
        thePath10[2] = daGrid[41][5];
        thePath10[3] = daGrid[39][5];

        float[] xHaterMoveSpeeds10 = new float[4];
        xHaterMoveSpeeds10[0] = (float)0.25;
        xHaterMoveSpeeds10[1] = 0;
        xHaterMoveSpeeds10[2] = (float)0.25;
        xHaterMoveSpeeds10[3] = 0;

        float[] yHaterMoveSpeeds10 = new float[4];
        yHaterMoveSpeeds10[0] = 0;
        yHaterMoveSpeeds10[1] = 2;
        yHaterMoveSpeeds10[2] = 0;
        yHaterMoveSpeeds10[3] = 2;

        haters[10] = new Hater((ImageView) findViewById(R.id.hater8_10));
        haters[10].setImageX(thePath10[0].getImageX());
        haters[10].setImageY(thePath10[0].getImageY());
        haters[10].setPath(thePath10, xHaterMoveSpeeds10, yHaterMoveSpeeds10);

        GridImageThing[] thePath11 = new GridImageThing[4];
        thePath11[0] = daGrid[41][5];
        thePath11[1] = daGrid[39][5];
        thePath11[2] = daGrid[39][3];
        thePath11[3] = daGrid[41][3];

        float[] xHaterMoveSpeeds11 = new float[4];
        xHaterMoveSpeeds11[0] = (float)0.25;
        xHaterMoveSpeeds11[1] = 0;
        xHaterMoveSpeeds11[2] = (float)0.25;
        xHaterMoveSpeeds11[3] = 0;

        float[] yHaterMoveSpeeds11 = new float[4];
        yHaterMoveSpeeds11[0] = 0;
        yHaterMoveSpeeds11[1] = 2;
        yHaterMoveSpeeds11[2] = 0;
        yHaterMoveSpeeds11[3] = 2;

        haters[11] = new Hater((ImageView) findViewById(R.id.hater8_11));
        haters[11].setImageX(thePath11[0].getImageX());
        haters[11].setImageY(thePath11[0].getImageY());
        haters[11].setPath(thePath11, xHaterMoveSpeeds11, yHaterMoveSpeeds11);

        GridImageThing[] thePath12 = new GridImageThing[18];
        thePath12[0] = daGrid[46][5];
        thePath12[1] = daGrid[46][0];
        thePath12[2] = daGrid[46][5];
        thePath12[3] = daGrid[47][5];
        thePath12[4] = daGrid[47][0];
        thePath12[5] = daGrid[47][5];
        thePath12[6] = daGrid[48][5];
        thePath12[7] = daGrid[48][0];
        thePath12[8] = daGrid[48][5];
        thePath12[9] = daGrid[49][5];
        thePath12[10] = daGrid[49][0];
        thePath12[11] = daGrid[49][5];
        thePath12[12] = daGrid[48][5];
        thePath12[13] = daGrid[48][0];
        thePath12[14] = daGrid[48][5];
        thePath12[15] = daGrid[47][5];
        thePath12[16] = daGrid[47][0];
        thePath12[17] = daGrid[47][5];

        float[] xHaterMoveSpeeds12 = new float[18];
        xHaterMoveSpeeds12[0] = 0;
        xHaterMoveSpeeds12[1] = 0;
        xHaterMoveSpeeds12[2] = (float)0.25;
        xHaterMoveSpeeds12[3] = 0;
        xHaterMoveSpeeds12[4] = 0;
        xHaterMoveSpeeds12[5] = (float)0.25;
        xHaterMoveSpeeds12[6] = 0;
        xHaterMoveSpeeds12[7] = 0;
        xHaterMoveSpeeds12[8] = (float)0.25;
        xHaterMoveSpeeds12[9] = 0;
        xHaterMoveSpeeds12[10] = 0;
        xHaterMoveSpeeds12[11] = (float)0.25;
        xHaterMoveSpeeds12[12] = 0;
        xHaterMoveSpeeds12[13] = 0;
        xHaterMoveSpeeds12[14] = (float)0.25;
        xHaterMoveSpeeds12[15] = 0;
        xHaterMoveSpeeds12[16] = 0;
        xHaterMoveSpeeds12[17] = (float)0.25;

        float[] yHaterMoveSpeeds12 = new float[18];
        yHaterMoveSpeeds12[0] = 6;
        yHaterMoveSpeeds12[1] = 6;
        yHaterMoveSpeeds12[2] = 0;
        yHaterMoveSpeeds12[3] = 6;
        yHaterMoveSpeeds12[4] = 6;
        yHaterMoveSpeeds12[5] = 0;
        yHaterMoveSpeeds12[6] = 6;
        yHaterMoveSpeeds12[7] = 6;
        yHaterMoveSpeeds12[8] = 0;
        yHaterMoveSpeeds12[9] = 6;
        yHaterMoveSpeeds12[10] = 6;
        yHaterMoveSpeeds12[11] = 0;
        yHaterMoveSpeeds12[12] = 6;
        yHaterMoveSpeeds12[13] = 6;
        yHaterMoveSpeeds12[14] = 0;
        yHaterMoveSpeeds12[15] = 6;
        yHaterMoveSpeeds12[16] = 6;
        yHaterMoveSpeeds12[17] = 0;

        haters[12] = new Hater((ImageView) findViewById(R.id.hater8_12));
        haters[12].setImageX(thePath12[0].getImageX());
        haters[12].setImageY(thePath12[0].getImageY());
        haters[12].setPath(thePath12, xHaterMoveSpeeds12, yHaterMoveSpeeds12);

        GridImageThing[] thePath13 = new GridImageThing[18];
        thePath13[0] = daGrid[47][5];
        thePath13[1] = daGrid[47][0];
        thePath13[2] = daGrid[47][5];
        thePath13[3] = daGrid[48][5];
        thePath13[4] = daGrid[48][0];
        thePath13[5] = daGrid[48][5];
        thePath13[6] = daGrid[49][5];
        thePath13[7] = daGrid[49][0];
        thePath13[8] = daGrid[49][5];
        thePath13[9] = daGrid[48][5];
        thePath13[10] = daGrid[48][0];
        thePath13[11] = daGrid[48][5];
        thePath13[12] = daGrid[47][5];
        thePath13[13] = daGrid[47][0];
        thePath13[14] = daGrid[47][5];
        thePath13[15] = daGrid[46][5];
        thePath13[16] = daGrid[46][0];
        thePath13[17] = daGrid[46][5];

        float[] xHaterMoveSpeeds13 = new float[18];
        xHaterMoveSpeeds13[0] = 0;
        xHaterMoveSpeeds13[1] = 0;
        xHaterMoveSpeeds13[2] = (float)0.25;
        xHaterMoveSpeeds13[3] = 0;
        xHaterMoveSpeeds13[4] = 0;
        xHaterMoveSpeeds13[5] = (float)0.25;
        xHaterMoveSpeeds13[6] = 0;
        xHaterMoveSpeeds13[7] = 0;
        xHaterMoveSpeeds13[8] = (float)0.25;
        xHaterMoveSpeeds13[9] = 0;
        xHaterMoveSpeeds13[10] = 0;
        xHaterMoveSpeeds13[11] = (float)0.25;
        xHaterMoveSpeeds13[12] = 0;
        xHaterMoveSpeeds13[13] = 0;
        xHaterMoveSpeeds13[14] = (float)0.25;
        xHaterMoveSpeeds13[15] = 0;
        xHaterMoveSpeeds13[16] = 0;
        xHaterMoveSpeeds13[17] = (float)0.25;

        float[] yHaterMoveSpeeds13 = new float[18];
        yHaterMoveSpeeds13[0] = 6;
        yHaterMoveSpeeds13[1] = 6;
        yHaterMoveSpeeds13[2] = 0;
        yHaterMoveSpeeds13[3] = 6;
        yHaterMoveSpeeds13[4] = 6;
        yHaterMoveSpeeds13[5] = 0;
        yHaterMoveSpeeds13[6] = 6;
        yHaterMoveSpeeds13[7] = 6;
        yHaterMoveSpeeds13[8] = 0;
        yHaterMoveSpeeds13[9] = 6;
        yHaterMoveSpeeds13[10] = 6;
        yHaterMoveSpeeds13[11] = 0;
        yHaterMoveSpeeds13[12] = 6;
        yHaterMoveSpeeds13[13] = 6;
        yHaterMoveSpeeds13[14] = 0;
        yHaterMoveSpeeds13[15] = 6;
        yHaterMoveSpeeds13[16] = 6;
        yHaterMoveSpeeds13[17] = 0;

        haters[13] = new Hater((ImageView) findViewById(R.id.hater8_13));
        haters[13].setImageX(thePath13[0].getImageX());
        haters[13].setImageY(thePath13[0].getImageY());
        haters[13].setPath(thePath13, xHaterMoveSpeeds13, yHaterMoveSpeeds13);

        GridImageThing[] thePath14 = new GridImageThing[18];
        thePath14[0] = daGrid[48][5];
        thePath14[1] = daGrid[48][0];
        thePath14[2] = daGrid[48][5];
        thePath14[3] = daGrid[49][5];
        thePath14[4] = daGrid[49][0];
        thePath14[5] = daGrid[49][5];
        thePath14[6] = daGrid[48][5];
        thePath14[7] = daGrid[48][0];
        thePath14[8] = daGrid[48][5];
        thePath14[9] = daGrid[47][5];
        thePath14[10] = daGrid[47][0];
        thePath14[11] = daGrid[47][5];
        thePath14[12] = daGrid[46][5];
        thePath14[13] = daGrid[46][0];
        thePath14[14] = daGrid[46][5];
        thePath14[15] = daGrid[47][5];
        thePath14[16] = daGrid[47][0];
        thePath14[17] = daGrid[47][5];

        float[] xHaterMoveSpeeds14 = new float[18];
        xHaterMoveSpeeds14[0] = 0;
        xHaterMoveSpeeds14[1] = 0;
        xHaterMoveSpeeds14[2] = (float)0.25;
        xHaterMoveSpeeds14[3] = 0;
        xHaterMoveSpeeds14[4] = 0;
        xHaterMoveSpeeds14[5] = (float)0.25;
        xHaterMoveSpeeds14[6] = 0;
        xHaterMoveSpeeds14[7] = 0;
        xHaterMoveSpeeds14[8] = (float)0.25;
        xHaterMoveSpeeds14[9] = 0;
        xHaterMoveSpeeds14[10] = 0;
        xHaterMoveSpeeds14[11] = (float)0.25;
        xHaterMoveSpeeds14[12] = 0;
        xHaterMoveSpeeds14[13] = 0;
        xHaterMoveSpeeds14[14] = (float)0.25;
        xHaterMoveSpeeds14[15] = 0;
        xHaterMoveSpeeds14[16] = 0;
        xHaterMoveSpeeds14[17] = (float)0.25;

        float[] yHaterMoveSpeeds14 = new float[18];
        yHaterMoveSpeeds14[0] = 6;
        yHaterMoveSpeeds14[1] = 6;
        yHaterMoveSpeeds14[2] = 0;
        yHaterMoveSpeeds14[3] = 6;
        yHaterMoveSpeeds14[4] = 6;
        yHaterMoveSpeeds14[5] = 0;
        yHaterMoveSpeeds14[6] = 6;
        yHaterMoveSpeeds14[7] = 6;
        yHaterMoveSpeeds14[8] = 0;
        yHaterMoveSpeeds14[9] = 6;
        yHaterMoveSpeeds14[10] = 6;
        yHaterMoveSpeeds14[11] = 0;
        yHaterMoveSpeeds14[12] = 6;
        yHaterMoveSpeeds14[13] = 6;
        yHaterMoveSpeeds14[14] = 0;
        yHaterMoveSpeeds14[15] = 6;
        yHaterMoveSpeeds14[16] = 6;
        yHaterMoveSpeeds14[17] = 0;

        haters[14] = new Hater((ImageView) findViewById(R.id.hater8_14));
        haters[14].setImageX(thePath14[0].getImageX());
        haters[14].setImageY(thePath14[0].getImageY());
        haters[14].setPath(thePath14, xHaterMoveSpeeds14, yHaterMoveSpeeds14);

        GridImageThing[] thePath15 = new GridImageThing[18];
        thePath15[0] = daGrid[49][5];
        thePath15[1] = daGrid[49][0];
        thePath15[2] = daGrid[49][5];
        thePath15[3] = daGrid[48][5];
        thePath15[4] = daGrid[48][0];
        thePath15[5] = daGrid[48][5];
        thePath15[6] = daGrid[47][5];
        thePath15[7] = daGrid[47][0];
        thePath15[8] = daGrid[47][5];
        thePath15[9] = daGrid[46][5];
        thePath15[10] = daGrid[46][0];
        thePath15[11] = daGrid[46][5];
        thePath15[12] = daGrid[47][5];
        thePath15[13] = daGrid[47][0];
        thePath15[14] = daGrid[47][5];
        thePath15[15] = daGrid[48][5];
        thePath15[16] = daGrid[48][0];
        thePath15[17] = daGrid[48][5];

        float[] xHaterMoveSpeeds15 = new float[18];
        xHaterMoveSpeeds15[0] = 0;
        xHaterMoveSpeeds15[1] = 0;
        xHaterMoveSpeeds15[2] = (float)0.25;
        xHaterMoveSpeeds15[3] = 0;
        xHaterMoveSpeeds15[4] = 0;
        xHaterMoveSpeeds15[5] = (float)0.25;
        xHaterMoveSpeeds15[6] = 0;
        xHaterMoveSpeeds15[7] = 0;
        xHaterMoveSpeeds15[8] = (float)0.25;
        xHaterMoveSpeeds15[9] = 0;
        xHaterMoveSpeeds15[10] = 0;
        xHaterMoveSpeeds15[11] = (float)0.25;
        xHaterMoveSpeeds15[12] = 0;
        xHaterMoveSpeeds15[13] = 0;
        xHaterMoveSpeeds15[14] = (float)0.25;
        xHaterMoveSpeeds15[15] = 0;
        xHaterMoveSpeeds15[16] = 0;
        xHaterMoveSpeeds15[17] = (float)0.25;

        float[] yHaterMoveSpeeds15 = new float[18];
        yHaterMoveSpeeds15[0] = 6;
        yHaterMoveSpeeds15[1] = 6;
        yHaterMoveSpeeds15[2] = 0;
        yHaterMoveSpeeds15[3] = 6;
        yHaterMoveSpeeds15[4] = 6;
        yHaterMoveSpeeds15[5] = 0;
        yHaterMoveSpeeds15[6] = 6;
        yHaterMoveSpeeds15[7] = 6;
        yHaterMoveSpeeds15[8] = 0;
        yHaterMoveSpeeds15[9] = 6;
        yHaterMoveSpeeds15[10] = 6;
        yHaterMoveSpeeds15[11] = 0;
        yHaterMoveSpeeds15[12] = 6;
        yHaterMoveSpeeds15[13] = 6;
        yHaterMoveSpeeds15[14] = 0;
        yHaterMoveSpeeds15[15] = 6;
        yHaterMoveSpeeds15[16] = 6;
        yHaterMoveSpeeds15[17] = 0;

        haters[15] = new Hater((ImageView) findViewById(R.id.hater8_15));
        haters[15].setImageX(thePath15[0].getImageX());
        haters[15].setImageY(thePath15[0].getImageY());
        haters[15].setPath(thePath15, xHaterMoveSpeeds15, yHaterMoveSpeeds15);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}