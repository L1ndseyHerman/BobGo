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

    private GridImageThing[][] daGrid = new GridImageThing[44][6];
    private int screenWidth, screenHeight;
    private Hater[] haters = new Hater[30];
    private Coin[] coins = new Coin[4];
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
        gameLogic.setWinCircleLogic(winCircle, daGrid[42][3]);

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
        daGrid[19][2] = new SquareObstacle((ImageView) findViewById(R.id.grid5_19x2), screenWidth, screenHeight);
        daGrid[19][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_19x3), screenWidth, screenHeight);
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x4));
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_20x2));
        daGrid[20][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_20x3), screenWidth, screenHeight);
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_20x4));
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_21x2));
        daGrid[21][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_21x3), screenWidth, screenHeight);
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_21x4));
        daGrid[21][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_22x0));
        daGrid[22][1] = new SquareObstacle((ImageView) findViewById(R.id.grid5_22x1), screenWidth, screenHeight);
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_22x3));
        daGrid[22][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_22x4), screenWidth, screenHeight);
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_23x0));
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_23x3));
        daGrid[23][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_23x4), screenWidth, screenHeight);
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_24x2));
        daGrid[24][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_24x3), screenWidth, screenHeight);
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_25x2));
        daGrid[25][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_25x3), screenWidth, screenHeight);
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x3));
        daGrid[26][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x4));
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x0));
        daGrid[27][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x4));
        daGrid[27][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_28x2));
        daGrid[28][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_28x3), screenWidth, screenHeight);
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x4));
        daGrid[30][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_32x2));
        daGrid[32][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_32x3), screenWidth, screenHeight);
        daGrid[32][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_32x4));
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_33x1));
        daGrid[33][2] = new SquareObstacle((ImageView) findViewById(R.id.grid5_33x2), screenWidth, screenHeight);
        daGrid[33][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_33x3), screenWidth, screenHeight);
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x3));
        daGrid[34][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_35x5));

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_36x0));
        daGrid[36][1] = new SquareObstacle((ImageView) findViewById(R.id.grid5_36x1), screenWidth, screenHeight);
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_36x3));
        daGrid[36][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_36x4));
        daGrid[36][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_36x5));

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_37x0));
        daGrid[37][1] = new SquareObstacle((ImageView) findViewById(R.id.grid5_37x1), screenWidth, screenHeight);
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_37x3));
        daGrid[37][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_37x4));
        daGrid[37][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_37x5));

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x3));
        daGrid[38][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x4));
        daGrid[38][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_38x5));

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x3));
        daGrid[40][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x3));
        daGrid[41][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_42x3));
        daGrid[42][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_42x4), screenWidth, screenHeight);
        daGrid[42][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid5_42x5));

        daGrid[43][0] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x0), screenWidth, screenHeight);
        daGrid[43][1] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x1), screenWidth, screenHeight);
        daGrid[43][2] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x2), screenWidth, screenHeight);
        daGrid[43][3] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x3), screenWidth, screenHeight);
        daGrid[43][4] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x4), screenWidth, screenHeight);
        daGrid[43][5] = new SquareObstacle((ImageView) findViewById(R.id.grid5_43x5), screenWidth, screenHeight);

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

        coins[2] = new Coin((ImageView) findViewById(R.id.coin5_2));
        coins[2].setImageX(daGrid[22][0].getImageX());
        coins[2].setImageY(daGrid[22][0].getImageY());

        coins[3] = new Coin((ImageView) findViewById(R.id.coin5_3));
        coins[3].setImageX(daGrid[32][2].getImageX());
        coins[3].setImageY(daGrid[32][2].getImageY());

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

        GridImageThing[] thePath10 = new GridImageThing[2];
        thePath10[0] = daGrid[20][2];
        thePath10[1] = daGrid[25][2];

        float[] xHaterMoveSpeeds10 = new float[2];
        xHaterMoveSpeeds10[0] = (float)1.25;
        xHaterMoveSpeeds10[1] = (float)1.25;

        float[] yHaterMoveSpeeds10 = new float[2];
        yHaterMoveSpeeds10[0] = 0;
        yHaterMoveSpeeds10[1] = 0;

        haters[10] = new Hater((ImageView) findViewById(R.id.hater5_10));
        haters[10].setImageX(thePath10[0].getImageX());
        haters[10].setImageY(thePath10[0].getImageY());
        haters[10].setPath(thePath10, xHaterMoveSpeeds10, yHaterMoveSpeeds10);

        GridImageThing[] thePath11 = new GridImageThing[2];
        thePath11[0] = daGrid[20][1];
        thePath11[1] = daGrid[23][1];

        float[] xHaterMoveSpeeds11 = new float[2];
        xHaterMoveSpeeds11[0] = (float)0.25;
        xHaterMoveSpeeds11[1] = (float)0.25;

        float[] yHaterMoveSpeeds11 = new float[2];
        yHaterMoveSpeeds11[0] = 0;
        yHaterMoveSpeeds11[1] = 0;

        haters[11] = new Hater((ImageView) findViewById(R.id.hater5_11));
        haters[11].setImageX(thePath11[0].getImageX());
        haters[11].setImageY(thePath11[0].getImageY());
        haters[11].setPath(thePath11, xHaterMoveSpeeds11, yHaterMoveSpeeds11);

        GridImageThing[] thePath12 = new GridImageThing[1];
        thePath12[0] = daGrid[26][5];

        float[] xHaterMoveSpeeds12 = new float[1];
        xHaterMoveSpeeds12[0] = 0;

        float[] yHaterMoveSpeeds12 = new float[1];
        yHaterMoveSpeeds12[0] = 0;

        haters[12] = new Hater((ImageView) findViewById(R.id.hater5_12));
        haters[12].setImageX(thePath12[0].getImageX());
        haters[12].setImageY(thePath12[0].getImageY());
        haters[12].setPath(thePath12, xHaterMoveSpeeds12, yHaterMoveSpeeds12);

        GridImageThing[] thePath13 = new GridImageThing[1];
        thePath13[0] = daGrid[27][5];

        float[] xHaterMoveSpeeds13 = new float[1];
        xHaterMoveSpeeds13[0] = 0;

        float[] yHaterMoveSpeeds13 = new float[1];
        yHaterMoveSpeeds13[0] = 0;

        haters[13] = new Hater((ImageView) findViewById(R.id.hater5_13));
        haters[13].setImageX(thePath13[0].getImageX());
        haters[13].setImageY(thePath13[0].getImageY());
        haters[13].setPath(thePath13, xHaterMoveSpeeds13, yHaterMoveSpeeds13);

        GridImageThing[] thePath14 = new GridImageThing[1];
        thePath14[0] = daGrid[28][5];

        float[] xHaterMoveSpeeds14 = new float[1];
        xHaterMoveSpeeds14[0] = 0;

        float[] yHaterMoveSpeeds14 = new float[1];
        yHaterMoveSpeeds14[0] = 0;

        haters[14] = new Hater((ImageView) findViewById(R.id.hater5_14));
        haters[14].setImageX(thePath14[0].getImageX());
        haters[14].setImageY(thePath14[0].getImageY());
        haters[14].setPath(thePath14, xHaterMoveSpeeds14, yHaterMoveSpeeds14);

        GridImageThing[] thePath15 = new GridImageThing[1];
        thePath15[0] = daGrid[29][5];

        float[] xHaterMoveSpeeds15 = new float[1];
        xHaterMoveSpeeds15[0] = 0;

        float[] yHaterMoveSpeeds15 = new float[1];
        yHaterMoveSpeeds15[0] = 0;

        haters[15] = new Hater((ImageView) findViewById(R.id.hater5_15));
        haters[15].setImageX(thePath15[0].getImageX());
        haters[15].setImageY(thePath15[0].getImageY());
        haters[15].setPath(thePath15, xHaterMoveSpeeds15, yHaterMoveSpeeds15);

        GridImageThing[] thePath16 = new GridImageThing[1];
        thePath16[0] = daGrid[30][5];

        float[] xHaterMoveSpeeds16 = new float[1];
        xHaterMoveSpeeds16[0] = 0;

        float[] yHaterMoveSpeeds16 = new float[1];
        yHaterMoveSpeeds16[0] = 0;

        haters[16] = new Hater((ImageView) findViewById(R.id.hater5_16));
        haters[16].setImageX(thePath16[0].getImageX());
        haters[16].setImageY(thePath16[0].getImageY());
        haters[16].setPath(thePath16, xHaterMoveSpeeds16, yHaterMoveSpeeds16);

        GridImageThing[] thePath17 = new GridImageThing[1];
        thePath17[0] = daGrid[31][5];

        float[] xHaterMoveSpeeds17 = new float[1];
        xHaterMoveSpeeds17[0] = 0;

        float[] yHaterMoveSpeeds17 = new float[1];
        yHaterMoveSpeeds17[0] = 0;

        haters[17] = new Hater((ImageView) findViewById(R.id.hater5_17));
        haters[17].setImageX(thePath17[0].getImageX());
        haters[17].setImageY(thePath17[0].getImageY());
        haters[17].setPath(thePath17, xHaterMoveSpeeds17, yHaterMoveSpeeds17);

        GridImageThing[] thePath18 = new GridImageThing[1];
        thePath18[0] = daGrid[32][5];

        float[] xHaterMoveSpeeds18 = new float[1];
        xHaterMoveSpeeds18[0] = 0;

        float[] yHaterMoveSpeeds18 = new float[1];
        yHaterMoveSpeeds18[0] = 0;

        haters[18] = new Hater((ImageView) findViewById(R.id.hater5_18));
        haters[18].setImageX(thePath18[0].getImageX());
        haters[18].setImageY(thePath18[0].getImageY());
        haters[18].setPath(thePath18, xHaterMoveSpeeds18, yHaterMoveSpeeds18);

        GridImageThing[] thePath19 = new GridImageThing[1];
        thePath19[0] = daGrid[33][5];

        float[] xHaterMoveSpeeds19 = new float[1];
        xHaterMoveSpeeds19[0] = 0;

        float[] yHaterMoveSpeeds19 = new float[1];
        yHaterMoveSpeeds19[0] = 0;

        haters[19] = new Hater((ImageView) findViewById(R.id.hater5_19));
        haters[19].setImageX(thePath19[0].getImageX());
        haters[19].setImageY(thePath19[0].getImageY());
        haters[19].setPath(thePath19, xHaterMoveSpeeds19, yHaterMoveSpeeds19);

        GridImageThing[] thePath20 = new GridImageThing[2];
        thePath20[0] = daGrid[34][0];
        thePath20[1] = daGrid[34][3];

        float[] xHaterMoveSpeeds20 = new float[2];
        xHaterMoveSpeeds20[0] = 0;
        xHaterMoveSpeeds20[1] = 0;

        float[] yHaterMoveSpeeds20 = new float[2];
        yHaterMoveSpeeds20[0] = 1;
        yHaterMoveSpeeds20[1] = 1;

        haters[20] = new Hater((ImageView) findViewById(R.id.hater5_20));
        haters[20].setImageX(thePath20[0].getImageX());
        haters[20].setImageY(thePath20[0].getImageY());
        haters[20].setPath(thePath20, xHaterMoveSpeeds20, yHaterMoveSpeeds20);

        GridImageThing[] thePath21 = new GridImageThing[1];
        thePath21[0] = daGrid[34][5];

        float[] xHaterMoveSpeeds21 = new float[1];
        xHaterMoveSpeeds21[0] = 0;

        float[] yHaterMoveSpeeds21 = new float[1];
        yHaterMoveSpeeds21[0] = 0;

        haters[21] = new Hater((ImageView) findViewById(R.id.hater5_21));
        haters[21].setImageX(thePath21[0].getImageX());
        haters[21].setImageY(thePath21[0].getImageY());
        haters[21].setPath(thePath21, xHaterMoveSpeeds21, yHaterMoveSpeeds21);

        GridImageThing[] thePath22 = new GridImageThing[1];
        thePath22[0] = daGrid[35][5];

        float[] xHaterMoveSpeeds22 = new float[1];
        xHaterMoveSpeeds22[0] = 0;

        float[] yHaterMoveSpeeds22 = new float[1];
        yHaterMoveSpeeds22[0] = 0;

        haters[22] = new Hater((ImageView) findViewById(R.id.hater5_22));
        haters[22].setImageX(thePath22[0].getImageX());
        haters[22].setImageY(thePath22[0].getImageY());
        haters[22].setPath(thePath22, xHaterMoveSpeeds22, yHaterMoveSpeeds22);

        GridImageThing[] thePath23 = new GridImageThing[1];
        thePath23[0] = daGrid[36][5];

        float[] xHaterMoveSpeeds23 = new float[1];
        xHaterMoveSpeeds23[0] = 0;

        float[] yHaterMoveSpeeds23 = new float[1];
        yHaterMoveSpeeds23[0] = 0;

        haters[23] = new Hater((ImageView) findViewById(R.id.hater5_23));
        haters[23].setImageX(thePath23[0].getImageX());
        haters[23].setImageY(thePath23[0].getImageY());
        haters[23].setPath(thePath23, xHaterMoveSpeeds23, yHaterMoveSpeeds23);

        GridImageThing[] thePath24 = new GridImageThing[1];
        thePath24[0] = daGrid[37][5];

        float[] xHaterMoveSpeeds24 = new float[1];
        xHaterMoveSpeeds24[0] = 0;

        float[] yHaterMoveSpeeds24 = new float[1];
        yHaterMoveSpeeds24[0] = 0;

        haters[24] = new Hater((ImageView) findViewById(R.id.hater5_24));
        haters[24].setImageX(thePath24[0].getImageX());
        haters[24].setImageY(thePath24[0].getImageY());
        haters[24].setPath(thePath24, xHaterMoveSpeeds24, yHaterMoveSpeeds24);

        GridImageThing[] thePath25 = new GridImageThing[1];
        thePath25[0] = daGrid[38][5];

        float[] xHaterMoveSpeeds25 = new float[1];
        xHaterMoveSpeeds25[0] = 0;

        float[] yHaterMoveSpeeds25 = new float[1];
        yHaterMoveSpeeds25[0] = 0;

        haters[25] = new Hater((ImageView) findViewById(R.id.hater5_25));
        haters[25].setImageX(thePath25[0].getImageX());
        haters[25].setImageY(thePath25[0].getImageY());
        haters[25].setPath(thePath25, xHaterMoveSpeeds25, yHaterMoveSpeeds25);

        GridImageThing[] thePath26 = new GridImageThing[1];
        thePath26[0] = daGrid[39][5];

        float[] xHaterMoveSpeeds26 = new float[1];
        xHaterMoveSpeeds26[0] = 0;

        float[] yHaterMoveSpeeds26 = new float[1];
        yHaterMoveSpeeds26[0] = 0;

        haters[26] = new Hater((ImageView) findViewById(R.id.hater5_26));
        haters[26].setImageX(thePath26[0].getImageX());
        haters[26].setImageY(thePath26[0].getImageY());
        haters[26].setPath(thePath26, xHaterMoveSpeeds26, yHaterMoveSpeeds26);

        GridImageThing[] thePath27 = new GridImageThing[1];
        thePath27[0] = daGrid[40][5];

        float[] xHaterMoveSpeeds27 = new float[1];
        xHaterMoveSpeeds27[0] = 0;

        float[] yHaterMoveSpeeds27 = new float[1];
        yHaterMoveSpeeds27[0] = 0;

        haters[27] = new Hater((ImageView) findViewById(R.id.hater5_27));
        haters[27].setImageX(thePath27[0].getImageX());
        haters[27].setImageY(thePath27[0].getImageY());
        haters[27].setPath(thePath27, xHaterMoveSpeeds27, yHaterMoveSpeeds27);

        GridImageThing[] thePath28 = new GridImageThing[1];
        thePath28[0] = daGrid[41][5];

        float[] xHaterMoveSpeeds28 = new float[1];
        xHaterMoveSpeeds28[0] = 0;

        float[] yHaterMoveSpeeds28 = new float[1];
        yHaterMoveSpeeds28[0] = 0;

        haters[28] = new Hater((ImageView) findViewById(R.id.hater5_28));
        haters[28].setImageX(thePath28[0].getImageX());
        haters[28].setImageY(thePath28[0].getImageY());
        haters[28].setPath(thePath28, xHaterMoveSpeeds28, yHaterMoveSpeeds28);

        GridImageThing[] thePath29 = new GridImageThing[1];
        thePath29[0] = daGrid[42][5];

        float[] xHaterMoveSpeeds29 = new float[1];
        xHaterMoveSpeeds29[0] = 0;

        float[] yHaterMoveSpeeds29 = new float[1];
        yHaterMoveSpeeds29[0] = 0;

        haters[29] = new Hater((ImageView) findViewById(R.id.hater5_29));
        haters[29].setImageX(thePath29[0].getImageX());
        haters[29].setImageY(thePath29[0].getImageY());
        haters[29].setPath(thePath29, xHaterMoveSpeeds29, yHaterMoveSpeeds29);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}