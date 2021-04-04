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

    private GridImageThing[][] daGrid = new GridImageThing[34][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[16];
    private Coin[] coins = new Coin[2];
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
        daGrid[16][3] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x3), screenWidth, screenHeight);
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x4), screenWidth, screenHeight);
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_16x5), screenWidth, screenHeight);

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_17x4));
        daGrid[17][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_17x5), screenWidth, screenHeight);

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x0));
        daGrid[18][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x1), screenWidth, screenHeight);
        daGrid[18][2] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x2), screenWidth, screenHeight);
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_18x4));
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_18x5), screenWidth, screenHeight);

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x0));
        daGrid[19][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_19x1), screenWidth, screenHeight);
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x3));
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_19x4));
        daGrid[19][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_19x5), screenWidth, screenHeight);

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x0));
        daGrid[20][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_20x1), screenWidth, screenHeight);
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_20x4));
        daGrid[20][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_20x5), screenWidth, screenHeight);

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x0));
        daGrid[21][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_21x1), screenWidth, screenHeight);
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_21x5), screenWidth, screenHeight);

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x0));
        daGrid[22][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_22x1), screenWidth, screenHeight);
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_22x4));
        daGrid[22][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_22x5), screenWidth, screenHeight);

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_23x1), screenWidth, screenHeight);
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_23x4));
        daGrid[23][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_23x5), screenWidth, screenHeight);

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x0));
        daGrid[24][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_24x1), screenWidth, screenHeight);
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_24x4));
        daGrid[24][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_24x5), screenWidth, screenHeight);

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x0));
        daGrid[25][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_25x1), screenWidth, screenHeight);
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_25x4));
        daGrid[25][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_25x5), screenWidth, screenHeight);

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x0));
        daGrid[26][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_26x1), screenWidth, screenHeight);
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_26x4));
        daGrid[26][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_26x5), screenWidth, screenHeight);

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_27x1), screenWidth, screenHeight);
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_27x4));
        daGrid[27][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_27x5), screenWidth, screenHeight);

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x0));
        daGrid[28][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_28x1), screenWidth, screenHeight);
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_28x4));
        daGrid[28][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_28x5), screenWidth, screenHeight);

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x0));
        daGrid[29][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_29x1), screenWidth, screenHeight);
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_29x4));
        daGrid[29][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_29x5), screenWidth, screenHeight);

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x0));
        daGrid[30][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_30x1), screenWidth, screenHeight);
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_30x4));
        daGrid[30][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_30x5), screenWidth, screenHeight);

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x0));
        daGrid[31][1] = new SquareObstacle((ImageView) findViewById(R.id.grid7_31x1), screenWidth, screenHeight);
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid7_31x4));
        daGrid[31][5] = new SquareObstacle((ImageView) findViewById(R.id.grid7_31x5), screenWidth, screenHeight);

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

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}