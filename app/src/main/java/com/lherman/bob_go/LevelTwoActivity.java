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

public class LevelTwoActivity extends AppCompatActivity
{
    private GridImageThing[][] daGrid = new GridImageThing[47][6];
    private Hater[] haters = new Hater[7];
    private Coin[] coins = new Coin[3];
    private Button beginButton;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        gameLogic = new GameLogic();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        gameLogic.setScreenWidth(screenWidth);
        gameLogic.setScreenHeight(screenHeight);

        gameLogic.setXMoveSpeedScreen(screenWidth /168);

        ImageView bobImage = findViewById(R.id.bob2);
        gameLogic.setBobLogic(bobImage);

        ImageView endBobImage0 = findViewById(R.id.winLooseBob2_0);
        gameLogic.setEndBobImage0(endBobImage0);

        ImageView badEndBobImage1 = findViewById(R.id.looseBob2_1);
        gameLogic.setBadEndBobImage1(badEndBobImage1);
        ImageView badEndBobImage2 = findViewById(R.id.looseBob2_2);
        gameLogic.setBadEndBobImage2(badEndBobImage2);
        ImageView badEndBobImage3 = findViewById(R.id.looseBob2_3);
        gameLogic.setBadEndBobImage3(badEndBobImage3);

        ImageView goodEndBobImage1 = findViewById(R.id.winBob2_1);
        gameLogic.setGoodEndBobImage1(goodEndBobImage1);
        ImageView goodEndBobImage2 = findViewById(R.id.winBob2_2);
        gameLogic.setGoodEndBobImage2(goodEndBobImage2);
        ImageView goodEndBobImage3 = findViewById(R.id.winBob2_3);
        gameLogic.setGoodEndBobImage3(goodEndBobImage3);

        daGrid = placeGridImages(daGrid);
        gameLogic.setDaGridLogic(daGrid);

        WinCircle winCircle = new WinCircle((ImageView) findViewById(R.id.winCircle2));
        gameLogic.setWinCircleLogic(winCircle, daGrid[45][5]);

        TextView scoreText = findViewById(R.id.scoreText2);
        gameLogic.setScoreLogic(scoreText);

        coins = placeCoins(coins);
        gameLogic.setCoinLogic(coins);

        haters = placeEnemies(haters);
        gameLogic.setHaterLogic(haters);

        beginButton = findViewById(R.id.startButton2);
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                beginButton.setVisibility(View.INVISIBLE);

                gameLogic.setLevelHighScoreKey("levelTwoHighScore");
                SharedPreferences sharedPrefReturn = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setTheSavedPreference(sharedPrefReturn);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                gameLogic.setThePreferenceImSaving(sharedPref);

                gameLogic.setLevelTimerLogic();
            }
        });

        Button endButton = findViewById(R.id.endButton2);
        gameLogic.setEndButtonLogic(endButton);
        endButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent startIntent = new Intent(getApplicationContext(), LevelSelectPageOneActivity.class);
                startActivity(startIntent);
            }
        });

    }

    public GridImageThing[][] placeGridImages(GridImageThing[][] daGrid)
    {
        daGrid[0][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x0));
        daGrid[0][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x1));
        daGrid[0][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x2));
        daGrid[0][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x3));
        daGrid[0][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x4));
        daGrid[0][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_0x5));

        daGrid[1][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x0));
        daGrid[1][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x1));
        daGrid[1][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x2));
        daGrid[1][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x3));
        daGrid[1][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x4));
        daGrid[1][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_1x5));

        daGrid[2][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x0));
        daGrid[2][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x1));
        daGrid[2][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x2));
        daGrid[2][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x3));
        daGrid[2][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x4));
        daGrid[2][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_2x5));

        daGrid[3][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x0));
        daGrid[3][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x1));
        daGrid[3][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x2));
        daGrid[3][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x3));
        daGrid[3][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x4));
        daGrid[3][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_3x5));

        daGrid[4][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x0));
        daGrid[4][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x1));
        daGrid[4][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x2));
        daGrid[4][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x3));
        daGrid[4][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x4));
        daGrid[4][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_4x5));

        daGrid[5][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x0));
        daGrid[5][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x1));
        daGrid[5][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x2));
        daGrid[5][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x3));
        daGrid[5][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x4));
        daGrid[5][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_5x5));

        daGrid[6][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x0));
        daGrid[6][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x1));
        daGrid[6][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x2));
        daGrid[6][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x3));
        daGrid[6][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x4));
        daGrid[6][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_6x5));

        daGrid[7][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x0));
        daGrid[7][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x1));
        daGrid[7][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x2));
        daGrid[7][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x3));
        daGrid[7][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x4));
        daGrid[7][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_7x5));

        daGrid[8][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x0));
        daGrid[8][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x1));
        daGrid[8][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x2));
        daGrid[8][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x3));
        daGrid[8][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x4));
        daGrid[8][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_8x5));

        daGrid[9][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x0));
        daGrid[9][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x1));
        daGrid[9][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x2));
        daGrid[9][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x3));
        daGrid[9][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x4));
        daGrid[9][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_9x5));

        daGrid[10][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x0));
        daGrid[10][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x1));
        daGrid[10][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x2));
        daGrid[10][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x3));
        daGrid[10][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x4));
        daGrid[10][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_10x5));

        daGrid[11][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x0));
        daGrid[11][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x1));
        daGrid[11][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x2));
        daGrid[11][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x3));
        daGrid[11][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x4));
        daGrid[11][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_11x5));

        daGrid[12][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x0));
        daGrid[12][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x1));
        daGrid[12][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x2));
        daGrid[12][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x3));
        daGrid[12][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_12x4));
        daGrid[12][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_12x5));

        daGrid[13][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x0));
        daGrid[13][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x1));
        daGrid[13][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x2));
        daGrid[13][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x3));
        daGrid[13][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x4));
        daGrid[13][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_13x5));

        daGrid[14][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x0));
        daGrid[14][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x1));
        daGrid[14][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x2));
        daGrid[14][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x3));
        daGrid[14][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x4));
        daGrid[14][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_14x5));

        daGrid[15][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x0));
        daGrid[15][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x1));
        daGrid[15][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x2));
        daGrid[15][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x3));
        daGrid[15][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x4));
        daGrid[15][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_15x5));

        daGrid[16][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x0));
        daGrid[16][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x1));
        daGrid[16][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x2));
        daGrid[16][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x3));
        daGrid[16][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x4));
        daGrid[16][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_16x5));

        daGrid[17][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x0));
        daGrid[17][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x1));
        daGrid[17][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x2));
        daGrid[17][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x3));
        daGrid[17][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_17x4));
        daGrid[17][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_17x5));

        daGrid[18][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x0));
        daGrid[18][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x1));
        daGrid[18][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x2));
        daGrid[18][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_18x3));
        daGrid[18][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_18x4));
        daGrid[18][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_18x5));

        daGrid[19][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x0));
        daGrid[19][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x1));
        daGrid[19][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x2));
        daGrid[19][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x3));
        daGrid[19][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x4));
        daGrid[19][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_19x5));

        daGrid[20][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x0));
        daGrid[20][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x1));
        daGrid[20][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x2));
        daGrid[20][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x3));
        daGrid[20][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x4));
        daGrid[20][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_20x5));

        daGrid[21][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_21x0));
        daGrid[21][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_21x1));
        daGrid[21][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_21x2));
        daGrid[21][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_21x3));
        daGrid[21][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_21x4));
        daGrid[21][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_21x5));

        daGrid[22][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x0));
        daGrid[22][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x1));
        daGrid[22][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x2));
        daGrid[22][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x3));
        daGrid[22][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x4));
        daGrid[22][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_22x5));

        daGrid[23][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x0));
        daGrid[23][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x1));
        daGrid[23][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x2));
        daGrid[23][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x3));
        daGrid[23][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x4));
        daGrid[23][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_23x5));

        daGrid[24][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x0));
        daGrid[24][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x1));
        daGrid[24][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x2));
        daGrid[24][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x3));
        daGrid[24][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x4));
        daGrid[24][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_24x5));

        daGrid[25][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_25x0));
        daGrid[25][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_25x1));
        daGrid[25][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_25x2));
        daGrid[25][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_25x3));
        daGrid[25][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_25x4));
        daGrid[25][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_25x5));

        daGrid[26][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_26x0));
        daGrid[26][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_26x1));
        daGrid[26][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_26x2));
        daGrid[26][3] = new SquareObstacle((ImageView) findViewById(R.id.grid2_26x3));
        daGrid[26][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_26x4));
        daGrid[26][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_26x5));

        daGrid[27][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x0));
        daGrid[27][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x1));
        daGrid[27][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x2));
        daGrid[27][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x3));
        daGrid[27][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x4));
        daGrid[27][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_27x5));

        daGrid[28][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x0));
        daGrid[28][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x1));
        daGrid[28][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x2));
        daGrid[28][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x3));
        daGrid[28][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x4));
        daGrid[28][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_28x5));

        daGrid[29][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x0));
        daGrid[29][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x1));
        daGrid[29][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x2));
        daGrid[29][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x3));
        daGrid[29][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x4));
        daGrid[29][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_29x5));

        daGrid[30][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x0));
        daGrid[30][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x1));
        daGrid[30][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x2));
        daGrid[30][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x3));
        daGrid[30][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x4));
        daGrid[30][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_30x5));

        daGrid[31][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x0));
        daGrid[31][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x1));
        daGrid[31][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x2));
        daGrid[31][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x3));
        daGrid[31][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x4));
        daGrid[31][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_31x5));

        daGrid[32][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x0));
        daGrid[32][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x1));
        daGrid[32][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x2));
        daGrid[32][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x3));
        daGrid[32][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x4));
        daGrid[32][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_32x5));

        daGrid[33][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_33x0));
        daGrid[33][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_33x1));
        daGrid[33][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_33x2));
        daGrid[33][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_33x3));
        daGrid[33][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_33x4));
        daGrid[33][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_33x5));

        daGrid[34][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_34x0));
        daGrid[34][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_34x1));
        daGrid[34][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_34x2));
        daGrid[34][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_34x3));
        daGrid[34][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_34x4));
        daGrid[34][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_34x5));

        daGrid[35][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x0));
        daGrid[35][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x1));
        daGrid[35][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x2));
        daGrid[35][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x3));
        daGrid[35][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x4));
        daGrid[35][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_35x5));

        daGrid[36][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x0));
        daGrid[36][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x1));
        daGrid[36][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x2));
        daGrid[36][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x3));
        daGrid[36][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x4));
        daGrid[36][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_36x5));

        daGrid[37][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_37x0));
        daGrid[37][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_37x1));
        daGrid[37][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_37x2));
        daGrid[37][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_37x3));
        daGrid[37][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_37x4));
        daGrid[37][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_37x5));

        daGrid[38][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x0));
        daGrid[38][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x1));
        daGrid[38][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x2));
        daGrid[38][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x3));
        daGrid[38][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x4));
        daGrid[38][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_38x5));

        daGrid[39][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x0));
        daGrid[39][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x1));
        daGrid[39][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x2));
        daGrid[39][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x3));
        daGrid[39][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x4));
        daGrid[39][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_39x5));

        daGrid[40][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_40x0));
        daGrid[40][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_40x1));
        daGrid[40][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_40x2));
        daGrid[40][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_40x3));
        daGrid[40][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_40x4));
        daGrid[40][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_40x5));

        daGrid[41][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x0));
        daGrid[41][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x1));
        daGrid[41][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x2));
        daGrid[41][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x3));
        daGrid[41][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x4));
        daGrid[41][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_41x5));

        daGrid[42][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_42x0));
        daGrid[42][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_42x1));
        daGrid[42][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_42x2));
        daGrid[42][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_42x3));
        daGrid[42][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_42x4));
        daGrid[42][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_42x5));

        daGrid[43][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_43x0));
        daGrid[43][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_43x1));
        daGrid[43][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_43x2));
        daGrid[43][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_43x3));
        daGrid[43][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_43x4));
        daGrid[43][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_43x5));

        daGrid[44][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x0));
        daGrid[44][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x1));
        daGrid[44][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x2));
        daGrid[44][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x3));
        daGrid[44][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x4));
        daGrid[44][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_44x5));

        daGrid[45][0] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x0));
        daGrid[45][1] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x1));
        daGrid[45][2] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x2));
        daGrid[45][3] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x3));
        daGrid[45][4] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x4));
        daGrid[45][5] = new BlankGridSpace((ImageView) findViewById(R.id.grid2_45x5));

        daGrid[46][0] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x0));
        daGrid[46][1] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x1));
        daGrid[46][2] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x2));
        daGrid[46][3] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x3));
        daGrid[46][4] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x4));
        daGrid[46][5] = new SquareObstacle((ImageView) findViewById(R.id.grid2_46x5));

        return daGrid;
    }

    public Coin[] placeCoins(Coin[] coins)
    {
        coins[0] = new Coin((ImageView) findViewById(R.id.coin2_0));
        coins[0].setImageX(daGrid[20][5].getImageX());
        coins[0].setImageY(daGrid[20][5].getImageY());

        coins[1] = new Coin((ImageView) findViewById(R.id.coin2_1));
        coins[1].setImageX(daGrid[38][5].getImageX());
        coins[1].setImageY(daGrid[38][5].getImageY());

        coins[2] = new Coin((ImageView) findViewById(R.id.coin2_2));
        coins[2].setImageX(daGrid[43][3].getImageX());
        coins[2].setImageY(daGrid[43][3].getImageY());

        return coins;
    }

    public Hater[] placeEnemies(Hater[] haters)
    {
        GridImageThing[] thePath0 = new GridImageThing[2];
        thePath0[0] = daGrid[13][0];
        thePath0[1] = daGrid[13][5];

        float[] xHaterMoveSpeeds0 = new float[2];
        xHaterMoveSpeeds0[0] = 0;
        xHaterMoveSpeeds0[1] = 0;

        float[] yHaterMoveSpeeds0 = new float[2];
        yHaterMoveSpeeds0[0] = (float)0.5;
        yHaterMoveSpeeds0[1] = (float)0.5;

        haters[0] = new Hater((ImageView) findViewById(R.id.hater2_0));
        haters[0].setImageX(thePath0[0].getImageX());
        haters[0].setImageY(thePath0[0].getImageY());
        haters[0].setPath(thePath0, xHaterMoveSpeeds0, yHaterMoveSpeeds0);

        GridImageThing[] thePath1 = new GridImageThing[2];
        thePath1[0] = daGrid[14][0];
        thePath1[1] = daGrid[14][5];

        float[] xHaterMoveSpeeds1 = new float[2];
        xHaterMoveSpeeds1[0] = 0;
        xHaterMoveSpeeds1[1] = 0;

        float[] yHaterMoveSpeeds1 = new float[2];
        yHaterMoveSpeeds1[0] = 1;
        yHaterMoveSpeeds1[1] = 1;

        haters[1] = new Hater((ImageView) findViewById(R.id.hater2_1));
        haters[1].setImageX(thePath1[0].getImageX());
        haters[1].setImageY(thePath1[0].getImageY());
        haters[1].setPath(thePath1, xHaterMoveSpeeds1, yHaterMoveSpeeds1);

        GridImageThing[] thePath2 = new GridImageThing[2];
        thePath2[0] = daGrid[19][4];
        thePath2[1] = daGrid[24][4];

        float[] xHaterMoveSpeeds2 = new float[2];
        xHaterMoveSpeeds2[0] = (float)0.25;
        xHaterMoveSpeeds2[1] = (float)0.25;

        float[] yHaterMoveSpeeds2 = new float[2];
        yHaterMoveSpeeds2[0] = 0;
        yHaterMoveSpeeds2[1] = 0;

        haters[2] = new Hater((ImageView) findViewById(R.id.hater2_2));
        haters[2].setImageX(thePath2[0].getImageX());
        haters[2].setImageY(thePath2[0].getImageY());
        haters[2].setPath(thePath2, xHaterMoveSpeeds2, yHaterMoveSpeeds2);

        GridImageThing[] thePath3 = new GridImageThing[2];
        thePath3[0] = daGrid[19][5];
        thePath3[1] = daGrid[24][5];

        float[] xHaterMoveSpeeds3 = new float[2];
        xHaterMoveSpeeds3[0] = (float)0.66;
        xHaterMoveSpeeds3[1] = (float)0.66;

        float[] yHaterMoveSpeeds3 = new float[2];
        yHaterMoveSpeeds3[0] = 0;
        yHaterMoveSpeeds3[1] = 0;

        haters[3] = new Hater((ImageView) findViewById(R.id.hater2_3));
        haters[3].setImageX(thePath3[0].getImageX());
        haters[3].setImageY(thePath3[0].getImageY());
        haters[3].setPath(thePath3, xHaterMoveSpeeds3, yHaterMoveSpeeds3);

        GridImageThing[] thePath4 = new GridImageThing[2];
        thePath4[0] = daGrid[27][3];
        thePath4[1] = daGrid[32][3];

        float[] xHaterMoveSpeeds4 = new float[2];
        xHaterMoveSpeeds4[0] = (float)1.50;
        xHaterMoveSpeeds4[1] = (float)1.50;

        float[] yHaterMoveSpeeds4 = new float[2];
        yHaterMoveSpeeds4[0] = 0;
        yHaterMoveSpeeds4[1] = 0;

        haters[4] = new Hater((ImageView) findViewById(R.id.hater2_4));
        haters[4].setImageX(thePath4[0].getImageX());
        haters[4].setImageY(thePath4[0].getImageY());
        haters[4].setPath(thePath4, xHaterMoveSpeeds4, yHaterMoveSpeeds4);

        GridImageThing[] thePath5 = new GridImageThing[2];
        thePath5[0] = daGrid[27][4];
        thePath5[1] = daGrid[32][4];

        float[] xHaterMoveSpeeds5 = new float[2];
        xHaterMoveSpeeds5[0] = 1;
        xHaterMoveSpeeds5[1] = 1;

        float[] yHaterMoveSpeeds5 = new float[2];
        yHaterMoveSpeeds5[0] = 0;
        yHaterMoveSpeeds5[1] = 0;

        haters[5] = new Hater((ImageView) findViewById(R.id.hater2_5));
        haters[5].setImageX(thePath5[0].getImageX());
        haters[5].setImageY(thePath5[0].getImageY());
        haters[5].setPath(thePath5, xHaterMoveSpeeds5, yHaterMoveSpeeds5);

        GridImageThing[] thePath6 = new GridImageThing[2];
        thePath6[0] = daGrid[35][5];
        thePath6[1] = daGrid[41][5];

        float[] xHaterMoveSpeeds6 = new float[2];
        xHaterMoveSpeeds6[0] = (float)1.50;
        xHaterMoveSpeeds6[1] = (float)1.50;

        float[] yHaterMoveSpeeds6 = new float[2];
        yHaterMoveSpeeds6[0] = 0;
        yHaterMoveSpeeds6[1] = 0;

        haters[6] = new Hater((ImageView) findViewById(R.id.hater2_6));
        haters[6].setImageX(thePath6[0].getImageX());
        haters[6].setImageY(thePath6[0].getImageY());
        haters[6].setPath(thePath6, xHaterMoveSpeeds6, yHaterMoveSpeeds6);

        return haters;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gameLogic.bobJumpLogic();
        return true;
    }

}