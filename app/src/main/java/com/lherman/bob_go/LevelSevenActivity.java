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

    private GridImageThing[][] daGrid = new GridImageThing[51][6];
    private Hater[] haters = new Hater[23];
    private Coin[] coins = new Coin[3];
    private Button beginButton;
    private GameLogic gameLogic;
    private BrightenUpPowerUp[] powerUps = new BrightenUpPowerUp[1];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_seven);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setxMoveSpeedScreen(screenWidth /168);

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
        gameLogic.setWinCircleLogic(winCircle, daGrid[49][5]);

        TextView scoreText = findViewById(R.id.scoreText7);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        powerUps = placePowerUps(powerUps);
        ImageView brightenedUpBobImage = findViewById(R.id.brightenedUpBob7);

        ImageView[] powerUpBarsUpTop = new ImageView[12];
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
        daGrid[4][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_4x5));

        daGrid[5][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_5x4));
        daGrid[5][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_5x5));

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
        daGrid[8][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_8x5));

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

        daGrid[11][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_11x3));
        daGrid[11][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_11x4));
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
        daGrid[15][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_16x2));
        daGrid[16][3] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x3));
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x4));
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x4));
        daGrid[17][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x0));
        daGrid[18][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x1));
        daGrid[18][2] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x4));
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x0));
        daGrid[19][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x3));
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x0));
        daGrid[20][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x4));
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x0));
        daGrid[21][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x0));
        daGrid[22][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x4));
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x4));
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x0));
        daGrid[24][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x4));
        daGrid[24][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x0));
        daGrid[25][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x4));
        daGrid[25][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x0));
        daGrid[26][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x4));
        daGrid[26][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x4));
        daGrid[27][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x0));
        daGrid[28][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x4));
        daGrid[28][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x0));
        daGrid[29][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x4));
        daGrid[29][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x0));
        daGrid[30][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x4));
        daGrid[30][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x0));
        daGrid[31][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x4));
        daGrid[31][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x3));
        daGrid[32][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x4));
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_35x5));

        daGrid[36][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_36x3));
        daGrid[36][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_36x4));
        daGrid[36][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_36x5));

        daGrid[37][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_37x3));
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_37x4));
        daGrid[37][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_37x5));

        daGrid[38][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_38x3));
        daGrid[38][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_38x4));
        daGrid[38][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_38x5));

        daGrid[39][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_39x4));
        daGrid[39][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_39x5));

        daGrid[40][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_40x2));
        daGrid[40][3] = new SquareObstacle((ImageView) findViewById(R.id.grid7_40x3));
        daGrid[40][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_40x4));
        daGrid[40][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_40x5));

        daGrid[41][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_41x3));
        daGrid[41][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_41x4));
        daGrid[41][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_41x5));

        daGrid[42][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_42x4));
        daGrid[42][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x3));
        daGrid[43][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x4));
        daGrid[43][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x3));
        daGrid[44][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x4));
        daGrid[44][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x1));
        daGrid[45][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x3));
        daGrid[45][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x4));
        daGrid[45][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_45x5));

        daGrid[46][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x1));
        daGrid[46][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x3));
        daGrid[46][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x4));
        daGrid[46][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_46x5));

        daGrid[47][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_47x1));
        daGrid[47][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_47x3));
        daGrid[47][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_47x4));
        daGrid[47][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_47x5));

        daGrid[48][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x1));
        daGrid[48][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x3));
        daGrid[48][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x4));
        daGrid[48][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_48x5));

        daGrid[49][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x0));
        daGrid[49][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x1));
        daGrid[49][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x2));
        daGrid[49][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x3));
        daGrid[49][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x4));
        daGrid[49][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_49x5));

        daGrid[50][0] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x0));
        daGrid[50][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x1));
        daGrid[50][2] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x2));
        daGrid[50][3] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x3));
        daGrid[50][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x4));
        daGrid[50][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_50x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin7_0));
        coins[0].setImageX(daGrid[13][5].getImageX());
        coins[0].setImageY(daGrid[13][5].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin7_1));
        coins[1].setImageX(daGrid[32][5].getImageX());
        coins[1].setImageY(daGrid[32][5].getImageY());

        coins[2] = new Coin((ImageView) findViewById(R.id.coin7_2));
        coins[2].setImageX(daGrid[35][5].getImageX());
        coins[2].setImageY(daGrid[35][5].getImageY());

        return coins;
    }

    public BrightenUpPowerUp[] placePowerUps(BrightenUpPowerUp[] powerUps)
    {
        powerUps[0] = new BrightenUpPowerUp((ImageView) findViewById(R.id.powerUp7_0));
        powerUps[0].setImageX(daGrid[18][4].getImageX());
        powerUps[0].setImageY(daGrid[18][4].getImageY());

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

        GridImageThing[] thePath3 = new GridImageThing[4];
        thePath3[0] = daGrid[19][1];
        thePath3[1] = daGrid[19][4];
        thePath3[2] = daGrid[19][5];
        thePath3[3] = daGrid[19][2];

        float[] xHaterMoveSpeeds3 = new float[4];
        xHaterMoveSpeeds3[0] = 0;
        xHaterMoveSpeeds3[1] = 0;
        xHaterMoveSpeeds3[2] = 0;
        xHaterMoveSpeeds3[3] = 0;

        float[] yHaterMoveSpeeds3 = new float[4];
        yHaterMoveSpeeds3[0] = 3;
        yHaterMoveSpeeds3[1] = (float)0.25;
        yHaterMoveSpeeds3[2] = 3;
        yHaterMoveSpeeds3[3] = (float)0.25;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater7_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[4];
        thePath4[0] = daGrid[20][1];
        thePath4[1] = daGrid[20][4];
        thePath4[2] = daGrid[20][5];
        thePath4[3] = daGrid[20][2];

        float[] xHaterMoveSpeeds4 = new float[4];
        xHaterMoveSpeeds4[0] = 0;
        xHaterMoveSpeeds4[1] = 0;
        xHaterMoveSpeeds4[2] = 0;
        xHaterMoveSpeeds4[3] = 0;

        float[] yHaterMoveSpeeds4 = new float[4];
        yHaterMoveSpeeds4[0] = 3;
        yHaterMoveSpeeds4[1] = (float)0.25;
        yHaterMoveSpeeds4[2] = 3;
        yHaterMoveSpeeds4[3] = (float)0.25;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater7_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        GridImageThing[] thePath5 = new GridImageThing[4];
        thePath5[0] = daGrid[21][1];
        thePath5[1] = daGrid[21][4];
        thePath5[2] = daGrid[21][5];
        thePath5[3] = daGrid[21][2];

        float[] xHaterMoveSpeeds5 = new float[4];
        xHaterMoveSpeeds5[0] = 0;
        xHaterMoveSpeeds5[1] = 0;
        xHaterMoveSpeeds5[2] = 0;
        xHaterMoveSpeeds5[3] = 0;

        float[] yHaterMoveSpeeds5 = new float[4];
        yHaterMoveSpeeds5[0] = 3;
        yHaterMoveSpeeds5[1] = (float)0.25;
        yHaterMoveSpeeds5[2] = 3;
        yHaterMoveSpeeds5[3] = (float)0.25;

        haters[5] = new Hater((ImageView) findViewById(R.id.hater7_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

        GridImageThing[] thePath6 = new GridImageThing[4];
        thePath6[0] = daGrid[22][1];
        thePath6[1] = daGrid[22][4];
        thePath6[2] = daGrid[22][5];
        thePath6[3] = daGrid[22][2];

        float[] xHaterMoveSpeeds6 = new float[4];
        xHaterMoveSpeeds6[0] = 0;
        xHaterMoveSpeeds6[1] = 0;
        xHaterMoveSpeeds6[2] = 0;
        xHaterMoveSpeeds6[3] = 0;

        float[] yHaterMoveSpeeds6 = new float[4];
        yHaterMoveSpeeds6[0] = 3;
        yHaterMoveSpeeds6[1] = (float)0.25;
        yHaterMoveSpeeds6[2] = 3;
        yHaterMoveSpeeds6[3] = (float)0.25;

        haters[6] = new Hater((ImageView) findViewById(R.id.hater7_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, xHaterMoveSpeeds6, yHaterMoveSpeeds6);

        GridImageThing[] thePath7 = new GridImageThing[4];
        thePath7[0] = daGrid[23][1];
        thePath7[1] = daGrid[23][4];
        thePath7[2] = daGrid[23][5];
        thePath7[3] = daGrid[23][2];

        float[] xHaterMoveSpeeds7 = new float[4];
        xHaterMoveSpeeds7[0] = 0;
        xHaterMoveSpeeds7[1] = 0;
        xHaterMoveSpeeds7[2] = 0;
        xHaterMoveSpeeds7[3] = 0;

        float[] yHaterMoveSpeeds7 = new float[4];
        yHaterMoveSpeeds7[0] = 3;
        yHaterMoveSpeeds7[1] = (float)0.25;
        yHaterMoveSpeeds7[2] = 3;
        yHaterMoveSpeeds7[3] = (float)0.25;

        haters[7] = new Hater((ImageView) findViewById(R.id.hater7_7));
        haters[7].setImageX(thePath7[0].getImageX());
        haters[7].setImageY(thePath7[0].getImageY());
        haters[7].setPath(thePath7, xHaterMoveSpeeds7, yHaterMoveSpeeds7);

        GridImageThing[] thePath8 = new GridImageThing[4];
        thePath8[0] = daGrid[24][1];
        thePath8[1] = daGrid[24][4];
        thePath8[2] = daGrid[24][5];
        thePath8[3] = daGrid[24][2];

        float[] xHaterMoveSpeeds8 = new float[4];
        xHaterMoveSpeeds8[0] = 0;
        xHaterMoveSpeeds8[1] = 0;
        xHaterMoveSpeeds8[2] = 0;
        xHaterMoveSpeeds8[3] = 0;

        float[] yHaterMoveSpeeds8 = new float[4];
        yHaterMoveSpeeds8[0] = 3;
        yHaterMoveSpeeds8[1] = (float)0.25;
        yHaterMoveSpeeds8[2] = 3;
        yHaterMoveSpeeds8[3] = (float)0.25;

        haters[8] = new Hater((ImageView) findViewById(R.id.hater7_8));
        haters[8].setImageX(thePath8[0].getImageX());
        haters[8].setImageY(thePath8[0].getImageY());
        haters[8].setPath(thePath8, xHaterMoveSpeeds8, yHaterMoveSpeeds8);

        GridImageThing[] thePath9 = new GridImageThing[4];
        thePath9[0] = daGrid[25][1];
        thePath9[1] = daGrid[25][4];
        thePath9[2] = daGrid[25][5];
        thePath9[3] = daGrid[25][2];

        float[] xHaterMoveSpeeds9 = new float[4];
        xHaterMoveSpeeds9[0] = 0;
        xHaterMoveSpeeds9[1] = 0;
        xHaterMoveSpeeds9[2] = 0;
        xHaterMoveSpeeds9[3] = 0;

        float[] yHaterMoveSpeeds9 = new float[4];
        yHaterMoveSpeeds9[0] = 3;
        yHaterMoveSpeeds9[1] = (float)0.25;
        yHaterMoveSpeeds9[2] = 3;
        yHaterMoveSpeeds9[3] = (float)0.25;

        haters[9] = new Hater((ImageView) findViewById(R.id.hater7_9));
        haters[9].setImageX(thePath9[0].getImageX());
        haters[9].setImageY(thePath9[0].getImageY());
        haters[9].setPath(thePath9, xHaterMoveSpeeds9, yHaterMoveSpeeds9);

        GridImageThing[] thePath10 = new GridImageThing[4];
        thePath10[0] = daGrid[26][1];
        thePath10[1] = daGrid[26][4];
        thePath10[2] = daGrid[26][5];
        thePath10[3] = daGrid[26][2];

        float[] xHaterMoveSpeeds10 = new float[4];
        xHaterMoveSpeeds10[0] = 0;
        xHaterMoveSpeeds10[1] = 0;
        xHaterMoveSpeeds10[2] = 0;
        xHaterMoveSpeeds10[3] = 0;

        float[] yHaterMoveSpeeds10 = new float[4];
        yHaterMoveSpeeds10[0] = 3;
        yHaterMoveSpeeds10[1] = (float)0.25;
        yHaterMoveSpeeds10[2] = 3;
        yHaterMoveSpeeds10[3] = (float)0.25;

        haters[10] = new Hater((ImageView) findViewById(R.id.hater7_10));
        haters[10].setImageX(thePath10[0].getImageX());
        haters[10].setImageY(thePath10[0].getImageY());
        haters[10].setPath(thePath10, xHaterMoveSpeeds10, yHaterMoveSpeeds10);

        GridImageThing[] thePath11 = new GridImageThing[4];
        thePath11[0] = daGrid[27][1];
        thePath11[1] = daGrid[27][4];
        thePath11[2] = daGrid[27][5];
        thePath11[3] = daGrid[27][2];

        float[] xHaterMoveSpeeds11 = new float[4];
        xHaterMoveSpeeds11[0] = 0;
        xHaterMoveSpeeds11[1] = 0;
        xHaterMoveSpeeds11[2] = 0;
        xHaterMoveSpeeds11[3] = 0;

        float[] yHaterMoveSpeeds11 = new float[4];
        yHaterMoveSpeeds11[0] = 3;
        yHaterMoveSpeeds11[1] = (float)0.25;
        yHaterMoveSpeeds11[2] = 3;
        yHaterMoveSpeeds11[3] = (float)0.25;

        haters[11] = new Hater((ImageView) findViewById(R.id.hater7_11));
        haters[11].setImageX(thePath11[0].getImageX());
        haters[11].setImageY(thePath11[0].getImageY());
        haters[11].setPath(thePath11, xHaterMoveSpeeds11, yHaterMoveSpeeds11);

        GridImageThing[] thePath12 = new GridImageThing[4];
        thePath12[0] = daGrid[28][1];
        thePath12[1] = daGrid[28][4];
        thePath12[2] = daGrid[28][5];
        thePath12[3] = daGrid[28][2];

        float[] xHaterMoveSpeeds12 = new float[4];
        xHaterMoveSpeeds12[0] = 0;
        xHaterMoveSpeeds12[1] = 0;
        xHaterMoveSpeeds12[2] = 0;
        xHaterMoveSpeeds12[3] = 0;

        float[] yHaterMoveSpeeds12 = new float[4];
        yHaterMoveSpeeds12[0] = 3;
        yHaterMoveSpeeds12[1] = (float)0.25;
        yHaterMoveSpeeds12[2] = 3;
        yHaterMoveSpeeds12[3] = (float)0.25;

        haters[12] = new Hater((ImageView) findViewById(R.id.hater7_12));
        haters[12].setImageX(thePath12[0].getImageX());
        haters[12].setImageY(thePath12[0].getImageY());
        haters[12].setPath(thePath12, xHaterMoveSpeeds12, yHaterMoveSpeeds12);

        GridImageThing[] thePath13 = new GridImageThing[4];
        thePath13[0] = daGrid[29][1];
        thePath13[1] = daGrid[29][4];
        thePath13[2] = daGrid[29][5];
        thePath13[3] = daGrid[29][2];

        float[] xHaterMoveSpeeds13 = new float[4];
        xHaterMoveSpeeds13[0] = 0;
        xHaterMoveSpeeds13[1] = 0;
        xHaterMoveSpeeds13[2] = 0;
        xHaterMoveSpeeds13[3] = 0;

        float[] yHaterMoveSpeeds13 = new float[4];
        yHaterMoveSpeeds13[0] = 3;
        yHaterMoveSpeeds13[1] = (float)0.25;
        yHaterMoveSpeeds13[2] = 3;
        yHaterMoveSpeeds13[3] = (float)0.25;

        haters[13] = new Hater((ImageView) findViewById(R.id.hater7_13));
        haters[13].setImageX(thePath13[0].getImageX());
        haters[13].setImageY(thePath13[0].getImageY());
        haters[13].setPath(thePath13, xHaterMoveSpeeds13, yHaterMoveSpeeds13);

        GridImageThing[] thePath14 = new GridImageThing[4];
        thePath14[0] = daGrid[30][1];
        thePath14[1] = daGrid[30][4];
        thePath14[2] = daGrid[30][5];
        thePath14[3] = daGrid[30][2];

        float[] xHaterMoveSpeeds14 = new float[4];
        xHaterMoveSpeeds14[0] = 0;
        xHaterMoveSpeeds14[1] = 0;
        xHaterMoveSpeeds14[2] = 0;
        xHaterMoveSpeeds14[3] = 0;

        float[] yHaterMoveSpeeds14 = new float[4];
        yHaterMoveSpeeds14[0] = 3;
        yHaterMoveSpeeds14[1] = (float)0.25;
        yHaterMoveSpeeds14[2] = 3;
        yHaterMoveSpeeds14[3] = (float)0.25;

        haters[14] = new Hater((ImageView) findViewById(R.id.hater7_14));
        haters[14].setImageX(thePath14[0].getImageX());
        haters[14].setImageY(thePath14[0].getImageY());
        haters[14].setPath(thePath14, xHaterMoveSpeeds14, yHaterMoveSpeeds14);

        GridImageThing[] thePath15 = new GridImageThing[4];
        thePath15[0] = daGrid[31][1];
        thePath15[1] = daGrid[31][4];
        thePath15[2] = daGrid[31][5];
        thePath15[3] = daGrid[31][2];

        float[] xHaterMoveSpeeds15 = new float[4];
        xHaterMoveSpeeds15[0] = 0;
        xHaterMoveSpeeds15[1] = 0;
        xHaterMoveSpeeds15[2] = 0;
        xHaterMoveSpeeds15[3] = 0;

        float[] yHaterMoveSpeeds15 = new float[4];
        yHaterMoveSpeeds15[0] = 3;
        yHaterMoveSpeeds15[1] = (float)0.25;
        yHaterMoveSpeeds15[2] = 3;
        yHaterMoveSpeeds15[3] = (float)0.25;

        haters[15] = new Hater((ImageView) findViewById(R.id.hater7_15));
        haters[15].setImageX(thePath15[0].getImageX());
        haters[15].setImageY(thePath15[0].getImageY());
        haters[15].setPath(thePath15, xHaterMoveSpeeds15, yHaterMoveSpeeds15);

        GridImageThing[] thePath16 = new GridImageThing[4];
        thePath16[0] = daGrid[36][5];
        thePath16[1] = daGrid[36][1];
        thePath16[2] = daGrid[36][0];
        thePath16[3] = daGrid[36][4];

        float[] xHaterMoveSpeeds16 = new float[4];
        xHaterMoveSpeeds16[0] = 0;
        xHaterMoveSpeeds16[1] = 0;
        xHaterMoveSpeeds16[2] = 0;
        xHaterMoveSpeeds16[3] = 0;

        float[] yHaterMoveSpeeds16 = new float[4];
        yHaterMoveSpeeds16[0] = 2;
        yHaterMoveSpeeds16[1] = (float)0.25;
        yHaterMoveSpeeds16[2] = 2;
        yHaterMoveSpeeds16[3] = (float)0.25;

        haters[16] = new Hater((ImageView) findViewById(R.id.hater7_16));
        haters[16].setImageX(thePath16[0].getImageX());
        haters[16].setImageY(thePath16[0].getImageY());
        haters[16].setPath(thePath16, xHaterMoveSpeeds16, yHaterMoveSpeeds16);

        GridImageThing[] thePath17 = new GridImageThing[4];
        thePath17[0] = daGrid[37][0];
        thePath17[1] = daGrid[37][4];
        thePath17[2] = daGrid[37][5];
        thePath17[3] = daGrid[37][1];

        float[] xHaterMoveSpeeds17 = new float[4];
        xHaterMoveSpeeds17[0] = 0;
        xHaterMoveSpeeds17[1] = 0;
        xHaterMoveSpeeds17[2] = 0;
        xHaterMoveSpeeds17[3] = 0;

        float[] yHaterMoveSpeeds17 = new float[4];
        yHaterMoveSpeeds17[0] = 2;
        yHaterMoveSpeeds17[1] = (float)0.25;
        yHaterMoveSpeeds17[2] = 2;
        yHaterMoveSpeeds17[3] = (float)0.25;

        haters[17] = new Hater((ImageView) findViewById(R.id.hater7_17));
        haters[17].setImageX(thePath17[0].getImageX());
        haters[17].setImageY(thePath17[0].getImageY());
        haters[17].setPath(thePath17, xHaterMoveSpeeds17, yHaterMoveSpeeds17);

        GridImageThing[] thePath18 = new GridImageThing[4];
        thePath18[0] = daGrid[38][5];
        thePath18[1] = daGrid[38][1];
        thePath18[2] = daGrid[38][0];
        thePath18[3] = daGrid[38][4];

        float[] xHaterMoveSpeeds18 = new float[4];
        xHaterMoveSpeeds18[0] = 0;
        xHaterMoveSpeeds18[1] = 0;
        xHaterMoveSpeeds18[2] = 0;
        xHaterMoveSpeeds18[3] = 0;

        float[] yHaterMoveSpeeds18 = new float[4];
        yHaterMoveSpeeds18[0] = 2;
        yHaterMoveSpeeds18[1] = (float)0.25;
        yHaterMoveSpeeds18[2] = 2;
        yHaterMoveSpeeds18[3] = (float)0.25;

        haters[18] = new Hater((ImageView) findViewById(R.id.hater7_18));
        haters[18].setImageX(thePath18[0].getImageX());
        haters[18].setImageY(thePath18[0].getImageY());
        haters[18].setPath(thePath18, xHaterMoveSpeeds18, yHaterMoveSpeeds18);

        GridImageThing[] thePath19 = new GridImageThing[4];
        thePath19[0] = daGrid[40][0];
        thePath19[1] = daGrid[40][4];
        thePath19[2] = daGrid[40][5];
        thePath19[3] = daGrid[40][1];

        float[] xHaterMoveSpeeds19 = new float[4];
        xHaterMoveSpeeds19[0] = 0;
        xHaterMoveSpeeds19[1] = 0;
        xHaterMoveSpeeds19[2] = 0;
        xHaterMoveSpeeds19[3] = 0;

        float[] yHaterMoveSpeeds19 = new float[4];
        yHaterMoveSpeeds19[0] = 2;
        yHaterMoveSpeeds19[1] = (float)0.25;
        yHaterMoveSpeeds19[2] = 2;
        yHaterMoveSpeeds19[3] = (float)0.25;

        haters[19] = new Hater((ImageView) findViewById(R.id.hater7_19));
        haters[19].setImageX(thePath19[0].getImageX());
        haters[19].setImageY(thePath19[0].getImageY());
        haters[19].setPath(thePath19, xHaterMoveSpeeds19, yHaterMoveSpeeds19);

        GridImageThing[] thePath20 = new GridImageThing[4];
        thePath20[0] = daGrid[41][5];
        thePath20[1] = daGrid[41][1];
        thePath20[2] = daGrid[41][0];
        thePath20[3] = daGrid[41][4];

        float[] xHaterMoveSpeeds20 = new float[4];
        xHaterMoveSpeeds20[0] = 0;
        xHaterMoveSpeeds20[1] = 0;
        xHaterMoveSpeeds20[2] = 0;
        xHaterMoveSpeeds20[3] = 0;

        float[] yHaterMoveSpeeds20 = new float[4];
        yHaterMoveSpeeds20[0] = 2;
        yHaterMoveSpeeds20[1] = (float)0.25;
        yHaterMoveSpeeds20[2] = 2;
        yHaterMoveSpeeds20[3] = (float)0.25;

        haters[20] = new Hater((ImageView) findViewById(R.id.hater7_20));
        haters[20].setImageX(thePath20[0].getImageX());
        haters[20].setImageY(thePath20[0].getImageY());
        haters[20].setPath(thePath20, xHaterMoveSpeeds20, yHaterMoveSpeeds20);

        GridImageThing[] thePath21 = new GridImageThing[4];
        thePath21[0] = daGrid[42][0];
        thePath21[1] = daGrid[42][4];
        thePath21[2] = daGrid[42][5];
        thePath21[3] = daGrid[42][1];

        float[] xHaterMoveSpeeds21 = new float[4];
        xHaterMoveSpeeds21[0] = 0;
        xHaterMoveSpeeds21[1] = 0;
        xHaterMoveSpeeds21[2] = 0;
        xHaterMoveSpeeds21[3] = 0;

        float[] yHaterMoveSpeeds21 = new float[4];
        yHaterMoveSpeeds21[0] = 2;
        yHaterMoveSpeeds21[1] = (float)0.25;
        yHaterMoveSpeeds21[2] = 2;
        yHaterMoveSpeeds21[3] = (float)0.25;

        haters[21] = new Hater((ImageView) findViewById(R.id.hater7_21));
        haters[21].setImageX(thePath21[0].getImageX());
        haters[21].setImageY(thePath21[0].getImageY());
        haters[21].setPath(thePath21, xHaterMoveSpeeds21, yHaterMoveSpeeds21);

        GridImageThing[] thePath22 = new GridImageThing[4];
        thePath22[0] = daGrid[47][4];
        thePath22[1] = daGrid[49][4];
        thePath22[2] = daGrid[50][4];
        thePath22[3] = daGrid[48][4];

        float[] xHaterMoveSpeeds22 = new float[4];
        xHaterMoveSpeeds22[0] = 4;
        xHaterMoveSpeeds22[1] = (float)0.50;
        xHaterMoveSpeeds22[2] = 4;
        xHaterMoveSpeeds22[3] = (float)0.50;

        float[] yHaterMoveSpeeds22 = new float[4];
        yHaterMoveSpeeds22[0] = 0;
        yHaterMoveSpeeds22[1] = 0;
        yHaterMoveSpeeds22[2] = 0;
        yHaterMoveSpeeds22[3] = 0;

        haters[22] = new Hater((ImageView) findViewById(R.id.hater7_22));
        haters[22].setImageX(thePath22[0].getImageX());
        haters[22].setImageY(thePath22[0].getImageY());
        haters[22].setPath(thePath22, xHaterMoveSpeeds22, yHaterMoveSpeeds22);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}