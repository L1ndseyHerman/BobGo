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

public class LevelFourActivity extends AppCompatActivity
{

    private GridImageThing[][] daGrid = new GridImageThing[85][6];
    private Hater[] haters = new Hater[5];
    private Coin[] coins = new Coin[2];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_four);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bob4);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob4_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob4_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob4_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob4_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob4_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob4_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob4_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle4));
        gameLogic.setWinCircleLogic(winCircle, daGrid[83][3]);

        TextView scoreText = findViewById(R.id.scoreText4);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton4);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelFourHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton4);
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
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_12x3));
        daGrid[12][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_12x4));
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_12x5));

        daGrid[13][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x0));
        daGrid[13][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x1));
        daGrid[13][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_13x3));
        daGrid[13][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x4));
        daGrid[13][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_13x5));

        daGrid[14][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x0));
        daGrid[14][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x1));
        daGrid[14][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_14x3));
        daGrid[14][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x4));
        daGrid[14][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_14x5));

        daGrid[15][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x0));
        daGrid[15][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x1));
        daGrid[15][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_15x3));
        daGrid[15][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x4));
        daGrid[15][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_16x3));
        daGrid[16][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_16x4));
        daGrid[16][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x4));
        daGrid[17][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x3));
        daGrid[18][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x4));
        daGrid[18][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x3));
        daGrid[19][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_19x4));
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_19x5));

        daGrid[20][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x0));
        daGrid[20][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x1));
        daGrid[20][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_20x3));
        daGrid[20][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_20x4));
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_20x5));

        daGrid[21][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x0));
        daGrid[21][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x1));
        daGrid[21][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_21x3));
        daGrid[21][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_21x4));
        daGrid[21][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_21x5));

        daGrid[22][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_22x0));
        daGrid[22][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_22x1));
        daGrid[22][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_22x3));
        daGrid[22][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_22x4));
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_22x5));

        daGrid[23][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_23x0));
        daGrid[23][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_23x1));
        daGrid[23][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_23x3));
        daGrid[23][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_23x4));
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_23x5));

        daGrid[24][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_24x0));
        daGrid[24][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_24x1));
        daGrid[24][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_24x3));
        daGrid[24][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_24x5));

        daGrid[25][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_25x0));
        daGrid[25][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_25x1));
        daGrid[25][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_25x3));
        daGrid[25][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_25x4));
        daGrid[25][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_25x5));

        daGrid[26][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_26x0));
        daGrid[26][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_26x1));
        daGrid[26][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_26x2));
        daGrid[26][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_26x3));
        daGrid[26][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_26x4));
        daGrid[26][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_26x5));

        daGrid[27][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_27x0));
        daGrid[27][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_27x1));
        daGrid[27][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_27x3));
        daGrid[27][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_27x4));
        daGrid[27][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_27x5));

        daGrid[28][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_28x0));
        daGrid[28][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_28x1));
        daGrid[28][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_28x2));
        daGrid[28][3] = new SquareObstacle((ImageView) findViewById(R.id.grid4_28x3));
        daGrid[28][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x4));
        daGrid[30][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_32x3));
        daGrid[32][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_32x4));
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_32x5));

        daGrid[33][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_33x0));
        daGrid[33][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_33x1));
        daGrid[33][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_33x3));
        daGrid[33][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_33x4));
        daGrid[33][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_33x5));

        daGrid[34][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_34x0));
        daGrid[34][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_34x1));
        daGrid[34][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_34x3));
        daGrid[34][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_34x4));
        daGrid[34][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_34x5));

        daGrid[35][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_35x0));
        daGrid[35][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_35x1));
        daGrid[35][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_35x3));
        daGrid[35][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_35x5));

        daGrid[36][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_36x0));
        daGrid[36][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_36x1));
        daGrid[36][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_36x3));
        daGrid[36][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_36x4));
        daGrid[36][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_36x5));

        daGrid[37][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_37x0));
        daGrid[37][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_37x1));
        daGrid[37][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_37x3));
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_37x4));
        daGrid[37][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_37x5));

        daGrid[38][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_38x0));
        daGrid[38][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_38x1));
        daGrid[38][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_38x3));
        daGrid[38][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_38x4));
        daGrid[38][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_38x5));

        daGrid[39][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_39x0));
        daGrid[39][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_39x1));
        daGrid[39][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_39x3));
        daGrid[39][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_39x5));

        daGrid[40][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_40x0));
        daGrid[40][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_40x1));
        daGrid[40][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_40x3));
        daGrid[40][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_40x5));

        daGrid[41][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_41x0));
        daGrid[41][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_41x1));
        daGrid[41][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_41x3));
        daGrid[41][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_41x5));

        daGrid[42][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_42x0));
        daGrid[42][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_42x1));
        daGrid[42][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_42x3));
        daGrid[42][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_42x4));
        daGrid[42][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_43x3));
        daGrid[43][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_43x4));
        daGrid[43][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_44x3));
        daGrid[44][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_44x4));
        daGrid[44][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_45x1));
        daGrid[45][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_45x3));
        daGrid[45][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_45x4));
        daGrid[45][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_45x5));

        daGrid[46][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_46x0));
        daGrid[46][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_46x1));
        daGrid[46][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_46x2));
        daGrid[46][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_46x3));
        daGrid[46][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_46x4));
        daGrid[46][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_46x5));

        daGrid[47][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_47x0));
        daGrid[47][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_47x1));
        daGrid[47][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_47x2));
        daGrid[47][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_47x3));
        daGrid[47][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_47x4));
        daGrid[47][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_47x5));

        daGrid[48][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_48x0));
        daGrid[48][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_48x1));
        daGrid[48][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_48x2));
        daGrid[48][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_48x3));
        daGrid[48][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_48x4));
        daGrid[48][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_48x5));

        daGrid[49][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_49x0));
        daGrid[49][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_49x1));
        daGrid[49][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_49x2));
        daGrid[49][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_49x3));
        daGrid[49][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_49x4));
        daGrid[49][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_49x5));

        daGrid[50][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_50x0));
        daGrid[50][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_50x1));
        daGrid[50][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_50x2));
        daGrid[50][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_50x3));
        daGrid[50][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_50x4));
        daGrid[50][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_50x5));

        daGrid[51][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_51x0));
        daGrid[51][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_51x1));
        daGrid[51][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_51x2));
        daGrid[51][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_51x3));
        daGrid[51][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_51x4));
        daGrid[51][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_51x5));

        daGrid[52][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_52x0));
        daGrid[52][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_52x1));
        daGrid[52][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_52x2));
        daGrid[52][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_52x3));
        daGrid[52][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_52x4));
        daGrid[52][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_52x5));

        daGrid[53][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_53x0));
        daGrid[53][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_53x1));
        daGrid[53][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_53x2));
        daGrid[53][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_53x3));
        daGrid[53][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_53x4));
        daGrid[53][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_53x5));

        daGrid[54][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_54x0));
        daGrid[54][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_54x1));
        daGrid[54][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_54x2));
        daGrid[54][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_54x3));
        daGrid[54][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_54x4));
        daGrid[54][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_54x5));

        daGrid[55][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_55x0));
        daGrid[55][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_55x1));
        daGrid[55][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_55x2));
        daGrid[55][3] = new SquareObstacle((ImageView) findViewById(R.id.grid4_55x3));
        daGrid[55][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_55x4));
        daGrid[55][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_55x5));

        daGrid[56][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_56x0));
        daGrid[56][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_56x1));
        daGrid[56][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_56x2));
        daGrid[56][3] = new SquareObstacle((ImageView) findViewById(R.id.grid4_56x3));
        daGrid[56][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_56x4));
        daGrid[56][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_56x5));

        daGrid[57][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_57x0));
        daGrid[57][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_57x1));
        daGrid[57][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_57x2));
        daGrid[57][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_57x3));
        daGrid[57][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_57x4));
        daGrid[57][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_57x5));

        daGrid[58][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_58x0));
        daGrid[58][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_58x1));
        daGrid[58][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_58x2));
        daGrid[58][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_58x3));
        daGrid[58][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_58x4));
        daGrid[58][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_58x5));

        daGrid[59][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_59x0));
        daGrid[59][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_59x1));
        daGrid[59][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_59x2));
        daGrid[59][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_59x3));
        daGrid[59][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_59x4));
        daGrid[59][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_59x5));

        daGrid[60][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_60x0));
        daGrid[60][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_60x1));
        daGrid[60][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_60x2));
        daGrid[60][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_60x3));
        daGrid[60][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_60x4));
        daGrid[60][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_60x5));

        daGrid[61][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_61x0));
        daGrid[61][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_61x1));
        daGrid[61][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_61x2));
        daGrid[61][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_61x3));
        daGrid[61][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_61x4));
        daGrid[61][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_61x5));

        daGrid[62][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_62x0));
        daGrid[62][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_62x1));
        daGrid[62][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_62x2));
        daGrid[62][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_62x3));
        daGrid[62][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_62x4));
        daGrid[62][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_62x5));

        daGrid[63][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_63x0));
        daGrid[63][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_63x1));
        daGrid[63][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_63x2));
        daGrid[63][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_63x3));
        daGrid[63][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_63x4));
        daGrid[63][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_63x5));

        daGrid[64][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_64x0));
        daGrid[64][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_64x1));
        daGrid[64][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_64x2));
        daGrid[64][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_64x3));
        daGrid[64][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_64x4));
        daGrid[64][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_64x5));

        daGrid[65][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_65x0));
        daGrid[65][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_65x1));
        daGrid[65][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_65x2));
        daGrid[65][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_65x3));
        daGrid[65][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_65x4));
        daGrid[65][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_65x5));

        daGrid[66][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_66x0));
        daGrid[66][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_66x1));
        daGrid[66][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_66x2));
        daGrid[66][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_66x3));
        daGrid[66][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_66x4));
        daGrid[66][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_66x5));

        daGrid[67][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_67x0));
        daGrid[67][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_67x1));
        daGrid[67][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_67x2));
        daGrid[67][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_67x3));
        daGrid[67][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_67x4));
        daGrid[67][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_67x5));

        daGrid[68][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_68x0));
        daGrid[68][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_68x1));
        daGrid[68][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_68x2));
        daGrid[68][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_68x3));
        daGrid[68][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_68x4));
        daGrid[68][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_68x5));

        daGrid[69][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_69x0));
        daGrid[69][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_69x1));
        daGrid[69][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_69x2));
        daGrid[69][3] = new SquareObstacle((ImageView) findViewById(R.id.grid4_69x3));
        daGrid[69][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_69x4));
        daGrid[69][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_69x5));

        daGrid[70][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_70x0));
        daGrid[70][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_70x1));
        daGrid[70][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_70x2));
        daGrid[70][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_70x3));
        daGrid[70][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_70x4));
        daGrid[70][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_70x5));

        daGrid[71][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_71x0));
        daGrid[71][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_71x1));
        daGrid[71][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_71x2));
        daGrid[71][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_71x3));
        daGrid[71][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_71x4));
        daGrid[71][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_71x5));

        daGrid[72][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x0));
        daGrid[72][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x1));
        daGrid[72][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x2));
        daGrid[72][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x3));
        daGrid[72][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x4));
        daGrid[72][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_72x5));

        daGrid[73][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x0));
        daGrid[73][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x1));
        daGrid[73][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x2));
        daGrid[73][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x3));
        daGrid[73][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x4));
        daGrid[73][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_73x5));

        daGrid[74][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_74x0));
        daGrid[74][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_74x1));
        daGrid[74][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_74x2));
        daGrid[74][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_74x3));
        daGrid[74][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_74x4));
        daGrid[74][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_74x5));

        daGrid[75][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_75x0));
        daGrid[75][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_75x1));
        daGrid[75][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_75x2));
        daGrid[75][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_75x3));
        daGrid[75][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_75x4));
        daGrid[75][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_75x5));

        daGrid[76][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_76x0));
        daGrid[76][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_76x1));
        daGrid[76][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_76x2));
        daGrid[76][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_76x3));
        daGrid[76][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_76x4));
        daGrid[76][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_76x5));

        daGrid[77][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_77x0));
        daGrid[77][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_77x1));
        daGrid[77][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_77x2));
        daGrid[77][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_77x3));
        daGrid[77][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_77x4));
        daGrid[77][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_77x5));

        daGrid[78][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_78x0));
        daGrid[78][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_78x1));
        daGrid[78][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_78x2));
        daGrid[78][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_78x3));
        daGrid[78][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_78x4));
        daGrid[78][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_78x5));

        daGrid[79][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_79x0));
        daGrid[79][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_79x1));
        daGrid[79][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_79x2));
        daGrid[79][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_79x3));
        daGrid[79][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_79x4));
        daGrid[79][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_79x5));

        daGrid[80][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_80x0));
        daGrid[80][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_80x1));
        daGrid[80][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_80x2));
        daGrid[80][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_80x3));
        daGrid[80][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_80x4));
        daGrid[80][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_80x5));

        daGrid[81][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_81x0));
        daGrid[81][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_81x1));
        daGrid[81][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_81x2));
        daGrid[81][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_81x3));
        daGrid[81][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_81x4));
        daGrid[81][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_81x5));

        daGrid[82][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_82x0));
        daGrid[82][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_82x1));
        daGrid[82][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_82x2));
        daGrid[82][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_82x3));
        daGrid[82][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_82x4));
        daGrid[82][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_82x5));

        daGrid[83][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_83x0));
        daGrid[83][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_83x1));
        daGrid[83][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_83x2));
        daGrid[83][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_83x3));
        daGrid[83][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_83x4));
        daGrid[83][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid4_83x5));

        daGrid[84][0] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x0));
        daGrid[84][1] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x1));
        daGrid[84][2] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x2));
        daGrid[84][3] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x3));
        daGrid[84][4] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x4));
        daGrid[84][5] = new SquareObstacle((ImageView) findViewById(R.id.grid4_84x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin4_0));
        coins[0].setImageX(daGrid[12][0].getImageX());
        coins[0].setImageY(daGrid[12][0].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin4_1));
        coins[1].setImageX(daGrid[12][3].getImageX());
        coins[1].setImageY(daGrid[12][3].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[1];
        thePath0[0] = daGrid[27][3];

        float[] xHaterMoveSpeeds0 = new float[1];
        xHaterMoveSpeeds0[0] = 0;

        float[] yHaterMoveSpeeds0 = new float[1];
        yHaterMoveSpeeds0[0] = 0;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater4_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[1];
        thePath1[0] = daGrid[41][5];

        float[] xHaterMoveSpeeds1 = new float[1];
        xHaterMoveSpeeds1[0] = 0;

        float[] yHaterMoveSpeeds1 = new float[1];
        yHaterMoveSpeeds1[0] = 0;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater4_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[1];
        thePath2[0] = daGrid[54][3];

        float[] xHaterMoveSpeeds2 = new float[1];
        xHaterMoveSpeeds2[0] = 0;

        float[] yHaterMoveSpeeds2 = new float[1];
        yHaterMoveSpeeds2[0] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater4_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[1];
        thePath3[0] = daGrid[68][3];

        float[] xHaterMoveSpeeds3 = new float[1];
        xHaterMoveSpeeds3[0] = 0;

        float[] yHaterMoveSpeeds3 = new float[1];
        yHaterMoveSpeeds3[0] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater4_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[1];
        thePath4[0] = daGrid[83][5];

        float[] xHaterMoveSpeeds4 = new float[1];
        xHaterMoveSpeeds4[0] = 0;

        float[] yHaterMoveSpeeds4 = new float[1];
        yHaterMoveSpeeds4[0] = 0;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater4_4));
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